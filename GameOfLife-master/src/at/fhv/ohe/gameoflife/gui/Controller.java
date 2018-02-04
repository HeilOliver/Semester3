package at.fhv.ohe.gameoflife.gui;

import at.fhv.ohe.gameoflife.map.Cell;
import at.fhv.ohe.gameoflife.map.IController;
import at.fhv.ohe.gameoflife.map.MapController;
import at.fhv.ohe.gameoflife.rule.IRule;
import at.fhv.ohe.gameoflife.rule.continuous.ResurrectCells;
import at.fhv.ohe.gameoflife.rule.continuous.SocialKill;
import at.fhv.ohe.gameoflife.rule.disaster.BabyBoom;
import at.fhv.ohe.gameoflife.rule.disaster.DeathByAge;
import at.fhv.ohe.gameoflife.rule.disaster.RandomDead;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controller implements IController {
    private MapController _mapController;
    private IRule[] _rules;
    private Map<String, IRule> _disasters;

    Controller() {
        _rules = new IRule[]{new ResurrectCells(), new SocialKill()};
        _mapController = new BoundedController();
        _disasters = new HashMap<>();
        _disasters.put("BabyBooom", new BabyBoom(0.6f));
        _disasters.put("Random Death", new RandomDead(0.6f));
        _disasters.put("Death by Age", new DeathByAge());
    }

    public Map<String, IRule> getDisasters() {
        return _disasters;
    }

    @Override
    public List<Cell> getCells() {
        return _mapController.toList();
    }


    @Override
    public void createNewWorld(int sizeX, int sizeY) {
        _mapController.startNewWorld(sizeX, sizeY);
    }

    @Override
    public void alterCellsBy(int generations) {
        for (int i = 0; i < generations; i++) {
            alterCells();
        }
    }

    @Override
    public void alterCells() {
        _mapController.alterZells(_rules);
    }

    @Override
    public void addDisaster(IRule disaster) {
        _mapController.alterZells(new IRule[]{disaster});
    }
}
