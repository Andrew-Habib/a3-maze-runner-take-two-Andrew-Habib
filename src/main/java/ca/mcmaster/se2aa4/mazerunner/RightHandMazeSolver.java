package ca.mcmaster.se2aa4.mazerunner;

public class RightHandMazeSolver extends MazeSolver {

    public RightHandMazeSolver(Maze maze) {
        super(maze);
    }

    @Override
    protected String algorithm() {

        String path = "";

        this.runner.runForward();
        path = path + 'F';

        while (this.runner.getXLocation() >= 0 && this.runner.getYLocation() >= 0 &&
        this.runner.getXLocation() < this.maze.getWidth() - 1 &&
        this.runner.getYLocation() <= this.maze.getHeight() - 1) {
            
            int right_x = this.runner.getXLocation();
            int right_y = this.runner.getYLocation();
            int forward_x = this.runner.getXLocation();
            int forward_y = this.runner.getYLocation();
            int left_x = this.runner.getXLocation();
            int left_y = this.runner.getYLocation();

            switch (this.runner.getDirection()) {
                case Direction.EAST:
                    right_y = this.runner.getYLocation() - 1;
                    forward_x = this.runner.getXLocation() + 1;
                    left_y = this.runner.getYLocation() + 1;
                    break;
                case Direction.WEST:
                    right_y = this.runner.getYLocation() + 1;
                    forward_x = this.runner.getXLocation() - 1;
                    left_y = this.runner.getYLocation() - 1;
                    break;
                case Direction.NORTH:
                    right_x = this.runner.getXLocation() + 1;
                    forward_y = this.runner.getYLocation() + 1;
                    left_x = this.runner.getXLocation() - 1;
                    break;
                case Direction.SOUTH:
                    right_x = this.runner.getXLocation() - 1;
                    forward_y = this.runner.getYLocation() - 1;
                    left_x = this.runner.getXLocation() + 1;
                    break;
            }

            if (this.maze.getTileAt(right_x, right_y) == Tile.EMPTY) {
                this.runner.turnRight();
                this.runner.runForward();
                path = path + "RF";
            } else if (this.maze.getTileAt(forward_x, forward_y) == Tile.EMPTY) {
                this.runner.runForward();
                path = path + 'F';
            } else if (this.maze.getTileAt(left_x, left_y) == Tile.EMPTY) {
                this.runner.turnLeft();
                this.runner.runForward();
                path = path + "LF";
            } else {
                this.runner.turnLeft();
                this.runner.turnLeft();
                this.runner.runForward();
                path = path + "LLF";
            }
            
        }

        return path;

    }

}