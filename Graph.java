import java.util.ArrayList;
import java.util.List;
class Node {
    private int row;
    private int col;
    private boolean wall;
    private boolean visited;
    private List<Node> neighbors;

    public Node(int row, int col, boolean wall) {
        this.row = row;
        this.col = col;
        this.wall = wall;
        this.visited = false;
        this.neighbors = new ArrayList<>();
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isWall() {
        return wall;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public List<Node> getNeighbors() {
        return neighbors;
    }

    public void addNeighbor(Node neighbor) {
        neighbors.add(neighbor);
    }
}

public class Graph {

    //2D array of nodes to represent the cells in the maze.
    private Node[][] nodes;
    private int numRows;
    private int numCols;

    public Graph(int[][] maze) {

        // inicializojme grafin si array 2D
        this.numRows = maze.length;
        this.numCols = maze[0].length;
        this.nodes = new Node[numRows][numCols];

        // Krijojme nodes for cdo qelize in the maze
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                nodes[row][col] = new Node(row, col, maze[row][col] == 1);
            }
        }

        // AKrijojme edges aty ky qelizat jane te lira, pra jo walls dhe shtojme fqinje
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                if (!nodes[row][col].isWall()) {
                    if (row > 0 && !nodes[row - 1][col].isWall()) {
                        nodes[row][col].addNeighbor(nodes[row - 1][col]);
                    }
                    if (row < numRows - 1 && !nodes[row + 1][col].isWall()) {
                        nodes[row][col].addNeighbor(nodes[row + 1][col]);
                    }
                    if (col > 0 && !nodes[row][col - 1].isWall()) {
                        nodes[row][col].addNeighbor(nodes[row][col - 1]);
                    }
                    if (col < numCols - 1 && !nodes[row][col + 1].isWall()) {
                        nodes[row][col].addNeighbor(nodes[row][col + 1]);
                    }
                }
            }
        }
    }

    //kap node ne koordinatat e caktuara
    public Node getNode(int row, int col) {
        if (row >= 0 && row < numRows && col >= 0 && col < numCols) {
            return nodes[row][col];
        }
        return null;
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumCols() {
        return numCols;
    }

    public boolean depthFirstSearch(Node start, Node end, List<Node> path) {

        // base case kur fillimi = me fundin, kemi arritur fundin
        if (start == end) {
            path.add(start);
            return true;
        }

        //vizitiojme piken fillestare dhe e shtojme ne path
        start.setVisited(true);
        path.add(start);

        // iterojme ne cdo node tjeter - fqinjet e start
        for (Node neighbor : start.getNeighbors()) {
            if (!neighbor.isVisited()) {
                // nqs nuk eshte i vizituar me pare, bejme te njejtin veprim per current node
                if (depthFirstSearch(neighbor, end, path)) {
                    return true; //dmth qe path is found
                }
            }
        }

        //current node is removed from the path list if it doesnt lead to end
        path.remove(path.size() - 1);
        return false; // nuk con drejt pathit
    }
}


