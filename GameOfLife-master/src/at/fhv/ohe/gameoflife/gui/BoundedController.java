package at.fhv.ohe.gameoflife.gui;

import at.fhv.ohe.gameoflife.map.MapController;
import at.fhv.ohe.gameoflife.rule.IRule;
import at.fhv.ohe.gameoflife.rule.continuous.NeverLifeOutside;
import at.fhv.ohe.gameoflife.rule.create.ICellCreator;

public class BoundedController extends MapController {
    IRule _boundredRule;


    @Override
    public void startNewWorld(int sizeX, int sizeY, ICellCreator cellCreator) {
        super.startNewWorld(sizeX, sizeY, cellCreator);
        _boundredRule = new NeverLifeOutside(sizeX, sizeY);
    }

    @Override
    public void alterZells(IRule[] rules) {
        IRule[] newRules = new IRule[rules.length + 1];
        System.arraycopy(rules, 0, newRules, 0, rules.length);
        newRules[newRules.length - 1] = _boundredRule;
        super.alterZells(newRules);
    }
}
