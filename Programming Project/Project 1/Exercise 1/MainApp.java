import processing.core.PApplet;

public class MainApp extends PApplet {
    public static void main(String[] args){
        PApplet.main("MainApp", args);
    }

    int i = 25;
    int j = 50;
    int k = 75;

    public void settings() {
        size(200, 200);
    }

    public void setup(){
        noStroke();
    }

    public void draw(){
        background(255);
        fill(255, 0, 0);
        rect(i, 25, 50, 50);
        fill(0, 255, 0);
        rect(50, j, 50, 50);
        fill(0, 0, 255);
        rect(k, 75, 50, 50);
        i++;
        j++;
        k--;
    }
}
