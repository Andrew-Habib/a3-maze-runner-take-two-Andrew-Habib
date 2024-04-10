package ca.mcmaster.se2aa4.mazerunner;

public class StateCheckPath implements State{
    @Override
    public void handle(Maze maze, Configuration config) {
        MazePathChecker pathChecker = new MazePathChecker(maze, config.getPathSequence());
        pathChecker.processPath();
        pathChecker.checkCorrect();
    }
}
