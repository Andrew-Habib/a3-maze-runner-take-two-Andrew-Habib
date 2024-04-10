package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;

/*
Referenced Ideas From SFWRENG 2C03 Slide Set 9 Slide Number 159 for Breadth-First Shortest Path Algorithm
on Unweighted graphs along with Slide Set 10 Number 226 for condition and path and cost storing by Dr.Hellings
 */

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
    public void initialize(Direction dir) {
        SolverInitialize dirSet = new SolverInitialize(this.maze, this.runner);
        dirSet.setStart(Direction.WEST);
    }

    @Override
    public void solve() {
        this.path = "";
        BFSMazeAlgorithm bfsAlgorithm = new BFSMazeAlgorithm(this.maze, this.runner);
        bfsAlgorithm.solve();
        Integer[][][] previous = bfsAlgorithm.getShortestPaths();
        TraversePath pathTraverser = new TraversePath(previous);
        pathTraverser.traversePath();
        ArrayList<Integer[]> path = pathTraverser.getPath();

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
        return FactorizedConverter.canonicalToFactorized(this.path);
    }
}
