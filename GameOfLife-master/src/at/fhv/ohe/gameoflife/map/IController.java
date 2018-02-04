package at.fhv.ohe.gameoflife.map;

import at.fhv.ohe.gameoflife.rule.IRule;

import java.util.Collection;

public interface IController {

    Collection<Cell> getCells();

    /**
     * Create a new World full of cells
     *
     * @param sizeX - The size in x direction of the game field
     * @param sizeY - The size in y direction of the game field
     */
    void createNewWorld(int sizeX, int sizeY);

    void alterCellsBy(int generations);

    void alterCells();

    void addDisaster(IRule disaster);

}
