package ca.mcmaster.se2aa4.mazerunner;

public class Runner {

    private int x;
    private int y;
    private Direction direction;

    public Runner(int x, int y, Direction dir) {
        this.x = x;
        this.y = y;
        this.direction = dir;
    }

    public void runForward() { 
        if (this.direction == Direction.NORTH) {
            this.y = this.y + 1;
        } else if (this.direction == Direction.SOUTH) {
            this.y = this.y - 1;
        } else if (this.direction == Direction.EAST) {
            this.x = this.x + 1;
        } else {
            this.x = this.x - 1;
        }
    }

    public void turnLeft() {
        this.direction = Direction.getLeft(this.direction);
    }

    public void turnRight() {
        this.direction = Direction.getRight(this.direction);
    }

    public void setXLocation(int x) {
        this.x = x;
    }

    public void setYLocation(int y) {
        this.y = y;
    }

    public void setDirection(Direction dir) {
        this.direction = dir;
    }

    public int getXLocation() {
        return this.x;
    }

    public int getYLocation() {
        return this.y;
    }

    public Direction getDirection() {
        return this.direction;
    }

}
