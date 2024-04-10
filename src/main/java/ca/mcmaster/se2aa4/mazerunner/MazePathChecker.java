package ca.mcmaster.se2aa4.mazerunner;

public class MazePathChecker implements MazeChecker{

    private Maze maze;
    private String path_sequence;
    private Runner runner;

    private void setStartLeft() {

        for (int row = 0; row < this.maze.getHeight() - 1; row++) {
            if (this.maze.getTileAt(0, row) == Tile.EMPTY) {
                this.runner.setXLocation(0);
                this.runner.setYLocation(row);
                this.runner.setDirection(Direction.EAST);
            }
        }
        
    }

    @Override
    public void processPath() {

        int num = 0;
        this.setStartLeft();

        this.path_sequence = this.path_sequence.toUpperCase();

        for (int i = 0; i < this.path_sequence.length(); i++) {
            if (Character.isDigit(this.path_sequence.charAt(i))) {
                num = num * 10 + Character.getNumericValue(this.path_sequence.charAt(i));
            } else if (this.path_sequence.charAt(i) == 'F' || this.path_sequence.charAt(i) == 'L' || this.path_sequence.charAt(i) == 'R') {
                if (num == 0) {
                    num = 1;
                }
                for (int j = 0; j < num; j++) {
                    if (this.runner.getXLocation() >= 0 && this.runner.getYLocation() >= 0 &&
                        this.runner.getXLocation() < this.maze.getWidth() - 1 &&
                        this.runner.getYLocation() <= this.maze.getHeight() - 1) {
                        switch (this.path_sequence.charAt(i)) {
                            case 'F':
                                Runner dummy = new Runner(this.runner.getXLocation(), this.runner.getYLocation(), this.runner.getDirection());
                                dummy.runForward();
                                if (this.maze.getTileAt(dummy.getXLocation(), dummy.getYLocation()) == Tile.EMPTY) {
                                    this.runner.runForward();
                                }   
                                break;
                            case 'L':
                                this.runner.turnLeft();
                                break;
                            case 'R':
                                this.runner.turnRight();
                                break;
                        }
                    }
                }
                num = 0;
            }
        }
    }

    @Override
    public boolean isCorrect() {
        return (this.runner.getXLocation() == this.maze.getWidth() - 1);
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