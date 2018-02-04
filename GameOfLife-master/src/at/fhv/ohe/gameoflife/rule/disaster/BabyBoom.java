package at.fhv.ohe.gameoflife.rule.disaster;

import at.fhv.ohe.gameoflife.map.Cell;
import at.fhv.ohe.gameoflife.map.CellState;
import at.fhv.ohe.gameoflife.rule.IRule;

import java.util.Random;

public class BabyBoom implements IRule {
    private Random _random;
    private float _birthRate;

    public BabyBoom(float birthRate) {
        _random = new Random();
        _birthRate = birthRate;
    }

    @Override
    public CellState doRule(Cell gameCell) {
        if (gameCell.getState() == CellState.DEAD) {
            if (_random.nextFloat() > _birthRate) {
                return CellState.DEAD;
            }
        }
        return CellState.LIFE;
    }
}
