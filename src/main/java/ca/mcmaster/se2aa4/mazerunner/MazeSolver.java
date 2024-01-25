package ca.mcmaster.se2aa4.mazerunner;

public class MazeSolver {

    private Maze maze;
    private Runner runner;

    private void setStartLeft() {

        for (int row = 0; row < this.maze.getHeight() - 1; row++) {
            if (this.maze.getTileAt(row, 0) == Tile.EMPTY) {
                this.runner.setXLocation(0);
                this.runner.setYLocation(row);
                this.runner.setDirection(Direction.EAST);
            }
        }
        
    }

    public void solve() {

        int num = 0;
        char dir = 'F';

        this.setStartLeft();

        while (this.runner.getXLocation() >= 0 && this.runner.getYLocation() >= 0 &&
            this.runner.getXLocation() < this.maze.getWidth() - 1 &&
            this.runner.getYLocation() <= this.maze.getHeight() - 1) {
            this.runner.runForward();
            num = num + 1;
        }
        String path = String.valueOf(num) + dir;
        System.out.println(path);

    }

    private void right_hand_algorithm() {
        
    }

    public MazeSolver(Maze maze) {
        this.maze = maze;
        this.runner = new Runner(0, 0, Direction.EAST);
    }

}
