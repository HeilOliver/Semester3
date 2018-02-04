package at.fhv.ohe.gameoflife.rule.create;

import at.fhv.ohe.gameoflife.map.Cell;
import at.fhv.ohe.gameoflife.map.Directions;
import at.fhv.ohe.gameoflife.map.MapController;
import at.fhv.ohe.gameoflife.map.MapLocation;
import at.fhv.ohe.gameoflife.rule.IRuleSupplier;

public class CellCreatorMK1 implements ICellCreator {
    private MapController _controller;
    private IRuleSupplier _ruleSupplier;

    public CellCreatorMK1(MapController controller, IRuleSupplier ruleSupplier) {
        _controller = controller;
        _ruleSupplier = ruleSupplier;
    }

    @Override
    public Cell createCell(MapLocation location, Directions k) {
        MapLocation mapLocation = location.copy();
        mapLocation.setX(mapLocation.getX() + k.getX());
        mapLocation.setY(mapLocation.getY() + k.getY());
        return new Cell(this, mapLocation);
    }

    @Override
    public MapController getController() {
        return _controller;
    }

    @Override
    public IRuleSupplier getRuleSender() {
        return _ruleSupplier;
    }
}
