package ca.mcmaster.se2aa4.mazerunner;

public class SolverInitialize {
    private Maze maze;
    private Runner runner;
    public SolverInitialize(Maze maze, Runner runner){
        this.maze = maze;
        this.runner = runner;
    }
    public void setStart(Direction dir) {
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
}
