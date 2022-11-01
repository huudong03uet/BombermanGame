package main.entities.enemy.AI;

import java.util.ArrayList;

import static main.settings.PropertiesConstant.HEIGHT_TILE;
import static main.settings.PropertiesConstant.WIDTH_TILE;
import static main.settings.PropertiesStatic.map;

public class AStar {
    private ArrayList<Node> nodeOpenList = new ArrayList<>();
    private ArrayList<Node> pathArrayList = new ArrayList<>();
    private Node startNode;
    private Node goalNode;
    private Node nowNode;
    private Node[][] nodes;
    private boolean goal = false;
    private int step = 0;

    public AStar() {
        // init start and goal node
        nodes = new Node[WIDTH_TILE][HEIGHT_TILE];
        for (int i = 0; i < WIDTH_TILE; i++) {
            for (int j = 0; j < HEIGHT_TILE; j++) {
                nodes[i][j] = new Node(i, j);
            }
        }
    }

    public void openNode(Node node) {
        if (node.isOpen() == false && node.isVisited() == false && node.isSolid() == false) {
            node.setParent(nowNode);
            node.setOpen(true);
            nodeOpenList.add(node);
        }
    }

    public void setNodes(int startCol, int startRow, int goalCol, int goalRow) {
        // resset all nodes
        for (int i = 0; i < WIDTH_TILE; i++)
            for (int j = 0; j < HEIGHT_TILE; j++) {
                nodes[i][j].setVisited(false);
                nodes[i][j].setSolid(false);
                nodes[i][j].setOpen(false);
            }
        pathArrayList.clear();
        nodeOpenList.clear();


        step = 0;
        goal = false;


        this.startNode = nodes[startCol][startRow];
        nowNode = startNode;
        goalNode = nodes[goalCol][goalRow];
        nodeOpenList.add(nowNode);

        for (int i = 0; i < WIDTH_TILE; i++) {
            for (int j = 0; j < HEIGHT_TILE; j++) {
                if (map[j][i] == '*' || map[j][i] == '#') {
                    nodes[i][j].setSolid(true);
                }
                getCostPath(nodes[i][j]);
            }
        }
    }

    public void getCostPath(Node node) {
        int xDistance = Math.abs(node.getColumn() - startNode.getColumn());
        int yDistance = Math.abs(node.getRow() - startNode.getRow());
        node.setgCost(xDistance + yDistance);
        xDistance = Math.abs(node.getColumn() - goalNode.getColumn());
        yDistance = Math.abs(node.getRow() - goalNode.getRow());
        node.setgCost(xDistance + yDistance);
        node.setfCost(node.getgCost() + node.gethCost());
    }

    public boolean searchPath() {
        while (goal == false && step < 999) {
            int column = nowNode.getColumn();
            int row = nowNode.getRow();
            nowNode.setVisited(true);
            nodeOpenList.remove(nowNode);

            if (column - 1 >= 0) {
                openNode(nodes[column - 1][row]);
            }
            if (row - 1 >= 0) {
                openNode(nodes[column][row - 1]);
            }
            if (row + 1 < WIDTH_TILE) {
                openNode(nodes[column][row + 1]);
            }
            if (column + 1 < HEIGHT_TILE) {
                openNode(nodes[column + 1][row]);
            }

            int bestNodeId = 0;
            int bestNodefCost = 999;
            for (int i = 0; i < nodeOpenList.size(); i++) {
                if (nodeOpenList.get(i).getfCost() < bestNodefCost) {
                    bestNodeId = i;
                    bestNodefCost = nodeOpenList.get(i).getfCost();
                } else if (nodeOpenList.get(i).getfCost() == bestNodefCost) {
                    if (nodeOpenList.get(i).getgCost() < nodeOpenList.get(bestNodeId).getgCost()) {
                        bestNodeId = i;
                    }
                }
            }

            if (nodeOpenList.size() == 0) {
                break;
            }

            nowNode = nodeOpenList.get(bestNodeId);
            if (nowNode == goalNode) {
                goal = true;
                // trace back
                Node nowNode = goalNode;
                while (nowNode != startNode) {
                    pathArrayList.add(0, nowNode);
                    nowNode = nowNode.getParent();
                }
            }
            step++;
        }
        return goal;
    }
    public ArrayList<Node> getPathArrayList() {
        return pathArrayList;
    }
}
