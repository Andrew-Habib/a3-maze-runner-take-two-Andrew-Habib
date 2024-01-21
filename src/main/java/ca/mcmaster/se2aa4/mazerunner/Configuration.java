package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Configuration {

    private static final Logger logger = LogManager.getLogger();
    
    public String maze_file () { return "./examples/straight.maz.txt"; }

    public String path_sequence () { return "F"; }

    public static void processInput(String[] args) {

        logger.info("** Starting Maze Runner");
        logger.info("**** Reading Command-Line Arguments");

        Options options = new Options();
        options.addOption("i", "input", true, "Maze Filename");
        options.addOption("p", "path", true, "Path Sequence");

        CommandLineParser parser = new DefaultParser();

        try {

            CommandLine cmd = parser.parse(options, args);
            String file = cmd.getOptionValue("i", "./examples/straight.maz.txt");
            String path = cmd.getOptionValue("p", "");

            logger.info("**** Reading the maze from file " + file);
            BufferedReader reader = new BufferedReader(new FileReader(file));
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
            
            logger.error("/!\\ An error has occured /!\\");

        }

        logger.info("**** Computing path");
        logger.debug("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");

    }
}