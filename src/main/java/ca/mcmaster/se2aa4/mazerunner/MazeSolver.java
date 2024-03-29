package ca.mcmaster.se2aa4.mazerunner;

public interface MazeSolver {

    void setStartDirection(Direction dir);
    void solve();
    String getCanonicalForm();
    String getFactorizedForm();

}
