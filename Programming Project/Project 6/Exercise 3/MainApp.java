import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

import java.util.ArrayList;

public class MainApp extends PApplet {

    PFont stdFont;
    final int EVENT_BUTTON1=1;
    final int EVENT_BUTTON2=2;
    final int EVENT_BUTTON3=3;
    final int EVENT_BUTTON4=4;
    final int EVENT_NULL=0;
    Screen theScreens[];
    int currentScreen=0;
    Checkbox theCheckbox;
    int numberOfRadioOptions=4;
    RadioButton theRadioButton[];
    Slider theSlider;
    int mouseDiff;

    public static void main(String[] args){PApplet.main("MainApp", args);}

    public void settings(){
        size(Constants.SCREENX, Constants.SCREENY);
    }

    public void setup(){
        stdFont = createFont("Georgia", 32);
        textFont(stdFont);
        theSlider = new Slider();

        theScreens = new Screen[2];
        theScreens[0]= new Screen(255);
        theScreens[1]= new Screen(150);

        theScreens[0].setup();
        theScreens[0].addWidget(100, 100, 180, 40, "Button 1", color(255,0,0), EVENT_BUTTON2);
        theScreens[0].addWidget(100, 200, 180, 40, "Forwards", color(0,255,0), EVENT_BUTTON1);
        theScreens[1].setup();
        theScreens[1].addWidget(100, 100, 180, 40, "Button 2", color(0,0,255), EVENT_BUTTON4);
        theScreens[1].addWidget(100, 200, 180, 40, "Backwards", color(0,255,255), EVENT_BUTTON3);

        theCheckbox = new Checkbox(300,0);
        theRadioButton = new RadioButton[numberOfRadioOptions];
        int yRadio=100;
        for (int i=0; i<numberOfRadioOptions; i++){
            theRadioButton[i] = new RadioButton(300, yRadio);
            yRadio+=100;
        }
    }

    public void draw(){
        if(currentScreen==Constants.SCREEN_ONE){
            theScreens[0].draw();
        }
        else if(currentScreen==Constants.SCREEN_TWO){
            theScreens[1].draw();
        }

        theCheckbox.draw();
        for (int i=0; i<numberOfRadioOptions; i++){
            theRadioButton[i].draw();
        }
        theSlider.draw();
    }

    public void mousePressed() {
        if(currentScreen==Constants.SCREEN_ONE){
            theScreens[0].mousePressed();
        }
        else if(currentScreen==Constants.SCREEN_TWO){
            theScreens[1].mousePressed();
        }

        theCheckbox.mousePressed(mouseX, mouseY);
        for (int i=0; i<numberOfRadioOptions; i++){
            theRadioButton[i].mousePressed(mouseX, mouseY);
            if(theRadioButton[i].checkedBox==true){
                for (int j=0; j<numberOfRadioOptions; j++){
                    if (j==i);
                    else theRadioButton[j].checkedBox=false;
                }
            }
        }
        mouseDiff=theSlider.squareStartX-mouseX;
    }

    public void mouseDragged(){
        if(mouseX>theSlider.squareStartX && mouseX<theSlider.squareStartX+theSlider.squareSize &&
                mouseY>theSlider.squareStartY && mouseY<theSlider.squareStartY+theSlider.squareSize)
        theSlider.mouseDragged(mouseX, mouseDiff);
    }

    public class Widget {
        int x, y, width, height;
        String label;
        int event;
        int widgetColor, labelColor;
        int strokeColour;

        PFont widgetFont;

        Widget(){
        }

        Widget(int x,int y, int width, int height, String label, int widgetColor, PFont widgetFont, int event){
            this.x=x; this.y=y; this.width = width; this.height= height;
            this.label=label; this.event=event;
            this.widgetColor=widgetColor; this.widgetFont=widgetFont;
            labelColor= color(0);
        }

        public void draw(){
            stroke(strokeColour);
            fill(widgetColor);
            rect(x,y,width,height);
            fill(labelColor);
            text(label, x+10, y+height-10);
        }

        int getEvent(int mX, int mY){
            if(mX>x && mX<x+width && mY>y && mY<y+height){
                return event;
            }
            return EVENT_NULL;
        }
    }

    public class Screen{
        int backgroundColour;
        ArrayList widgetList;

        Screen(int backgroundColour){
            this.backgroundColour=backgroundColour;
        }

        public void setup(){
            widgetList = new ArrayList();
        }

        public void draw(){
            background(backgroundColour);
            for(int i = 0; i<widgetList.size(); i++){
                Widget aWidget = (Widget)widgetList.get(i);
                aWidget.draw();
            }
        }

        public void mousePressed(){
            int event;

            for(int i = 0; i<widgetList.size(); i++){
                Widget aWidget = (Widget) widgetList.get(i);
                event = aWidget.getEvent(mouseX,mouseY);
                switch(event) {
                    case EVENT_BUTTON1:
                        currentScreen=1;
                        break;
                    case EVENT_BUTTON2:
                        println("Screen 1 top button");
                        break;
                    case EVENT_BUTTON3:
                        currentScreen=0;
                        break;
                    case EVENT_BUTTON4:
                        println("Screen 2 top button");
                        break;
                }
            }
        }

        public void addWidget(int widgetX, int widgetY, int widgetWidth, int widgetHeight, String widgetLabel, int widgetColour, int widgetAction){
            Widget newWidget;
            newWidget=new Widget(widgetX, widgetY, widgetWidth, widgetHeight, widgetLabel, color(widgetColour), stdFont, widgetAction);
            widgetList.add(newWidget);
        }
    }

    public class Checkbox extends Widget {
        PImage checkbox;
        PImage checkedbox;
        boolean checkedBox = false;

        Checkbox(int x, int y){
                checkbox = loadImage("circle-50.png");
                checkedbox = loadImage("tick-inside-circle-50.png");
                this.x=x;
                this.y=y;
        }

        public void draw(){
            if(checkedBox==true){
                image(checkedbox,x,y);
            }
            else image(checkbox,x,y);
        }

        public void mousePressed(int mX, int mY){
            if(mX>x && mX<x+50 && mY>y && mY<y+50 && checkedBox==false){
                checkedBox=true;
                println(checkedBox);
            }
            else if (mX>x && mX<x+50 && mY>y && mY<y+50 && checkedBox==true){
                checkedBox=false;
                println(checkedBox);
            }
        }
    }

    public class RadioButton extends Widget {
        PImage checkbox;
        PImage checkedbox;
        boolean checkedBox = false;

        RadioButton(int x, int y){
            checkbox = loadImage("circle-50.png");
            checkedbox = loadImage("tick-inside-circle-50.png");
            this.x=x;
            this.y=y;
        }

        public void draw(){
            if(checkedBox==true){
                image(checkedbox,x,y);
            }
            else image(checkbox,x,y);
        }

        public void mousePressed(int mX, int mY){
            if(mX>x && mX<x+50 && mY>y && mY<y+50 && checkedBox==false){
                checkedBox=true;
                println(checkedBox);
            }
            else if (mX>x && mX<x+50 && mY>y && mY<y+50 && checkedBox==true){
                checkedBox=false;
                println(checkedBox);
            }
        }
    }

    public class Slider {
        int squareStartX = 15;
        int squareStartY = 10;
        int sliderStartX = 10;
        int sliderStartY = 15;
        int squareSize = 15;
        int sliderLength = 100;
        int sliderWidth = 5;


        public void draw(){
            fill(color(200));
            rect(sliderStartX, sliderStartY, sliderLength, sliderWidth);
            fill(255, 0, 0);
            square(squareStartX, squareStartY, squareSize);

        }

        public void mouseDragged(int mX, int mD){
            if(squareStartX<sliderStartX) squareStartX++;
            else if (squareStartX+squareSize>sliderStartX+sliderLength) squareStartX--;
            else if (squareStartX>=sliderStartX&&squareStartX+squareSize<=sliderStartX+sliderLength) squareStartX=mX+mD;
        }
    }
}
