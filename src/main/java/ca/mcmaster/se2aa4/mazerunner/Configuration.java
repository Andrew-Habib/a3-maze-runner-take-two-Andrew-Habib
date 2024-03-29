package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Configuration {

    private static final Logger logger = LogManager.getLogger();
    
    private String maze_file = "./examples/straight.maz.txt";
    private String path_sequence = "";
    private boolean path_given = false;

    public String getMazeFile () { return this.maze_file; }
    public String getPathSequence () { return this.path_sequence; }
    public boolean pathGiven () { return this.path_given; } 

    public void processInput(String[] args) {

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

            this.maze_file = file;

            if(cmd.hasOption('p')) {
                this.path_given = true;
                this.path_sequence = path;
            } else {
                this.path_given = false;
            }    
            
        } catch(Exception e) {
            
            logger.error("/!\\ An error has occured while processing input /!\\");

        }       

    }

}