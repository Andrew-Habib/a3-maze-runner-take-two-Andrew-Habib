package ca.mcmaster.se2aa4.mazerunner;

public class MazeSolver {

    private Maze maze = new Maze();
    private Runner run_solver = new Runner(0, 0, Direction.EAST);

    public void solve() {

        int num = 0;
        char dir = 'F';
        while (this.run_solver.getXLocation() != this.maze.endX() || this.run_solver.getYLocation() != this.maze.endY()) {
            run_solver.runForward();
            num = num + 1;
        }
        String path = String.valueOf(num) + dir;
        System.out.println(path);

    }

    public MazeSolver(Maze maze) {
        this.maze = maze;
        this.run_solver = new Runner(maze.startX(), maze.startY(), Direction.EAST);
    }

}
