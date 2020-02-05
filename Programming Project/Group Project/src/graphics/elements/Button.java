package graphics.elements;

import processing.core.PApplet;
import processing.core.PFont;

import java.util.function.Consumer;

public class Button extends Widget {

    protected boolean clicked = false;

    public Button(PApplet p, int x, int y, int width, int height, String label, int widgetColor, PFont widgetFont, Consumer<? extends Button> action) {
        super(p,x,y,width,height,label,widgetColor,widgetFont, action);
    }

    @Override
    public void fire() {
        action.accept(this);
        clicked = !clicked;
    }
}
