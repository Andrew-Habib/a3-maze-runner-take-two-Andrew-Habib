package ca.mcmaster.se2aa4.mazerunner;

public enum Tile {

    WALL('#'), 
    EMPTY(' ');

    private final char tile_char;

    Tile(char tile) { 
        this.tile_char = tile;
    }

    public char getTileChar() { 
        return this.tile_char; 
    }

}
