package at.fhv.ohe.gameoflife.rule.disaster;

import at.fhv.ohe.gameoflife.map.Cell;
import at.fhv.ohe.gameoflife.map.CellState;
import at.fhv.ohe.gameoflife.rule.IRule;

public class DeathByAge implements IRule {
    @Override
    public CellState doRule(Cell gameCell) {
        if (gameCell.getAge() > 5) {
            return CellState.DEAD;
        }
        return CellState.LIFE;
    }
}
