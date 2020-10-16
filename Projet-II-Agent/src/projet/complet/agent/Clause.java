package projet.complet.agent;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.util.Vector;

public class Clause {
    Vector ruleRefs;
    RuleVariable lhs;
    String rhs;
    Condition cond;
    Boolean consequent;
    Boolean truth;

    Clause(RuleVariable Lhs, Condition Cond, String Rhs) {
        this.lhs = Lhs;
        this.cond = Cond;
        this.rhs = Rhs;
        this.lhs.addClauseRef(this);
        this.ruleRefs = new Vector();
        this.truth = null;
        this.consequent = new Boolean(false);
    }

    void addRuleRef(Rule ref) {
        this.ruleRefs.addElement(ref);
    }

    Boolean check() {
        if (this.consequent) {
            return null;
        } else if (this.lhs.value == null) {
            return this.truth = null;
        } else {
            switch(this.cond.index) {
            case 1:
                this.truth = new Boolean(this.lhs.value.equals(this.rhs));
                break;
            case 2:
                this.truth = new Boolean(this.lhs.value.compareTo(this.rhs) > 0);
                break;
            case 3:
                this.truth = new Boolean(this.lhs.value.compareTo(this.rhs) < 0);
                break;
            case 4:
                this.truth = new Boolean(this.lhs.value.compareTo(this.rhs) != 0);
            }

            return this.truth;
        }
    }

    void isConsequent() {
        this.consequent = new Boolean(true);
    }

    Rule getRule() {
        return this.consequent ? (Rule)this.ruleRefs.firstElement() : null;
    }
}
