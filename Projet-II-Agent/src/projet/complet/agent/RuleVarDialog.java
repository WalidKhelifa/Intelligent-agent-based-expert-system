package projet.complet.agent;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.awt.Button;
import java.awt.Dialog;
import java.awt.Event;
import java.awt.Frame;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.TextField;

public class RuleVarDialog extends Dialog {
    Label label1;
    TextField textField1;
    Button button1;
    String answer;

    void button1_Clicked(Event event) {
        this.answer = this.textField1.getText();
    }

    void button2_Clicked(Event event) {
        this.answer = "";
        this.textField1.setText("");
    }

    public RuleVarDialog(Frame parent, boolean modal) {
        super(parent, modal);
        this.answer = new String("");
        this.setLayout((LayoutManager)null);
        this.addNotify();
        this.resize(this.insets().left + this.insets().right + 352, this.insets().top + this.insets().bottom + 214);
        this.label1 = new Label("");
        this.label1.reshape(this.insets().left + 0, this.insets().top + 12, 407, 61);
        this.add(this.label1);
        this.textField1 = new TextField();
        this.textField1.setText("");
        this.textField1.reshape(this.insets().left + 192, this.insets().top + 84, 97, 39);
        this.add(this.textField1);
        this.button1 = new Button("Set");
        this.button1.reshape(this.insets().left + 24, this.insets().top + 144, 124, 41);
        this.add(this.button1);
        this.setTitle("Rule Applet -- Ask User");
    }

    public RuleVarDialog(Frame parent, String title, boolean modal) {
        this(parent, modal);
        this.setTitle(title);
    }

    public boolean gotFocus(Event e, Object arg) {
        this.textField1.requestFocus();
        return false;
    }

    public synchronized void show() {
        Rectangle bounds = this.getParent().bounds();
        Rectangle abounds = this.bounds();
        this.move(bounds.x + (bounds.width - abounds.width) / 2, bounds.y + (bounds.height - abounds.height) / 2);
        super.show();
    }

    public boolean handleEvent(Event event) {
        if (event.id == 1004 & event.target == this.button1) {
            this.button1_Clicked(event);
            this.hide();
            this.dispose();
            return true;
        } else {
            return super.handleEvent(event);
        }
    }

    public String getText() {
        return this.answer;
    }
}
