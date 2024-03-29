package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class MazeImporter {

    private static final Logger logger = LogManager.getLogger();

    private Maze maze;
    private String maze_file;

    public Maze getMaze() { 
        return this.maze; 
    }

    public MazeImporter(String file) {
        this.maze_file = file;
    }

    public void importMaze() {

        try {

            logger.info("**** Reading and importing the maze from file");
            BufferedReader reader = new BufferedReader(new FileReader(this.maze_file));
            this.mazeBuilder(reader);

        } catch(Exception e) {
            
            logger.error("/!\\ An error has occured while importing. Please Check the file exists /!\\");

        }

        logger.info("**** Computing path");
        logger.debug("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");

    }

    private void mazeBuilder(BufferedReader reader) throws IOException {

        ArrayList<Tile> row_maze = new ArrayList<>();
        ArrayList<Tile[]> arr_maze = new ArrayList<>();
        String line;

        while ((line = reader.readLine()) != null) {
            
            row_maze.clear();
            for (int idx = 0; idx < line.length(); idx++) {
                if (line.charAt(idx) == '#') {
                    row_maze.add(Tile.WALL);
                    logger.trace("WALL ");
                } else if (line.charAt(idx) == ' ') {
                    row_maze.add(Tile.EMPTY);
                    logger.trace("PASS ");
                }
            }
            arr_maze.add(row_maze.toArray(new Tile[0]));
            logger.info(System.lineSeparator());

        }

        this.maze = new Maze(arr_maze.toArray(new Tile[0][0]));

    }
    
}
