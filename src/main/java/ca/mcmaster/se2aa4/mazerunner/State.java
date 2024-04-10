package ca.mcmaster.se2aa4.mazerunner;

public interface State {
    void handle(Maze maze, Configuration configuration);
}
