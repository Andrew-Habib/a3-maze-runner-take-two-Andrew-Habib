package ca.mcmaster.se2aa4.mazerunner;

public abstract class MazeSolver {

    protected Maze maze;
    protected Runner runner;

    public MazeSolver(Maze maze) {
        this.maze = maze;
        this.runner = new Runner(0, 0, Direction.EAST);
    }

    protected void setStartLeft() {

        for (int row = 0; row < this.maze.getHeight() - 1; row++) {
            if (this.maze.getTileAt(0, row) == Tile.EMPTY) {
                this.runner.setXLocation(0);
                this.runner.setYLocation(row);
                this.runner.setDirection(Direction.EAST);
            }
        }
        
    }

    protected abstract String algorithm();

    protected String canonicalForm(String path) {

        String canonical_path = "";
        char before = path.charAt(0);
        int num = 1;


        for (int i = 1; i < path.length(); i++) {
            if (path.charAt(i) == before && i < path.length()) {
                num = num + 1;
                if (i == path.length() - 1) {
                    canonical_path = canonical_path + String.valueOf(num) + before;
                }
            } else {
                if (num > 1) {
                    canonical_path = canonical_path + String.valueOf(num);
                }
                canonical_path = canonical_path + before + ' ';
                before = path.charAt(i);
                num = 1;
            }
        } 

        return canonical_path;

    }

    public void solve() {

        this.setStartLeft();
        String path = this.algorithm();
        String canonical_path = this.canonicalForm(path);
        System.out.println(canonical_path);

    }      

}
