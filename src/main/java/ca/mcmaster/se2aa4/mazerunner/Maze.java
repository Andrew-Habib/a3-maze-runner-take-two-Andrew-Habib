package ca.mcmaster.se2aa4.mazerunner;

public class Maze {

    private String[][] tiles = new String[5][5];

    public int getWidth() { return 5; }

    public int getHeight() { return 5; }

    public String[][] getTiles() { return this.tiles; }

    public Maze() {
        this.tiles = new String[][] {
            {"#", "#", "#", "#", "#"}, 
            {"#", "#", "#", "#", "#"}, 
            {" ", " ", " ", " ", " "}, 
            {"#", "#", "#", "#", "#"}, 
            {"#", "#", "#", "#", "#"}
        };
    }

}
