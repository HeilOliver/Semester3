package at.fhv.ohe.gameoflife.rule.create;

import at.fhv.ohe.gameoflife.map.Cell;
import at.fhv.ohe.gameoflife.map.Directions;
import at.fhv.ohe.gameoflife.map.MapController;
import at.fhv.ohe.gameoflife.map.MapLocation;
import at.fhv.ohe.gameoflife.rule.IRuleSupplier;

public interface ICellCreator {

    Cell createCell(MapLocation location, Directions k);

    MapController getController();

    IRuleSupplier getRuleSender();
}
