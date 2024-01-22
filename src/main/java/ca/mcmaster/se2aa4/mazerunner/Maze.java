package ca.mcmaster.se2aa4.mazerunner;

public class Maze {

    private String[][] tiles = new String[5][5];

    public Maze() {
        this.tiles = new String[][] {
            {"#", "#", "#", "#", "#"}, 
            {"#", "#", "#", "#", "#"}, 
            {" ", " ", " ", " ", " "}, 
            {"#", "#", "#", "#", "#"}, 
            {"#", "#", "#", "#", "#"}
        };
    }

    public int startX() { return 0; } 

    public int startY() { return 2; } 

    public int endX() { return 4; } 

    public int endY() { return 2; } 

    public int getWidth() { return 5; }

    public int getHeight() { return 5; }

    public String[][] getTiles() { return this.tiles; }

}
