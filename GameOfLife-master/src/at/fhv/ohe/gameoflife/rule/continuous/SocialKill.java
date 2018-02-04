package at.fhv.ohe.gameoflife.rule.continuous;

import at.fhv.ohe.gameoflife.map.Cell;
import at.fhv.ohe.gameoflife.map.CellState;
import at.fhv.ohe.gameoflife.map.Directions;
import at.fhv.ohe.gameoflife.rule.IRule;

import java.util.Map;

public class SocialKill implements IRule {


    @Override
    public CellState doRule(Cell gameCell) {
        if (gameCell.getState() == CellState.LIFE) {
            int count = 0;
            for (Map.Entry<Directions, Cell> entry : gameCell.getNeighbors().entrySet()) {
                if (entry.getValue().getState() == CellState.LIFE) {
                    count++;
                }
            }

            if (count <= 1 || count > 3) {
                return CellState.DEAD;
            }
        }
        return CellState.LIFE;
    }
}
