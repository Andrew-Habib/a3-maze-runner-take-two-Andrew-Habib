package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BFSMazeSolverTest {
    private Maze straight;
    private Maze tiny;
    private Maze direct;
    private Maze small;
    private Maze regular;
    private Maze rectangle;
    private Maze medium;
    private Maze large;
    private Maze huge;
    private Maze giant;
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
        String rectangle_text = "./examples/rectangle.maz.txt";
        String medium_text = "./examples/medium.maz.txt";
        String large_text = "./examples/large.maz.txt";
        String huge_text = "./examples/huge.maz.txt";
        String giant_text = "./examples/giant.maz.txt";

        MazeImporter importer_straight = new MazeImporter(straight_text);
        MazeImporter importer_tiny = new MazeImporter(tiny_text);
        MazeImporter importer_direct = new MazeImporter(direct_text);
        MazeImporter importer_small = new MazeImporter(small_text);
        MazeImporter importer_regular = new MazeImporter(regular_text);
        MazeImporter importer_rectangle = new MazeImporter(rectangle_text);
        MazeImporter importer_medium = new MazeImporter(medium_text);
        MazeImporter importer_large = new MazeImporter(large_text);
        MazeImporter importer_huge = new MazeImporter(huge_text);
        MazeImporter importer_giant = new MazeImporter(giant_text);

        importer_straight.importMaze();
        importer_tiny.importMaze();
        importer_direct.importMaze();
        importer_small.importMaze();
        importer_regular.importMaze();
        importer_rectangle.importMaze();
        importer_medium.importMaze();
        importer_large.importMaze();
        importer_huge.importMaze();
        importer_giant.importMaze();

        straight = importer_straight.getMaze();
        tiny = importer_tiny.getMaze();
        direct = importer_direct.getMaze();
        small = importer_small.getMaze();
        regular = importer_regular.getMaze();
        rectangle = importer_rectangle.getMaze();
        medium = importer_medium.getMaze();
        large = importer_large.getMaze();
        huge = importer_huge.getMaze();
        giant = importer_giant.getMaze();

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
    @Test
    void solverRectangleBFS() {

        BFSMazeSolver solverRectangle = new BFSMazeSolver(rectangle);
        solverRectangle.initialize(Direction.WEST);
        solverRectangle.solve();
        MazePathChecker checker = new MazePathChecker(rectangle, solverRectangle.getFactorizedForm());
        checker.processPath();
        assertTrue(checker.isCorrect());
    }
    @Test
    void solverMediumBFS() {
        BFSMazeSolver solverMedium = new BFSMazeSolver(medium);
        solverMedium.initialize(Direction.WEST);
        solverMedium.solve();
        MazePathChecker checker = new MazePathChecker(medium, solverMedium.getFactorizedForm());
        checker.processPath();
        assertTrue(checker.isCorrect());
    }
    @Test
    void solverLargeBFS() {
        BFSMazeSolver solverLarge = new BFSMazeSolver(large);
        solverLarge.initialize(Direction.WEST);
        solverLarge.solve();
        MazePathChecker checker = new MazePathChecker(large, solverLarge.getFactorizedForm());
        checker.processPath();
        assertTrue(checker.isCorrect());
    }
    @Test
    void solverHugeBFS() {
        BFSMazeSolver solverHuge = new BFSMazeSolver(huge);
        solverHuge.initialize(Direction.WEST);
        solverHuge.solve();
        MazePathChecker checker = new MazePathChecker(huge, solverHuge.getFactorizedForm());
        checker.processPath();
        assertTrue(checker.isCorrect());
    }
    @Test
    void solverGiantBFS() {
        BFSMazeSolver solverGiant = new BFSMazeSolver(giant);
        solverGiant.initialize(Direction.WEST);
        solverGiant.solve();
        MazePathChecker checker = new MazePathChecker(giant, solverGiant.getFactorizedForm());
        checker.processPath();
        assertTrue(checker.isCorrect());
    }
}
