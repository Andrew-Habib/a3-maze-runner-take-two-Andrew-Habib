package ca.mcmaster.se2aa4.mazerunner;

import java.util.Collections;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.ArrayList;

// Referenced Ideas From SFWRENG 2C03 Slide Set 9 Slide Number 159 for Breadth-First Shortest Path Algorithm on Unweighted graphs by Dr.Hellings

public class BFSMazeSolver implements MazeSolver{
    private Maze maze;
    private Runner runner;
    private String path;

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
        int start_x = 0;
        int start_y = 0;
        int end_x = 0;
        int end_y = 0;

        for (int j = 0; j < previous[0].length; j++) {
            if (previous[0][j][0] != null && previous[0][j][1] != null) {
                start_x = previous[0][j][0];
                start_y = previous[0][j][1];
            }
            if (previous[previous.length - 1][j][0] != null && previous[previous.length - 1][j][1] != null) {
                end_x = previous[previous.length - 1][j][0];
                end_y = previous[previous.length - 1][j][1];
            }
        }
        ArrayList<Integer[]> path = new ArrayList();
        int curr_x = end_x;
        int curr_y = end_y;
        while (curr_x != start_x || curr_y != start_y) {
            path.add(new Integer[]{curr_x, curr_y});
            Integer[] prev = previous[curr_x][curr_y];
            curr_x = prev[0];
            curr_y = prev[1];
        }
        path.add(new Integer[]{start_x, start_y});
        Collections.reverse(path);
        path.add(new Integer[]{end_x + 1, end_y});
        this.path = "";

        for (Integer[] pos : path) {
            Direction currDir = this.runner.getDirection();
            int currX = this.runner.getXLocation();
            int currY = this.runner.getYLocation();

            int right_x = currX + Direction.getRight(currDir).unitX();
            int right_y = currY + Direction.getRight(currDir).unitY();
            int forward_x = currX + currDir.unitX();
            int forward_y = currY + currDir.unitY();
            int left_x = currX + Direction.getLeft(currDir).unitX();
            int left_y = currY + Direction.getLeft(currDir).unitY();

            if (right_x == pos[0] && right_y == pos[1]) {
                this.runner.turnRight();
                this.runner.runForward();
                this.path = this.path + "RF";
            } else if (forward_x == pos[0] && forward_y == pos[1]) {
                this.runner.runForward();
                this.path = this.path + 'F';
            } else if (left_x == pos[0] && left_y == pos[1]) {
                this.runner.turnLeft();
                this.runner.runForward();
                this.path = this.path + "LF";
            }
        }
    }

    @Override
    public String getCanonicalForm() {
        return this.path;
    }

    @Override
    public String getFactorizedForm() {

        String fact_path = "";
        char before = this.path.charAt(0);
        int num = 1;


        for (int i = 1; i < path.length(); i++) {

            if (this.path.charAt(i) == before && i < this.path.length()) {

                num = num + 1;
                if (i == this.path.length() - 1) {
                    fact_path = fact_path + String.valueOf(num) + before;
                }

            } else {

                if (num > 1) {
                    fact_path = fact_path + String.valueOf(num);
                }

                fact_path = fact_path + before + ' ';
                num = 1;
                before = path.charAt(i);

                if (i == this.path.length() - 1) {
                    fact_path = fact_path + before;
                }

            }

        }

        return fact_path;

    }
}
