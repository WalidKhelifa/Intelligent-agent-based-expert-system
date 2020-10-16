package projet.complet.agent;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.util.Enumeration;
import java.util.StringTokenizer;
import java.util.Vector;

public abstract class Variable {
    String name;
    String value;
    Vector labels;
    int column;

    public Variable() {
    }

    public Variable(String Name) {
        this.name = Name;
        this.value = null;
    }

    void setValue(String val) {
        this.value = val;
    }

    String getValue() {
        return this.value;
    }

    void setLabels(String Labels) {
        this.labels = new Vector();
        StringTokenizer tok = new StringTokenizer(Labels, " ");

        while(tok.hasMoreTokens()) {
            this.labels.addElement(new String(tok.nextToken()));
        }

    }

    String getLabel(int index) {
        return (String)this.labels.elementAt(index);
    }

    String getLabels() {
        String labelList = new String();

        for(Enumeration enume = this.labels.elements(); enume.hasMoreElements(); labelList = labelList + enume.nextElement() + " ") {
        }

        return labelList;
    }

    int getIndex(String label) {
        int i = 0;
        boolean index = false;

        for(Enumeration enume = this.labels.elements(); enume.hasMoreElements(); ++i) {
            if (label.equals(enume.nextElement())) {
                break;
            }
        }

        return i;
    }

    boolean categorical() {
        return this.labels != null;
    }

    public void setColumn(int col) {
        this.column = col;
    }

    public int normalizedSize() {
        return 1;
    }

    public String getDecodedValue(float[] act, int index) {
        return String.valueOf(act[index]);
    }

    public abstract void computeStatistics(String var1);

    public abstract int normalize(String var1, float[] var2, int var3);
}
