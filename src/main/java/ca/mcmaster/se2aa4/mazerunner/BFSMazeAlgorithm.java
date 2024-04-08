package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayDeque;
import java.util.Queue;

public class BFSMazeAlgorithm {
    private Maze maze;
    private Runner runner;
    private Integer[][] pathLengths;
    private Integer[][][] previous;
    public BFSMazeAlgorithm(Maze maze, Runner runner) {
        this.maze = maze;
        this.runner = runner;
        pathLengths = new Integer[this.maze.getHeight()][this.maze.getWidth()];
        previous = new Integer[this.maze.getHeight()][this.maze.getWidth()][2];
    }
    public Integer[][] getPathLengths() {
        return this.pathLengths;
    }
    public Integer[][][] getShortestPaths() {
        return this.previous;
    }
    public void solve() {

        Queue<Integer[]> mapNodeQueue = new ArrayDeque<>();
        mapNodeQueue.add(new Integer[]{this.runner.getXLocation(), this.runner.getYLocation()});

        int runner_x = this.runner.getXLocation();
        int runner_y = this.runner.getYLocation();
        pathLengths[runner_x][runner_y] = 0;
        previous[runner_x][runner_y] = new Integer[]{runner_x, runner_y};
        mapNodeQueue.add(new Integer[]{runner_x, runner_y});

        while (!mapNodeQueue.isEmpty()) {
            Integer[] currPos = mapNodeQueue.remove();
            Integer[][] edges = new Integer[][]{{currPos[0] - 1, currPos[1]},
                    {currPos[0] + 1, currPos[1]}, {currPos[0], currPos[1] - 1}, {currPos[0], currPos[1] + 1}};

            for (Integer[] dir : edges) {
                if (dir[0] >= 0 && dir[1] >= 0 && dir[0] <= this.maze.getWidth() - 1 && dir[1] <= this.maze.getHeight() - 1
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

    }
}
