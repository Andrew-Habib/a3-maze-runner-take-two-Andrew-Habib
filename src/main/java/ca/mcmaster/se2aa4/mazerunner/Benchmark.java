package ca.mcmaster.se2aa4.mazerunner;

import java.text.DecimalFormat;

public class Benchmark {
    private MazeImporter maze_importer;
    private MazeSolver method;
    private MazeSolver baseline;
    private int methodLen = 1;
    private int baselineLen = 1;
    private final DecimalFormat decimalFormat = new DecimalFormat("0.00");
    public Benchmark(MazeSolver method, MazeSolver baseline, MazeImporter maze_importer, String methodStr, String baselineStr) {
        this.maze_importer = maze_importer;
        this.method = method;
        this.baseline = baseline;
        this.methodLen = methodStr.length();
        this.baselineLen = baselineStr.length();
    }
    public String getMazeImportTime() {
        long startTime = System.currentTimeMillis();
        this.maze_importer.importMaze();
        long endTime = System.currentTimeMillis();
        return decimalFormat.format( endTime - startTime);
    }
    public String getMethodTime() {
        long startTime = System.currentTimeMillis();
        this.method.setStartDirection(Direction.WEST);
        this.method.solve();
        long endTime = System.currentTimeMillis();
        return decimalFormat.format( endTime - startTime);
    }
    public String getBaselineTime() {
        long startTime = System.currentTimeMillis();
        this.baseline.setStartDirection(Direction.WEST);
        this.baseline.solve();
        long endTime = System.currentTimeMillis();
        return decimalFormat.format( endTime - startTime);
    }
    public String getSpeedUp() {
        return decimalFormat.format((float) this.baselineLen / this.methodLen);
    }
}
