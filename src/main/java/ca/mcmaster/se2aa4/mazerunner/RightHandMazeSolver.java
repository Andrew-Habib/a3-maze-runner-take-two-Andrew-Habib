package ca.mcmaster.se2aa4.mazerunner;

public class RightHandMazeSolver implements MazeSolver {

    private Maze maze;
    private Runner runner;
    private String path;

    public RightHandMazeSolver(Maze maze) {
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
        this.runner.runForward();
        this.path = this.path + 'F';

        while (this.runner.getXLocation() >= 0 && this.runner.getYLocation() >= 0 &&
        this.runner.getXLocation() < this.maze.getWidth() - 1 &&
        this.runner.getYLocation() <= this.maze.getHeight() - 1) {
            Direction currDir = this.runner.getDirection();
            int currX = this.runner.getXLocation();
            int currY = this.runner.getYLocation();

            int right_x = currX + Direction.getRight(currDir).unitX();
            int right_y = currY + Direction.getRight(currDir).unitY();
            int forward_x = currX + currDir.unitX();
            int forward_y = currY + currDir.unitY();
            int left_x = currX + Direction.getLeft(currDir).unitX();
            int left_y = currY + Direction.getLeft(currDir).unitY();

            if (this.maze.getTileAt(right_x, right_y) == Tile.EMPTY) {
                this.runner.turnRight();
                this.runner.runForward();
                this.path = this.path + "RF";
            } else if (this.maze.getTileAt(forward_x, forward_y) == Tile.EMPTY) {
                this.runner.runForward();
                this.path = this.path + 'F';
            } else if (this.maze.getTileAt(left_x, left_y) == Tile.EMPTY) {
                this.runner.turnLeft();
                this.runner.runForward();
                this.path = this.path + "LF";
            } else {
                this.runner.turnLeft();
                this.runner.turnLeft();
                this.runner.runForward();
                this.path = this.path + "LLF";
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