package ca.mcmaster.se2aa4.mazerunner;

import java.util.HashMap;
import java.util.Map;

public class StateSolve implements State{
    private final static Map<String, MazeSolver> mapMazeSolvers = new HashMap<>();
    @Override
    public void handle(Maze maze, Configuration config) {
        mapMazeSolvers.put("righthand", new RightHandMazeSolver(maze));
        mapMazeSolvers.put("bfs", new BFSMazeSolver(maze));
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
