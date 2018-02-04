package at.fhv.ohe.gameoflife.rule;

public interface IRuleListener {
    void applyRule();

    void doRuleCheck(IRule rule);
}
