package at.fhv.ohe.gameoflife.rule.continuous;

import at.fhv.ohe.gameoflife.map.Cell;
import at.fhv.ohe.gameoflife.map.CellState;
import at.fhv.ohe.gameoflife.map.MapLocation;
import at.fhv.ohe.gameoflife.rule.IRule;

public class NeverLifeOutside implements IRule {
    private int _maxX;
    private int _maxY;
    private int _minX;
    private int _minY;

    public NeverLifeOutside(int x, int y) {
        if ((x < 1 || y < 1)) {
            throw new IllegalArgumentException();
        }

        _maxX = x / 2;
        _maxY = y / 2;

        _minX = getMin(x);
        _minY = getMin(y);
    }

    private int getMin(int val) {
        if (val % 2 == 0) {
            return ((val / 2) - 1) * -1;
        }
        return (val / 2) * -1;
    }


    @Override
    public CellState doRule(Cell gameCell) {
        MapLocation location = gameCell.getLocation();
        if (location.getX() > _maxX || location.getX() < _minX) {
            return CellState.DEAD;
        }

        if (location.getY() > _maxY || location.getY() < _minY) {
            return CellState.DEAD;
        }
        return CellState.LIFE;
    }
}
