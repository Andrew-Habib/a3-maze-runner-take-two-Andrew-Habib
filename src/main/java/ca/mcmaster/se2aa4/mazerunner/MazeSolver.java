package ca.mcmaster.se2aa4.mazerunner;

public class MazeSolver {

    private Maze maze;
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

    public void solve() {

        this.setStartLeft();
        String path = this.right_hand_algorithm();
        String canonical_path = this.canonicalForm(path);
        System.out.println(canonical_path);

    }

    private String right_hand_algorithm() {

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

    private String canonicalForm(String path) {

        String canonical_path = "";
        char before = path.charAt(0);
        int num = 1;


        for (int i = 1; i < path.length(); i++) {
            if (path.charAt(i) == before && i < path.length()) {
                num = num + 1;
                if (i == path.length() - 1) {
                    canonical_path = canonical_path + String.valueOf(num) + before;
                }
            } else {
                if (num > 1) {
                    canonical_path = canonical_path + String.valueOf(num);
                }
                canonical_path = canonical_path + before + ' ';
                before = path.charAt(i);
                num = 1;
            }
        } 

        return canonical_path;

    }

    public MazeSolver(Maze maze) {
        this.maze = maze;
        this.runner = new Runner(0, 0, Direction.EAST);
    }

}
