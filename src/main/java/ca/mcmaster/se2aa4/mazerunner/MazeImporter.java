package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MazeImporter {

    private static final Logger logger = LogManager.getLogger();

    private Maze maze = new Maze();
    private String maze_file = "";

    public Maze getMaze() { return this.maze; }

    public MazeImporter(String file) {
        this.maze_file = file;
    }

    public void importMaze() {

        try {

            logger.info("**** Reading and importing the maze from file");
            BufferedReader reader = new BufferedReader(new FileReader(this.maze_file));
            String line;

            while ((line = reader.readLine()) != null) {
                for (int idx = 0; idx < line.length(); idx++) {
                    if (line.charAt(idx) == '#') {
                        logger.trace("WALL ");
                    } else if (line.charAt(idx) == ' ') {
                        logger.trace("PASS ");
                    }
                }
                logger.info(System.lineSeparator());
            }

        } catch(Exception e) {
            
            logger.error("/!\\ An error has occured while importing. Please Check the file exists /!\\");

        }

        logger.info("**** Computing path");
        logger.debug("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");

    }
    
}
