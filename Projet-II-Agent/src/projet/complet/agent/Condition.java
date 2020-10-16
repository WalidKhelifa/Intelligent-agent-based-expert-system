package projet.complet.agent;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

public class Condition {
    int index;
    String symbol;

    Condition(String Symbol) {
        this.symbol = Symbol;
        if (Symbol.equals("=")) {
            this.index = 1;
        } else if (Symbol.equals(">")) {
            this.index = 2;
        } else if (Symbol.equals("<")) {
            this.index = 3;
        } else if (Symbol.equals("!=")) {
            this.index = 4;
        } else {
            this.index = -1;
        }

    }

    String asString() {
        String temp = new String();
        switch(this.index) {
        case 1:
            temp = "=";
            break;
        case 2:
            temp = ">";
            break;
        case 3:
            temp = "<";
            break;
        case 4:
            temp = "!=";
        }

        return temp;
    }
}
