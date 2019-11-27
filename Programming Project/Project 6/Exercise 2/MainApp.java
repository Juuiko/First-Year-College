import processing.core.PApplet;
import processing.core.PFont;
import java.util.ArrayList;

public class MainApp extends PApplet {

    PFont stdFont;
    final int EVENT_BUTTON1=1;
    final int EVENT_BUTTON2=2;
    final int EVENT_BUTTON3=3;
    final int EVENT_NULL=0;
    Widget widget1, widget2, widget3;
    ArrayList widgetList;

    public static void main(String[] args){PApplet.main("MainApp", args);}

    public void settings(){
        size(Constants.SCREENX, Constants.SCREENY);
    }

    public void setup(){
        stdFont = createFont("Georgia", 32);
        textFont(stdFont);

        widget1=new Widget(100, 100, 180, 40, "Red!", color(100), stdFont, EVENT_BUTTON1);
        widget2=new Widget(100, 200, 180, 40, "Green!", color(100), stdFont, EVENT_BUTTON2);
        widget3=new Widget(100, 300, 180, 40, "Blue!", color(100), stdFont, EVENT_BUTTON3);


        widgetList = new ArrayList();
        widgetList.add(widget1);
        widgetList.add(widget2);
        widgetList.add(widget3);
    }

    public void draw(){
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
                    widget1.widgetColor=color(255,0,0);
                    break;
                case EVENT_BUTTON2:
                    widget2.widgetColor=color(0,255,0);
                    break;
                case EVENT_BUTTON3:
                    widget3.widgetColor=color(0,0,255);
                    break;
            }
        }
    }

    public void mouseMoved(){
        int event;
        boolean whiteButton=false;

        for(int i = 0; i<widgetList.size(); i++){
            Widget aWidget = (Widget) widgetList.get(i);
            event = aWidget.getEvent(mouseX,mouseY);
            switch(event) {
                case EVENT_BUTTON1:
                    widget1.strokeColour=255;
                    whiteButton=true;
                    break;
                case EVENT_BUTTON2:
                    widget2.strokeColour=255;
                    whiteButton=true;
                    break;
                case EVENT_BUTTON3:
                    widget3.strokeColour=255;
                    whiteButton=true;
                    break;

            }
        }
        if(whiteButton==false){
            widget1.strokeColour=0;
            widget2.strokeColour=0;
            widget3.strokeColour=0;
        }
    }


    public class Widget {
        int x, y, width, height;
        String label;
        int event;
        int widgetColor, labelColor;
        int strokeColour;

        PFont widgetFont;

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

}
