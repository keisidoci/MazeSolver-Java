import java.util.ArrayList;
import java.util.List;

public class MazeSolver {
    public static boolean solveMaze(Graph graph, int startRow, int startCol, int endRow, int endCol) {

        // marrim start node dhe end node nga koordinatat e dhena
        Node startNode = graph.getNode(startRow, startCol);
        Node endNode = graph.getNode(endRow, endCol);

        if (startNode == null || endNode == null) {
            return false; // Start or end node is invalid dhe maze nuk zgjidhet
        }

        //ruajme ne path noded qe jane zgjidhje, duke thirrush funksionin qe ben kerkimin ne Graph
        List<Node> path = new ArrayList<>();
        boolean found = graph.depthFirstSearch(startNode, endNode, path);

        if (found) {
            System.out.println("Path found:");
            printMazeWithPath(graph, path);
        } else {
            System.out.println("No path found.");
        }

        return found;
    }

    public static void printMazeWithPath(Graph graph, List<Node> path) {
        int startRow = 0, startCol = 0;
        int endRow = graph.getNumRows() - 1, endCol = graph.getNumCols() - 1;

        for (int row = 0; row < graph.getNumRows(); row++) {
            for (int col = 0; col < graph.getNumCols(); col++) {
                Node node = graph.getNode(row, col);

                if (row == startRow && col == startCol) {
                    System.out.print("S ");
                } else if (row == endRow && col == endCol) {
                    System.out.print("E ");
                } else if (node.isWall()) {
                    System.out.print("# ");
                } else if (path.contains(node)) {
                    System.out.print("* ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }

}
