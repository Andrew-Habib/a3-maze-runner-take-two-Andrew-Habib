package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BFSMazeSolverTest {
    private Maze straight;
    private Maze tiny;
    private Maze direct;
    private Maze small;
    private Runner runner;
    @BeforeEach
    void initialize(){
        String straight_text = "./examples/straight.maz.txt";
        String tiny_text = "./examples/tiny.maz.txt";
        String direct_text = "./examples/direct.maz.txt";
        String small_text = "./examples/small.maz.txt";
        MazeImporter importer_straight = new MazeImporter(straight_text);
        MazeImporter importer_tiny = new MazeImporter(tiny_text);
        MazeImporter importer_direct = new MazeImporter(direct_text);
        MazeImporter importer_small = new MazeImporter(small_text);

        importer_straight.importMaze();
        importer_tiny.importMaze();
        importer_direct.importMaze();
        importer_small.importMaze();

        straight = importer_straight.getMaze();
        tiny = importer_tiny.getMaze();
        direct = importer_direct.getMaze();
        small = importer_small.getMaze();
        runner = new Runner(0, 0, Direction.WEST);
    }
    @Test
    void getCanonicalForm() {
        BFSMazeSolver solverStraight = new BFSMazeSolver(straight);
        solverStraight.initialize(Direction.WEST);
        solverStraight.solve();
        assertEquals("4F", solverStraight.getFactorizedForm());
    }
}
