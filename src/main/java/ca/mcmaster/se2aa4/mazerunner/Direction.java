package ca.mcmaster.se2aa4.mazerunner;

public enum Direction {
    NORTH, EAST, SOUTH, WEST;
    public static Direction getLeft(Direction dir) {
        return Direction.values()[(dir.ordinal() + 3) % 4];
    }
    public static Direction getRight(Direction dir) {
        return Direction.values()[(dir.ordinal() + 1) % 4];
    }
}
