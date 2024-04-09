package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.Level;

import java.util.Map;
import java.util.HashMap;

public class Main {

    private static final Logger logger = LogManager.getLogger();
    private final static Map<String, MazeSolver> mapMazeSolvers = new HashMap<>();
    
    public static void main(String[] args) {

        Configurator.setAllLevels(LogManager.getRootLogger().getName(), Level.ERROR);
        logger.info("Execution Begins");

        Configuration config = new Configuration();
        config.processInput(args);
        MazeImporter imported_maze = new MazeImporter(config.getMazeFile());
        imported_maze.importMaze();
        Maze maze = imported_maze.getMaze();

        mapMazeSolvers.put("righthand", new RightHandMazeSolver(maze));
        mapMazeSolvers.put("bfs", new BFSMazeSolver(maze));

            if (config.pathGiven()) {
                MazePathChecker pathChecker = new MazePathChecker(maze, config.getPathSequence());
                pathChecker.processPath();
                pathChecker.checkCorrect();
            } else {

                MazeSolver solver = mapMazeSolvers.get(config.getMethod());
                solver.initialize(Direction.WEST);
                solver.solve();
                System.out.println(solver.getFactorizedForm());
                if (config.baselineGiven()) {
                    MazeSolver baseline = mapMazeSolvers.get(config.getBaseline());
                    baseline.initialize(Direction.WEST);
                    baseline.solve();
                    Benchmark benchmark = new Benchmark(solver, baseline, new MazeImporter(config.getMazeFile()),
                            solver.getCanonicalForm(), baseline.getCanonicalForm());
                    System.out.println("Maze Import Time (ms): " + benchmark.getMazeImportTime());
                    System.out.println("Method Algorithm Time (ms): " + benchmark.getMethodTime());
                    System.out.println("Baseline Algorithm Time (ms): " + benchmark.getBaselineTime());
                    System.out.println("Speedup: " + benchmark.getSpeedUp());
                }

            }

    }
    
}
