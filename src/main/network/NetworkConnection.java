package main.network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public abstract class NetworkConnection {
    private ConnectionThread connThread = new ConnectionThread();
    private Consumer<Serializable> onReceiveCallback;

    public NetworkConnection(Consumer<Serializable> onReceiveCallback) {
        this.onReceiveCallback = onReceiveCallback;
        connThread.setDaemon(true);
    }

    public void startConnection() throws Exception {
        connThread.start();
    }

    public void send(Serializable data) throws Exception {
        connThread.out.writeObject(data);
    }

    public void closeConnection() throws Exception {
        connThread.socket.close();
    }

    protected abstract boolean isServer();

    protected abstract String getIP();

    protected abstract int getPort();

    private class ConnectionThread extends Thread {

        private Socket socket;
        private ObjectOutputStream out;

        @Override
        public void run() {
            try (ServerSocket server = isServer() ? new ServerSocket(getPort()) : null;
                 Socket socket = isServer() ? server.accept() : new Socket(getIP(), getPort())) {
                InputStream in = socket.getInputStream();
                OutputStream out = socket.getOutputStream();
                this.socket = socket;
                this.out = new ObjectOutputStream(out);
                socket.setTcpNoDelay(true);

                while (true) {
                    Serializable data = (Serializable) new ObjectInputStream(in).readObject();
                    onReceiveCallback.accept(data);
                }
            } catch (Exception e) {
                onReceiveCallback.accept("Connection closed");
            }
        }
    }
}
