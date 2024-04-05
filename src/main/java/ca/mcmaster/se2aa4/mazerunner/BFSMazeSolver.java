package ca.mcmaster.se2aa4.mazerunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Queue;
import java.util.ArrayDeque;


// Referenced Ideas From SFWRENG 2C03 Slide Set 9 Slide Number 159 for Breadth-First Shortest Path Algorithm on Unweighted graphs by Dr.Hellings

public class BFSMazeSolver implements MazeSolver{
    private Maze maze;
    private Runner runner;
    private String path;
    private static final Logger logger = LogManager.getLogger();

    public BFSMazeSolver(Maze maze) {
        this.maze = maze;
        this.path = "";
        this.runner = new Runner(0, 0, Direction.EAST);
    }

    @Override
    public void setStartDirection(Direction dir) {
        switch(dir) {
            case Direction.WEST:
                for (int row = 0; row < this.maze.getHeight() - 1; row++) {
                    if (this.maze.getTileAt(0, row) == Tile.EMPTY) {
                        this.runner.setXLocation(0);
                        this.runner.setYLocation(row);
                        this.runner.setDirection(Direction.EAST);
                    }
                }
                break;
            case Direction.EAST:
                for (int row = 0; row < this.maze.getHeight() - 1; row++) {
                    if (this.maze.getTileAt(this.maze.getWidth() - 1, row) == Tile.EMPTY) {
                        this.runner.setXLocation(this.maze.getWidth() - 1);
                        this.runner.setYLocation(row);
                        this.runner.setDirection(Direction.WEST);
                    }
                }
                break;
        }

    }

    @Override
    public void solve() {
        Integer[][] pathLengths = new Integer[this.maze.getHeight()][this.maze.getWidth()];
        Integer[][][] previous = new Integer[this.maze.getHeight()][this.maze.getWidth()][2];
        Queue<Integer[]> mapNodeQueue = new ArrayDeque<>();
        mapNodeQueue.add(new Integer[]{this.runner.getXLocation(), this.runner.getYLocation()});

        int runner_x = this.runner.getXLocation();
        int runner_y = this.runner.getYLocation();
        pathLengths[runner_x][runner_y] = 0;
        previous[runner_x][runner_y] = new Integer[]{runner_x, runner_y};
        mapNodeQueue.add(new Integer[]{runner_x, runner_y});

        while (!mapNodeQueue.isEmpty()) {
            Integer[] currPos = mapNodeQueue.remove();
            Integer[][] directions = new Integer[][]{{currPos[0] - 1, currPos[1]},
                    {currPos[0] + 1, currPos[1]}, {currPos[0], currPos[1] - 1}, {currPos[0], currPos[1] + 1}};

            for (Integer[] dir : directions) {
                if (dir[0] >= 0 && dir[1] >= 0 && dir[0] <= this.maze.getWidth() - 1 && dir[1] <= this.maze.getWidth() - 1
                        && this.maze.getTileAt(dir[0], dir[1]) == Tile.EMPTY) {
                    if (pathLengths[dir[0]][dir[1]] == null
                            || pathLengths[currPos[0]][currPos[1]] + 1 < pathLengths[dir[0]][dir[1]]) {
                        pathLengths[dir[0]][dir[1]] = pathLengths[currPos[0]][currPos[1]] + 1;
                        previous[dir[0]][dir[1]] = new Integer[]{currPos[0], currPos[1]};
                        mapNodeQueue.add(new Integer[]{dir[0], dir[1]});
                    }
                }
            }

        }
        // Output pathLengths array
        System.out.println("Path Lengths:");
        for (int i = 0; i < pathLengths.length; i++) {
            for (int j = 0; j < pathLengths[i].length; j++) {
                System.out.print(pathLengths[i][j] + " ");
            }
            System.out.println();
            System.out.println();
            System.out.println();
        }

// Output previous array
        System.out.println("\nPrevious:");
        for (int i = 0; i < previous.length; i++) {
            for (int j = 0; j < previous[i].length; j++) {
                for (int k = 0; k < previous[i][j].length; k++) {
                    System.out.print(previous[i][j][k] + " ");
                }
                System.out.print("\t");
            }
            System.out.println();
            System.out.println();
            System.out.println();
        }
        this.path = "";
    }

    @Override
    public String getCanonicalForm() {
        return this.path;
    }

    @Override
    public String getFactorizedForm() {

        String fact_path = "";
        return fact_path;

    }
}
