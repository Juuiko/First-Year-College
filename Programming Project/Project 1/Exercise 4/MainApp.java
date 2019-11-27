import processing.core.PApplet;

public class MainApp extends PApplet {
    public static void main(String[] args){
        PApplet.main("MainApp", args);
    }

    int i=0;
    int j=width+50;

    public void settings() {
        size(200, 200);
    }

    public void setup(){
        noStroke(); fill(255, 204, 0);
    }

    public void draw(){
        background(255);
        fill(255, 204, 0);
        rect(i, 20, 50, 50);
        fill(100, 50);
        rect(j, 100, 50, 50);
        fill(255, 204, 0);
        if(i+100>=width) rect(i-width, 20, 50, 50);
        if(i++>=width) i=0;
        fill(100, 50);
        if(j+100<=width) rect(j+width, 100, 50, 50);
        fill(100, 50);
        if (j--<=-50) j=width-50;
    }
}
