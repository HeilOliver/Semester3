package at.fhv.ohe.gameoflife.map;

import at.fhv.ohe.gameoflife.rule.IRule;
import at.fhv.ohe.gameoflife.rule.IRuleListener;
import at.fhv.ohe.gameoflife.rule.create.ICellCreator;

import java.util.Collections;
import java.util.Map;

public class Cell implements IRuleListener {
    private final long _uniqueCellNumber;       // A unique number for every cell
    private static long _cellCount;             // The next Cell number

    private ICellCreator _creator;              // creates my type of cells
    private Map<Directions, Cell> _neighbors; // how is living next to me?
    private CellState _state;                  // my state
    private CellState _nextState;              // my state in the next generation

    private int _age;                           // my generation age
    private MapLocation _location;              // The Location of the Cell

    public Cell(ICellCreator creator, MapLocation location) {
        // Initialize Maps, Lists and variables
        _state = CellState.DEAD;
        _age = 0;
        _creator = creator;
        _uniqueCellNumber = _cellCount++;
        _location = location;

        // Now I listen to rules
        _creator.getRuleSender().addRuleListener(this);

        // Add Me and my Neighbors
        _neighbors = creator.getController().registerCell(location, this);
        _neighbors.forEach((k, v) -> v.addMe(this, Directions.getReverse(k)));
    }

    private void checkConsistence() {
        if (_nextState == CellState.LIFE && _state == CellState.DEAD) {       // get the transition from dead to life
            if (_neighbors.size() < Directions.getValueCount()) {
                for (Directions directions : Directions.values()) {
                    _neighbors.computeIfAbsent(directions, k -> _creator.createCell(_location, directions));
                    // Create missing Neighbors until i have 8
                }
            }
        }
    }

    private void addMe(Cell cell, Directions directions) {
        _neighbors.put(directions, cell);
    }

    //region public methods
    public Map<Directions, Cell> getNeighbors() {
        return Collections.unmodifiableMap(_neighbors);
    }

    public CellState getState() {
        return _state;
    }
    //endregion

    //region interface IRuleListener
    @Override
    public void applyRule() {
        checkConsistence();
        _state = _nextState;
        _nextState = null;

        if (_state == CellState.DEAD) {
            _age = 0;
        } else {
            _age++;
        }
    }

    @Override
    public void doRuleCheck(IRule rule) {
        CellState state = rule.doRule(this);   // TODO Performance Increase when Dead is save
        if (_nextState == CellState.DEAD) {
            return;
        }
        _nextState = state;     // TODO Save transfer state
    }
    //endregion


    @Override
    public String toString() {
        return "Cell-" + _uniqueCellNumber + " " +
                "{ Neighbors=" + _neighbors.size() +
                ", State=" + _state +
                ", NextState=" + _nextState +
                ", Age=" + _age +
                '}';
    }

    public MapLocation getLocation() {
        return _location;
    }

    public int getAge() {
        return _age;
    }
}
