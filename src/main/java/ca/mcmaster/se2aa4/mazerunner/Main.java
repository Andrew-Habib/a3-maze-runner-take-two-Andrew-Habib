package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.Level;

public class Main {

    private static final Logger logger = LogManager.getLogger();
    
    public static void main(String[] args) {

        Configurator.setAllLevels(LogManager.getRootLogger().getName(), Level.ERROR);
        logger.info("Execution Begins");

        Configuration config = new Configuration();
        config.processInput(args);
        MazeImporter imported_maze = new MazeImporter(config.maze_file());
        Maze maze = imported_maze.getMaze();
        MazePathChecker pathChecker = new MazePathChecker(maze, config.path_sequence());
        MazeSolver solver = new MazeSolver(maze);

    }
    
}
