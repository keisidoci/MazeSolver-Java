
public class Main {
    public static void main(String[] args) {
        int[][] maze = {
                {0, 1, 0, 0, 0},
                {0, 1, 0, 1, 0},
                {0, 0, 0, 1, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 0, 0}
        };

        Graph graph = new Graph(maze);

        System.out.println("Generated Maze:");
        printMaze(maze);

        System.out.println();

        MazeSolver.solveMaze(graph, 0, 0, 4, 4);
    }

    public static void printMaze(int[][] maze) {
        for (int[] row : maze) {
            for (int cell : row) {
                System.out.print(cell == 1 ? "#  " : "   ");
            }
            System.out.println();
        }
    }
}
