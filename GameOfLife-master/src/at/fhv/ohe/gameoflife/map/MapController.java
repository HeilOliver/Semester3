package at.fhv.ohe.gameoflife.map;

import at.fhv.ohe.gameoflife.rule.IRule;
import at.fhv.ohe.gameoflife.rule.IRuleListener;
import at.fhv.ohe.gameoflife.rule.IRuleSupplier;
import at.fhv.ohe.gameoflife.rule.create.CellCreatorMK1;
import at.fhv.ohe.gameoflife.rule.create.CreateWorld;
import at.fhv.ohe.gameoflife.rule.create.ICellCreator;
import at.fhv.ohe.gameoflife.rule.disaster.RandomDead;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MapController implements IRuleSupplier {
    private static final long WAITING_TIME = 4000;   // Waiting Time for Threads to Finish
    private static final int THREADPOOLCOUNT = 12;   // Do it single threaded fore some reasons :(

    private List<IRuleListener> _ruleListenersTransact;
    private List<IRuleListener> _ruleListeners;
    private Map<Integer, Map<Integer, Cell>> _cellMap;
    private boolean _transact;


    public void alterZells(IRule[] rules) {
        sendRuleSet(rules);
        applyRules();
    }

    public void startNewWorld(int sizeX, int sizeY) {
        startNewWorld(sizeX, sizeY, new CellCreatorMK1(this, this));
    }

    public void startNewWorld(int sizeX, int sizeY, ICellCreator cellCreator) {
        initialize();
        createWorld(sizeX, sizeY, cellCreator);
    }

    public List<Cell> toList() {
        List<Cell> cells = new ArrayList<>();
        Collection<Map<Integer, Cell>> values = _cellMap.values();
        Iterator<Map<Integer, Cell>> iterator = values.iterator();
        iterator.forEachRemaining((k) -> cells.addAll(k.values()));
        return cells;
    }

    private void createWorld(int sizeX, int sizeY, ICellCreator cellCreator) {
        new Cell(cellCreator, new MapLocation(0, 0));

        CreateWorld createWorld = new CreateWorld(sizeX, sizeY);
        IRule[] ruleSet = {createWorld};
        while (_ruleListeners.size() < ((sizeX + 2) * (sizeY + 2))) {
            sendRuleSet(ruleSet);
            applyRules();
        }
        ruleSet = new IRule[]{new RandomDead(0.8f)};
        sendRuleSet(ruleSet);
        applyRules();
    }

    private void initialize() {
        _cellMap = new HashMap<>();
        _ruleListeners = new ArrayList<>();
        _ruleListenersTransact = null;
    }

    synchronized Map<Directions, Cell> registerCell(MapLocation location, Cell cell) {
        _cellMap.computeIfAbsent(location.getX(), v -> new HashMap<>());
        _cellMap.get(location.getX()).put(location.getY(), cell);

        return getCellNeighbors(location);
    }

    private Map<Directions, Cell> getCellNeighbors(MapLocation location) {

        Map<Directions, Cell> neighbors = new EnumMap<>(Directions.class);

        // Generate a list of all Neighbours out of the HashMap.
        for (Directions directions : Directions.values()) { // TODO Use Location Class in HashMap as Mapping object
            Map<Integer, Cell> cellMap = _cellMap.get(location.getX() + directions.getX());
            Cell cell = null;

            if (cellMap != null) {
                cell = cellMap.get(location.getY() + directions.getY());
            }
            if (cell != null) {
                neighbors.put(directions, cell);
            }
        }

        return neighbors;
    }

    //region RuleSupplier
    private void sendRuleSet(IRule[] rules) {
        startTransaction();

        // MultiThreaded
        ExecutorService executor = Executors.newFixedThreadPool(THREADPOOLCOUNT);
        for (IRuleListener ruleListener : _ruleListeners) {
            executor.execute(() -> {
                for (IRule rule : rules) {
                    ruleListener.doRuleCheck(rule);
                }
            });
        }
        executor.shutdown();
        try {
            executor.awaitTermination(WAITING_TIME, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            throw new ThreadDeath();    // Waiting for the given time until exception is thrown
        }
    }

    private void applyRules() {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        for (IRuleListener ruleListener : _ruleListeners) {
            executor.execute(ruleListener::applyRule);
        }
        executor.shutdown();
        try {
            executor.awaitTermination(WAITING_TIME, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            throw new ThreadDeath();    // Waiting for the given time until exception is thrown
        }

        endTransaction();
    }

    private void startTransaction() {
        if (!_transact) {
            _ruleListenersTransact = new ArrayList<>();
            _transact = true;
        }
    }

    private void endTransaction() {
        _ruleListeners.addAll(_ruleListenersTransact);
        _ruleListenersTransact = null;
        _transact = false;
    }

    @Override
    public void addRuleListener(IRuleListener gameCell) {
        if (_transact) {
            _ruleListenersTransact.add(gameCell);
        } else {
            _ruleListeners.add(gameCell);
        }
    }
    //endregion
}
