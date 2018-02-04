package at.fhv.ohe.gameoflife.rule;

import at.fhv.ohe.gameoflife.map.Cell;
import at.fhv.ohe.gameoflife.map.CellState;

public interface IRule {
    CellState doRule(Cell gameCell);
}
