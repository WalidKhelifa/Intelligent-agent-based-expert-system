package projet.complet.agent;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.util.Enumeration;
import java.util.Vector;
import javafx.scene.control.TextArea;

public class Rule {
    RuleBase rb;
    String name;
    static TextArea textArea1;
    Clause[] antecedents;
    Clause consequent;
    Boolean truth;
    boolean fired = false;

    Rule(RuleBase Rb, String Name, Clause lhs, Clause rhs) {
        this.rb = Rb;
        this.name = Name;
        this.antecedents = new Clause[1];
        this.antecedents[0] = lhs;
        lhs.addRuleRef(this);
        this.consequent = rhs;
        rhs.addRuleRef(this);
        rhs.isConsequent();
        this.rb.ruleList.addElement(this);
        this.truth = null;
    }
    

    Rule(RuleBase Rb, String Name, Clause lhs1, Clause lhs2, Clause rhs) {
        this.rb = Rb;
        this.name = Name;
        this.antecedents = new Clause[2];
        this.antecedents[0] = lhs1;
        lhs1.addRuleRef(this);
        this.antecedents[1] = lhs2;
        lhs2.addRuleRef(this);
        this.consequent = rhs;
        rhs.addRuleRef(this);
        rhs.isConsequent();
        this.rb.ruleList.addElement(this);
        this.truth = null;
    }
    
    
    
    Rule(RuleBase Rb, String Name, Clause lhs1, Clause lhs2,Clause lhs3, Clause rhs) {
        this.rb = Rb;
        this.name = Name;
        this.antecedents = new Clause[2];
        this.antecedents[0] = lhs1;
        lhs1.addRuleRef(this);
        this.antecedents[1] = lhs2;
        lhs2.addRuleRef(this);
        this.antecedents[2] = lhs3;
        lhs3.addRuleRef(this);
        this.consequent = rhs;
        rhs.addRuleRef(this);
        rhs.isConsequent();
        this.rb.ruleList.addElement(this);
        this.truth = null;
    }

    Rule(RuleBase Rb, String Name, Clause lhs1, Clause lhs2, Clause lhs3, Clause lhs4, Clause lhs5, Clause lhs6, Clause lhs7, Clause lhs8, Clause rhs) {
        this.rb = Rb;
        this.name = Name;
        this.antecedents = new Clause[8];
        this.antecedents[0] = lhs1;
        lhs1.addRuleRef(this);
        this.antecedents[1] = lhs2;
        lhs2.addRuleRef(this);
        this.antecedents[2] = lhs3;
        lhs3.addRuleRef(this);
        this.antecedents[3] = lhs4;
        lhs4.addRuleRef(this);
        this.antecedents[4] = lhs5;
        lhs5.addRuleRef(this);
        this.antecedents[5] = lhs6;
        lhs6.addRuleRef(this);
        this.antecedents[6] = lhs7;
        lhs7.addRuleRef(this);
        this.antecedents[7] = lhs8;
        lhs8.addRuleRef(this);
        this.consequent = rhs;
        rhs.addRuleRef(this);
        rhs.isConsequent();
        this.rb.ruleList.addElement(this);
        this.truth = null;
    }
    Rule(RuleBase Rb, String Name, Clause lhs1, Clause lhs2, Clause lhs3, Clause lhs4, Clause lhs5, Clause lhs6, Clause lhs7,  Clause rhs) {
        this.rb = Rb;
        this.name = Name;
        this.antecedents = new Clause[7];
        this.antecedents[0] = lhs1;
        lhs1.addRuleRef(this);
        this.antecedents[1] = lhs2;
        lhs2.addRuleRef(this);
        this.antecedents[2] = lhs3;
        lhs3.addRuleRef(this);
        this.antecedents[3] = lhs4;
        lhs4.addRuleRef(this);
        this.antecedents[4] = lhs5;
        lhs5.addRuleRef(this);
        this.antecedents[5] = lhs6;
        lhs6.addRuleRef(this);
        this.antecedents[6] = lhs7;
        lhs7.addRuleRef(this);
        this.consequent = rhs;
        rhs.addRuleRef(this);
        rhs.isConsequent();
        this.rb.ruleList.addElement(this);
        this.truth = null;
    }

    Rule(RuleBase Rb, String Name, Clause lhs1, Clause lhs2, Clause lhs3, Clause lhs4, Clause rhs) {
        this.rb = Rb;
        this.name = Name;
        this.antecedents = new Clause[4];
        this.antecedents[0] = lhs1;
        lhs1.addRuleRef(this);
        this.antecedents[1] = lhs2;
        lhs2.addRuleRef(this);
        this.antecedents[2] = lhs3;
        lhs3.addRuleRef(this);
        this.antecedents[3] = lhs4;
        lhs4.addRuleRef(this);
        this.consequent = rhs;
        rhs.addRuleRef(this);
        rhs.isConsequent();
        this.rb.ruleList.addElement(this);
        this.truth = null;
    }
    Rule(RuleBase Rb, String Name, Clause lhs1, Clause lhs2, Clause lhs3, Clause lhs4,Clause lhs5, Clause rhs) {
        this.rb = Rb;
        this.name = Name;
        this.antecedents = new Clause[4];
        this.antecedents[0] = lhs1;
        lhs1.addRuleRef(this);
        this.antecedents[1] = lhs2;
        lhs2.addRuleRef(this);
        this.antecedents[2] = lhs3;
        lhs3.addRuleRef(this);
        this.antecedents[3] = lhs4;
        lhs4.addRuleRef(this);
        this.antecedents[4] = lhs5;
        lhs5.addRuleRef(this);
        this.consequent = rhs;
        rhs.addRuleRef(this);
        rhs.isConsequent();
        this.rb.ruleList.addElement(this);
        this.truth = null;
    }
    Rule(RuleBase Rb, String Name, Clause lhs1, Clause lhs2, Clause lhs3, Clause lhs4,Clause lhs5,Clause lhs6, Clause rhs) {
        this.rb = Rb;
        this.name = Name;
        this.antecedents = new Clause[4];
        this.antecedents[0] = lhs1;
        lhs1.addRuleRef(this);
        this.antecedents[1] = lhs2;
        lhs2.addRuleRef(this);
        this.antecedents[2] = lhs3;
        lhs3.addRuleRef(this);
        this.antecedents[3] = lhs4;
        lhs4.addRuleRef(this);
        this.antecedents[4] = lhs5;
        lhs5.addRuleRef(this);
        this.antecedents[5] = lhs6;
        lhs6.addRuleRef(this);
        this.consequent = rhs;
        rhs.addRuleRef(this);
        rhs.isConsequent();
        this.rb.ruleList.addElement(this);
        this.truth = null;
    }

    long numAntecedents() {
        return (long)this.antecedents.length;
    }

    Boolean check() {
        RuleBase.appendText("\nTesting rule " + this.name);

        for(int i = 0; i < this.antecedents.length; ++i) {
            if (this.antecedents[i].truth == null) {
                return null;
            }

            if (!this.antecedents[i].truth) {
                return this.truth = new Boolean(false);
            }
        }

        return this.truth = new Boolean(true);
    }

    void fire() {
        RuleBase.appendText("\nFiring rule " + this.name);
        this.truth = new Boolean(true);
        this.fired = true;
        this.consequent.lhs.setValue(this.consequent.rhs);
        checkRules(this.consequent.lhs.clauseRefs);
    }

    public static void checkRules(Vector clauseRefs) {
        Enumeration enume = clauseRefs.elements();

        while(enume.hasMoreElements()) {
            Clause temp = (Clause)enume.nextElement();
            Enumeration enume2 = temp.ruleRefs.elements();

            while(enume2.hasMoreElements()) {
                ((Rule)enume2.nextElement()).check();
            }
        }

    }

    void display(TextArea textArea) {
        textArea.appendText(this.name + ": IF ");
        int cmp = 0;
        for(int i = 0; i < this.antecedents.length; ++i) {
            Clause nextClause = this.antecedents[i];
            if(!nextClause.rhs.equals(""))
            {
                if(cmp==0)
                {
                    textArea.appendText(nextClause.lhs.name + nextClause.cond.asString() + nextClause.rhs + " ");
                    cmp++;
                }
                else
                {
                    textArea.appendText("\n AND ");
                    textArea.appendText(nextClause.lhs.name + nextClause.cond.asString() + nextClause.rhs + " ");
                }
           
            }
        }

        textArea.appendText("\n THEN ");
        textArea.appendText(this.consequent.lhs.name + this.consequent.cond.asString() + this.consequent.rhs + "\n");
    }

    Boolean backChain() {
        RuleBase.appendText("\nEvaluating rule " + this.name);

        for(int i = 0; i < this.antecedents.length; ++i) {
            if (this.antecedents[i].truth == null) {
                this.rb.backwardChain(this.antecedents[i].lhs.name);
            }

            if (this.antecedents[i].truth == null) {
                this.antecedents[i].lhs.askUser();
                this.truth = this.antecedents[i].check();
            }

            if (!this.antecedents[i].truth) {
                return this.truth = new Boolean(false);
            }
        }

        return this.truth = new Boolean(true);
    }
}
