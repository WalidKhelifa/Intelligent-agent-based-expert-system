package PartieIAgents;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.util.Enumeration;
import java.util.Vector;

public class RuleVariable extends Variable {
    Vector clauseRefs = new Vector();
    String promptText;
    String ruleName;

    public RuleVariable(String Name) {
        super(Name);
    }

    void setValue(String val) {
        this.value = val;
        this.updateClauses();
    }

    String askUser() {
        return null;
    }

    void addClauseRef(Clause ref) {
        this.clauseRefs.addElement(ref);
    }

    void updateClauses() {
        Enumeration enume = this.clauseRefs.elements();

        while(enume.hasMoreElements()) {
            ((Clause)enume.nextElement()).check();
        }

    }

    void setRuleName(String rname) {
        this.ruleName = rname;
    }

    void setPromptText(String text) {
        this.promptText = text;
    }

    public void computeStatistics(String inValue) {
    }

    public int normalize(String inValue, float[] outArray, int inx) {
        return inx;
    }
}
