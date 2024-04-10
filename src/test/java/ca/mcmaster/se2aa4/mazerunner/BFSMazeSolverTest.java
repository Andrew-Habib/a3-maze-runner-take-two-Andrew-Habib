package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BFSMazeSolverTest {
    private Maze straight;
    private Maze tiny;
    private Maze direct;
    private Maze small;
    private Maze regular;
    private Runner runner;
    private static final Logger logger = LogManager.getLogger();
    @BeforeEach
    void initialize(){
        Configurator.setAllLevels(LogManager.getRootLogger().getName(), Level.ERROR);
        logger.info("Execution Begins");

        String straight_text = "./examples/straight.maz.txt";
        String tiny_text = "./examples/tiny.maz.txt";
        String direct_text = "./examples/direct.maz.txt";
        String small_text = "./examples/small.maz.txt";
        String regular_text = "./examples/regular.maz.txt";
        MazeImporter importer_straight = new MazeImporter(straight_text);
        MazeImporter importer_tiny = new MazeImporter(tiny_text);
        MazeImporter importer_direct = new MazeImporter(direct_text);
        MazeImporter importer_small = new MazeImporter(small_text);
        MazeImporter importer_regular = new MazeImporter(regular_text);

        importer_straight.importMaze();
        importer_tiny.importMaze();
        importer_direct.importMaze();
        importer_small.importMaze();
        importer_regular.importMaze();

        straight = importer_straight.getMaze();
        tiny = importer_tiny.getMaze();
        direct = importer_direct.getMaze();
        small = importer_small.getMaze();
        regular = importer_regular.getMaze();
        runner = new Runner(0, 0, Direction.WEST);
    }
    @Test
    void solverStraightBFS() {
        BFSMazeSolver solverStraight = new BFSMazeSolver(straight);
        solverStraight.initialize(Direction.WEST);
        solverStraight.solve();
        assertEquals("4F", solverStraight.getFactorizedForm());
    }
    @Test
    void solverTinyBFS() {
        BFSMazeSolver solverTiny = new BFSMazeSolver(tiny);
        solverTiny.initialize(Direction.WEST);
        solverTiny.solve();
        assertEquals("3F L 4F R 3F", solverTiny.getFactorizedForm());
    }
    @Test
    void solverDirectBFS() {
        BFSMazeSolver solverDirect = new BFSMazeSolver(direct);
        solverDirect.initialize(Direction.WEST);
        solverDirect.solve();
        assertEquals("F R 2F L 4F R 2F L 2F", solverDirect.getFactorizedForm());
    }
    @Test
    void solverSmallBFS() {
        BFSMazeSolver solverSmall = new BFSMazeSolver(small);
        solverSmall.initialize(Direction.WEST);
        solverSmall.solve();
        assertEquals("F L F R 2F L 6F R 4F R 2F L 2F R 2F L F", solverSmall.getFactorizedForm());
    }
    @Test
    void solverRegularBFS() {
        BFSMazeSolver solverRegular = new BFSMazeSolver(regular);
        solverRegular.initialize(Direction.WEST);
        solverRegular.solve();
        assertEquals("3F L 2F L 2F R 30F R 16F R 2F L 4F R 4F L 2F R 2F L 2F R 6F L 2F R 4F L 2F R 6F L 4F R 2F L 2F R 2F L 4F L 2F R F",
                solverRegular.getFactorizedForm());
    }
}
