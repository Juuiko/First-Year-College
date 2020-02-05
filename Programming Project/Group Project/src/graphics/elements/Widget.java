package graphics.elements;

import processing.core.PApplet;
import processing.core.PFont;

import java.util.function.Consumer;

public abstract class Widget {

    private PApplet p;
    protected int x, y, width, height;
    private String label;
    private int widgetColor, labelColor, strokeColor;
    private PFont widgetFont;
    protected Consumer<Widget> action;

    public Widget(PApplet p, int x, int y, int width, int height, String label, int widgetColor, PFont widgetFont, Consumer<? extends Widget> action) {
        this.p = p;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.label = label;
        this.widgetColor = widgetColor;
        this.widgetFont = widgetFont;
        labelColor = p.color(0);
        strokeColor = p.color(0);
        this.action = (Consumer<Widget>) action;
    }
    public Widget(PApplet p, int x, int y, int width, int height, int widgetColor, Consumer<? extends Widget> action) {
        this.p = p;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.widgetColor = widgetColor;
        labelColor = p.color(0);
        strokeColor = p.color(0);
        this.action = (Consumer<Widget>) action;
    }
    public void draw() {
        p.stroke(strokeColor);
        p.fill(widgetColor);
        p.rect(x, y, width, height);
        p.fill(labelColor);
        p.textFont(widgetFont);
        p.text(label, x+10, y+height-10);
    }

    public void setStrokeColor(int c) {
        strokeColor = c;
    }

    public boolean isWithinBounds(int targetx, int targety){
        return targetx >= x && targetx <= x + width &&
                targety >= y && targety <= y + height;
    }

    abstract public void fire();
}
