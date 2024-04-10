package ca.mcmaster.se2aa4.mazerunner;

public class EventManagerContext implements State {
    private State eventState;

    public void changeState(State state) {
        this.eventState = state;
    }
    @Override
    public void handle(Maze maze, Configuration configuration) {
        this.eventState.handle(maze, configuration);
    }
}
