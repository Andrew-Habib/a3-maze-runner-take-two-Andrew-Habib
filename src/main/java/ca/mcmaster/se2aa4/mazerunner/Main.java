package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {

        logger.info("Execution Begins");
        Configuration config = new Configuration();
        config.processInput(args);
        MazeImporter imported_maze = new MazeImporter(config.maze_file());
        Maze maze = imported_maze.getMaze();
        MazePathChecker pathChecker = new MazePathChecker(maze, config.path_sequence());
        MazeSolver solver = new MazeSolver(maze);

    }
    
}
