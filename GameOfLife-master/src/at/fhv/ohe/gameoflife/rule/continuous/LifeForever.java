package at.fhv.ohe.gameoflife.rule.continuous;

import at.fhv.ohe.gameoflife.map.Cell;
import at.fhv.ohe.gameoflife.map.CellState;
import at.fhv.ohe.gameoflife.rule.IRule;

public class LifeForever implements IRule {

    @Override
    public CellState doRule(Cell gameCell) {
        return CellState.LIFE;
    }
}
