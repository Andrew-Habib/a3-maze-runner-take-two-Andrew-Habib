package ca.mcmaster.se2aa4.mazerunner;

public enum Direction {
    NORTH(0, 1),
    EAST(1, 0),
    SOUTH(0, -1),
    WEST(-1, 0);
    private final int x;
    private final int y;
    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int unitX() {
        return this.x;
    }
    public int unitY() {
        return this.y;
    }
    public static Direction getLeft(Direction dir) {
        return Direction.values()[(dir.ordinal() + 3) % 4];
    }
    public static Direction getRight(Direction dir) {
        return Direction.values()[(dir.ordinal() + 1) % 4];
    }
}
