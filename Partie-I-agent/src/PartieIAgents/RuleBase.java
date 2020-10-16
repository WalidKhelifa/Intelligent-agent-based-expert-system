package PartieIAgents;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Stack;
import java.util.Vector;
import javafx.scene.control.TextArea;

public class RuleBase {
    String name;
    Hashtable variableList;
    Clause[] clauseVarList;
    Vector ruleList;
    Vector conclusionVarList;
    Rule rulePtr;
    Clause clausePtr;
    Stack goalClauseStack;
    static TextArea textArea1;

    public void setDisplay(TextArea txtArea) {
        textArea1 = txtArea;
    }

    RuleBase(String Name) {
        this.name = Name;
    }

    public static void appendText(String text) {
        textArea1.appendText(text);
    }

    public void displayVariables(TextArea textArea) {
        Enumeration enume = this.variableList.elements();

        while(enume.hasMoreElements()) {
            RuleVariable temp = (RuleVariable)enume.nextElement();
            if(temp.name.equals("modele") || !temp.value.equals(""))
            {
            textArea.appendText("\n" + temp.name + " value = " + temp.value);
            }
            
        }

    }

    public void displayRules(TextArea textArea) {
        textArea.appendText("\n" + this.name + " Rule Base: \n");
        Enumeration enume = this.ruleList.elements();

        while(enume.hasMoreElements()) {
            Rule temp = (Rule)enume.nextElement();
            temp.display(textArea);
        }

    }

    public void displayConflictSet(Vector ruleSet) {
        textArea1.appendText("\n -- Rules in conflict set:\n");
        Enumeration enume = ruleSet.elements();

        while(enume.hasMoreElements()) {
            Rule temp = (Rule)enume.nextElement();
            textArea1.appendText(temp.name + "(" + temp.numAntecedents() + "), ");
        }

    }

    public void reset() {
        textArea1.appendText("\n Setting all variables to null");
        Enumeration enume = this.variableList.elements();

        while(enume.hasMoreElements()) {
            RuleVariable temp = (RuleVariable)enume.nextElement();
            temp.setValue((String)null);
        }

    }

    public void forwardChain() {
        new Vector();

        for(Vector conflictRuleSet = this.match(true); conflictRuleSet.size() > 0; conflictRuleSet = this.match(false)) {
            Rule selected = this.selectRule(conflictRuleSet);
            selected.fire();
        }

    }

    public Vector match(boolean test) {
        Vector matchList = new Vector();
        Enumeration enume = this.ruleList.elements();

        while(enume.hasMoreElements()) {
            Rule testRule = (Rule)enume.nextElement();
            if (test) {
                testRule.check();
            }

            if (testRule.truth != null && testRule.truth && !testRule.fired) {
                matchList.addElement(testRule);
            }
        }

        this.displayConflictSet(matchList);
        return matchList;
    }

    public Rule selectRule(Vector ruleSet) {
        Enumeration enume = ruleSet.elements();
        Rule bestRule = (Rule)enume.nextElement();
        long max = bestRule.numAntecedents();

        while(enume.hasMoreElements()) {
            Rule nextRule = (Rule)enume.nextElement();
            long numClauses;
            if ((numClauses = nextRule.numAntecedents()) > max) {
                max = numClauses;
                bestRule = nextRule;
            }
        }

        return bestRule;
    }

    public void backwardChain(String goalVarName) {
        RuleVariable goalVar = (RuleVariable)this.variableList.get(goalVarName);
        Enumeration goalClauses = goalVar.clauseRefs.elements();

        while(goalClauses.hasMoreElements()) {
            Clause goalClause = (Clause)goalClauses.nextElement();
            if (goalClause.consequent) {
                this.goalClauseStack.push(goalClause);
                Rule goalRule = goalClause.getRule();
                Boolean ruleTruth = goalRule.backChain();
                if (ruleTruth == null) {
                    textArea1.appendText("\nRule " + goalRule.name + " is null, can't determine truth value.");
                } else if (ruleTruth) {
                    goalVar.setValue(goalClause.rhs);
                    goalVar.setRuleName(goalRule.name);
                    this.goalClauseStack.pop();
                    textArea1.appendText("\nRule " + goalRule.name + " is true, setting " + goalVar.name + ": =" + goalVar.value);
                    if (this.goalClauseStack.empty()) {
                        textArea1.appendText("\n +++ Found Solution for goal: " + goalVar.name);
                        break;
                    }
                } else {
                    this.goalClauseStack.pop();
                    textArea1.appendText("\nRule " + goalRule.name + " is false, can�t set " + goalVar.name);
                }
            }
        }

        if (goalVar.value == null) {
            textArea1.appendText("\n +++ Could Not Find Solution for goal: " + goalVar.name);
        }

    }
}
