package at.fhv.ohe.gameoflife.map;

public class MapLocation {
    private static final int HASHPRIME = 32;
    private int _x;
    private int _y;

    private MapLocation() {
        this(0, 0);
    }

    MapLocation(int x, int y) {
        _x = x;
        _y = y;
    }

    public int getX() {
        return _x;
    }

    public void setX(int x) {
        _x = x;
    }

    public int getY() {
        return _y;
    }

    public void setY(int y) {
        _y = y;
    }

    @Override
    public int hashCode() {
        return _x * _y * HASHPRIME;
    }

    @Override
    public boolean equals(Object obj) {
        MapLocation location = ((MapLocation) obj);
        return location._y == _y && location._x == _x;
    }

    public MapLocation copy() {
        return new MapLocation(_x, _y);
    }
}
