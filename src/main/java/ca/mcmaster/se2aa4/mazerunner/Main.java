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
        MazeImporter imported_maze = new MazeImporter(config.getMazeFile());
        imported_maze.importMaze();
        Maze maze = imported_maze.getMaze();

        EventManagerContext context = new EventManagerContext();

        if (config.pathGiven()) {
            context.changeState(new StateCheckPath());
        } else {
            context.changeState(new StateSolve());
        }
        context.handle(maze, config);

    }
    
}
