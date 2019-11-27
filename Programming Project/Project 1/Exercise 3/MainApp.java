import processing.core.PApplet;

public class MainApp extends PApplet {
    public static void main(String[] args){
        PApplet.main("MainApp", args);
    }

    int i=0;

    public void settings() {
        size(200, 200);
    }

    public void setup(){
        noStroke(); fill(255, 204, 0);
    }

    public void draw(){
        background(255);
        rect(i, 20, 50, 50);
        i++;
        if(i+50>=width) rect(i-width, 20, 50, 50);
        if(i>=width) i=0;
    }
}
