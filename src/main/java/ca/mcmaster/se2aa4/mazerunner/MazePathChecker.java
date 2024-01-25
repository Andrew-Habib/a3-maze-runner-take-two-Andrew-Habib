package ca.mcmaster.se2aa4.mazerunner;

public class MazePathChecker {

    private Maze maze;
    private String path_sequence;
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

    public void processPath() {
        String num = "0";

        this.setStartLeft();

        for (int i = 0; i < this.path_sequence.length(); i++) {
            if (Character.isDigit(this.path_sequence.charAt(i))) {
                num = num + this.path_sequence.charAt(i);
            } else {
                for (int j = 0; j < Integer.parseInt(num); j++) {
                    if (this.runner.getXLocation() >= 0 && this.runner.getXLocation() < this.maze.getWidth() - 1) {
                        if (this.path_sequence.charAt(i) == 'F') {
                            this.runner.runForward();
                        }
                    }
                }
                num = "0";
            }
        }
    }

    public void checkCorrect() {
        if (this.runner.getXLocation() == this.maze.getWidth() - 1) {
            System.out.println("correct path");
            
        } else {
            System.out.println("incorrect path");
            
        }
    }

    public MazePathChecker(Maze maze, String path) {
        this.maze = maze;
        this.path_sequence = path;
        this.runner = new Runner(0, 0, Direction.EAST);
    }

}