package ca.mcmaster.se2aa4.mazerunner;

public class MazePathChecker {

    private Maze maze = new Maze();
    private String path_sequence = "";
    private Runner run_checker = new Runner(0, 0, Direction.EAST);

    public void processPath() {
        String num = "0";
        for (int i = 0; i < this.path_sequence.length(); i++) {
            if (Character.isDigit(this.path_sequence.charAt(i))) {
                num = num + this.path_sequence.charAt(i);
            } else {
                for (int j = 0; j < Integer.parseInt(num); j++) {
                    if (this.run_checker.getXLocation() >= 0 && this.run_checker.getXLocation() < this.maze.endX()) {
                        if (this.path_sequence.charAt(i) == 'F') {
                            run_checker.runForward();
                        }
                    }
                }
                num = "0";
            }
        }
    }

    public void checkCorrect() {
        if (this.run_checker.getXLocation() == this.maze.endX() && this.run_checker.getYLocation() == this.maze.endY()) {
            System.out.println("correct path");
            
        } else {
            System.out.println("incorrect path");
            
        }
    }

    public MazePathChecker(Maze maze, String path) {
        this.maze = maze;
        this.path_sequence = path;
        this.run_checker = new Runner(maze.startX(), maze.startY(), Direction.EAST);
    }

}