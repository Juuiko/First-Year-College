import processing.core.PApplet;

public class MainApp extends PApplet {
    public static void main(String[] args){
        PApplet.main("MainApp", args);
    }

    int i=0;
    int j=width+50;
    float angle=0;
    float colourR=0;
    float colourG=0;
    float colourB=0;


    public void settings() {
        size(200, 200);
    }

    public void setup(){
        noStroke();
    }

    public void draw(){
        float heightSqr = 10*sin(angle);
        background(255);
        rect(i, 20+heightSqr, 50, 50);
        rect(j, 100-heightSqr, 50, 50);
        if(i+100>=width) rect(i-width, 20+heightSqr, 50, 50);
        if(i++>=width) i=0;
        if(j+100<=width) rect(j+width, 100-heightSqr, 50, 50);
        fill(colourR,colourG,colourB);
        if (j--<=-50) j=width-50;
        angle += 0.1;
        colourR=random(0,255);
        colourG=random(0,255);
        colourB=random(0,255);
    }
}
