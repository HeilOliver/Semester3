package at.fhv.ohe.cheesequalitycontroll;

public class QualityControl {
    private int _holeCount;
    private int _maxHoleSize;

    public int getHoleCount() {
        return _holeCount;
    }

    public int getMaxHoleSize() {
        return _maxHoleSize;
    }

    public void checkCheese(Cheese cheese) {
        _holeCount = 0;
        _maxHoleSize = 0;
        int height = cheese.getHeight();
        int width = cheese.getWidth();
        ICheeseIterator cheeseLookUp = cheese.getIterator();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (cheeseLookUp.isInHole(i,j)) {
                    int count = searchHole(i, j, Direction.NORTH, cheeseLookUp);
                    _holeCount++;
                    _maxHoleSize = (_maxHoleSize > count) ? _maxHoleSize : count;
                }
            }
        }
    }

    private Direction searchNextPos(int x, int y, Direction startDir, ICheeseIterator look) {
        Direction neighbour = startDir.getLastNeighbour();
        do {
            if (look.isInHole(x + neighbour.getX(), y + neighbour.getY())) {
                return neighbour;
            }
            neighbour = neighbour.getNextNeighbour();

        }while (neighbour != startDir.getLastNeighbour());
        return null;
    }

    private int searchHole(int x, int y, Direction startDir, ICheeseIterator look) {
        if (startDir == null) {
            return 0;
        }
        Direction nextPos = searchNextPos(x, y, startDir, look);

        return searchHole(x + ((nextPos == null) ? 0 : nextPos.getX()),
                y + ((nextPos == null) ? 0 : nextPos.getY()),
                nextPos,
                look) + 1;
    }
}
