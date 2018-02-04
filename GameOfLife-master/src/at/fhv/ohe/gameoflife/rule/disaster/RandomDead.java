package at.fhv.ohe.gameoflife.rule.disaster;

import at.fhv.ohe.gameoflife.map.Cell;
import at.fhv.ohe.gameoflife.map.CellState;
import at.fhv.ohe.gameoflife.rule.IRule;

import java.util.Random;

public class RandomDead implements IRule {
    private Random _random;
    private float _deathRate;

    public RandomDead(float deathRate) {
        _random = new Random();
        _deathRate = deathRate;
    }

    @Override
    public CellState doRule(Cell gameCell) {
        if (gameCell.getState() == CellState.LIFE) {
            if (_random.nextFloat() > _deathRate) {
                return CellState.LIFE;
            }
        }
        return CellState.DEAD;
    }
}
