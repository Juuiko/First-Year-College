package graphics.elements;

import processing.core.PApplet;
import processing.core.PFont;

import java.util.ArrayList;
import java.util.List;

public class DropDown extends Button {

    private List<Button> dropDownWidgets = new ArrayList<>();

    public DropDown(PApplet p, int x, int y, int width, int height, String label,
                    int widgetColor, PFont widgetFont, List<String> dropDownElements) {

        super(p, x, y, width, height, label, widgetColor, widgetFont, button -> {});

        int widgetDropDownY = y;

        for(String dropDownLabel : dropDownElements) {
            widgetDropDownY += height;
            dropDownWidgets.add(new Button(p, x, widgetDropDownY, width, height, dropDownLabel, widgetColor, widgetFont,
                    button -> System.out.println(button.toString())));
        }
    }

    public void draw() {
        super.draw();
        if(clicked) dropDownWidgets.forEach(Button::draw);
    }

    /*@Override
    public boolean isWithinBounds(int targetx, int targety){
        return targetx >= x && targetx <= x + width &&
                targety >= y + height &&
                targety <= y + height + dropDownWidgets.size() * height;
    }*/
}
