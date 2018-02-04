package at.fhv.ohe.cheesequalitycontroll;

import java.util.HashSet;
import java.util.Set;

public class Cheese {

    private boolean[][] _cheeseArr;

    public Cheese(boolean[][] cheeseArr) {
        _cheeseArr = cheeseArr;
    }

    public int getWidth() {
        return _cheeseArr.length;
    }

    public int getHeight() {
        return _cheeseArr[0].length;
    }

    public ICheeseIterator getIterator() {
        return new CheeseIterator(this);
    }

    private boolean isHole(int x, int y) {
        return x >= 0 && x < getWidth()
                && y >= 0 && y < getHeight()
                && _cheeseArr[x][y];
    }

    final class CheeseIterator implements ICheeseIterator {
        Cheese _cheese;
        Set<Point> _alreadyLookedUp;

        private CheeseIterator(Cheese cheese) {
            _cheese = cheese;
            _alreadyLookedUp = new HashSet<>();
        }

        @Override
        public boolean isInHole(int x, int y) {
            if (_cheese.isHole(x,y)) {
                Point point = new Point(x, y);
                if (!_alreadyLookedUp.contains(point)) {
                    _alreadyLookedUp.add(point);
                    return true;
                }
            }
            return false;
        }

        final class Point {
            int x;
            int y;

            Point(int x, int y) {
                this.x = x;
                this.y = y;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;

                Point point = (Point) o;

                return x == point.x && y == point.y;
            }

            @Override
            public int hashCode() {
                return (((x+y)*(x+y+1))/2)+y;
            }
        }
    }
}
