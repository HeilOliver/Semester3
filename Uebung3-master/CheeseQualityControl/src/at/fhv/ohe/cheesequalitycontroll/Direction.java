package at.fhv.ohe.cheesequalitycontroll;

public enum Direction {
    NORTH(0, 0, 1),
    NORTHEAST(1, 1, 1),
    EAST(2, 1, 0),
    SOUTHEAST(3, 1, -1),
    SOUTH(4, 0, -1),
    SOUTHWEST(5, -1, -1),
    WEST(6, -1, 0),
    NORTHWEST(7, -1, 1);

    private static final int SIZE = Direction.values().length;    // TODO if this is working
    private final int _x;
    private final int _value;
    private final int _y;

    Direction(int value, int x, int y) {
        _x = x;
        _y = y;
        _value = value;
    }

    public static Direction getReverse(Direction direction) {
        return getByValue((direction.getValue() + 4) % 8);
    }

    private static Direction getByValue(int value) {
        switch (value) {
            case 0:
                return NORTH;
            case 1:
                return NORTHEAST;
            case 2:
                return EAST;
            case 3:
                return SOUTHEAST;
            case 4:
                return SOUTH;
            case 5:
                return SOUTHWEST;
            case 6:
                return WEST;
            case 7:
                return NORTHWEST;
            default:
                throw new IllegalArgumentException();
        }
    }

    public static int getValueCount() {
        return SIZE;
    }

    public int getX() {
        return _x;
    }

    public int getY() {
        return _y;
    }

    public int getValue() {
        return _value;
    }

    public Direction getNextNeighbour() {
        return getByValue((_value + 1) % SIZE);
    }

    public Direction getLastNeighbour() {
        return getByValue((_value + SIZE - 1) % SIZE);
    }
}
