import processing.core.PApplet;

public class MainApp extends PApplet {

    Alien theAlien;

    public static void main(String[] args){PApplet.main("MainApp", args);}

    public void settings(){
        size(Constants.SCREENX, Constants.SCREENY);
    }

    public void setup(){
        theAlien = new Alien(this);
        theAlien.setup();
    }

    public void draw(){
        background(255);
        theAlien.move();
        theAlien.draw();
    }

    public void mousePressed(){

    }
}
