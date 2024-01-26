package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;

public class Maze {

    private Tile[][] layout;

    public Maze(Tile[][] tiles) {
        this.layout = tiles;
    }
 
    public int getWidth() { 
        return this.layout[0].length;
    }

    public int getHeight() { 
        return this.layout.length;
    }

    public Tile getTileAt(int x, int y) {
        return this.layout[this.getHeight() - y - 1][x];
    }

}
