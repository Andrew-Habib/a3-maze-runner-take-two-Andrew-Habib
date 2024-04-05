package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Configuration {

    private static final Logger logger = LogManager.getLogger();
    
    private String maze_file = "./examples/straight.maz.txt";
    private String path_sequence = "";
    private String method = "bfs";
    private String baseline = "righthand";
    private boolean path_given = false;
    private boolean base_given = false;

    public String getMazeFile () { return this.maze_file; }
    public String getPathSequence () { return this.path_sequence; }
    public String getMethod () { return this.method; }
    public String getBaseline() { return this.baseline; }
    public boolean pathGiven () { return this.path_given; }
    public boolean baselineGiven() { return this.base_given; }

    public void processInput(String[] args) {

        logger.info("** Starting Maze Runner");
        logger.info("**** Reading Command-Line Arguments");

        Options options = new Options();
        options.addOption("i", "input", true, "Maze Filename");
        options.addOption("p", "path", true, "Path Sequence");
        options.addOption("m", "method", true, "Method");
        options.addOption("b", "baseline", true, "Baseline");

        CommandLineParser parser = new DefaultParser();

        try {

            CommandLine cmd = parser.parse(options, args);
            String file = cmd.getOptionValue("i", "./examples/straight.maz.txt");
            String path = cmd.getOptionValue("p", "");
            String method = cmd.getOptionValue("method", "bfs");
            String baseline = cmd.getOptionValue("baseline", "righthand");

            this.path_given = false;
            this.maze_file = file;
            this.method = method;
            this.path_sequence = path;
            this.baseline = baseline;
            this.path_given = cmd.hasOption('p');
            this.base_given = cmd.hasOption('b');
            
        } catch(Exception e) {
            
            logger.error("/!\\ An error has occured while processing input /!\\");

        }       

    }

}