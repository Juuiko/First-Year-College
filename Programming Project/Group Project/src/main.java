import data.JSONLoader;
import graphics.elements.Button;
import graphics.elements.DropDown;
import graphics.elements.Widget;
import processing.core.PApplet;
import processing.core.PFont;

import java.util.ArrayList;
import java.util.List;

public class main extends PApplet{

    private List<Widget> widgets = new ArrayList<>();
    private JSONLoader data;
    private PFont myFont;

    public void settings(){
        size(720, 480);
    }

    public void setup() {
        data = new JSONLoader(this);
        myFont = createFont("Georgia", 32);

        widgets.add(new Button(this, 200, 200, 100, 40, "button", this.color(255), myFont,
                button -> System.out.println(button.toString())));

        List<String> dropDownLabels = new ArrayList<>();
        dropDownLabels.add("Test1234");

        widgets.add(new DropDown(this, 300, 300, 100, 40, "button", this.color(255), myFont, dropDownLabels));
    }

    public void draw(){
        background(100);
        widgets.forEach(Widget::draw);
        color(255);
    }

    public void mousePressed(){
        widgets.forEach(widget -> {
            if(widget.isWithinBounds(mouseX,mouseY))
                widget.fire();
        });
    }

    public static void main(String[] args){
        PApplet.main("main");
    }
}