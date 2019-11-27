import processing.core.PApplet;
import processing.core.PFont;

public class MainApp extends PApplet {

    public static void main(String[] args){PApplet.main("MainApp", args);}

    Player thePlayer;
    Ball theBall;
    Computer theComputer;

    public void settings(){
        size(Constants.SCREENX, Constants.SCREENY);
    }

    public void setup(){
        PFont myFont = createFont("Impact", 18);
        textFont(myFont);
        thePlayer = new Player(this);
        theBall = new Ball(this);
        theComputer = new Computer(this);
        ellipseMode(RADIUS);
        theBall.spawn();
        theComputer.spawn();
    }

    public void draw(){
        background(0);
        thePlayer.move(mouseX);
        theBall.move();
        theBall.collideP1(thePlayer, mouseX, pmouseX);
        thePlayer.draw(theBall.paddleColour);
        theComputer.move(theBall.x);
        theComputer.draw();
        theBall.collideP2(theComputer);
        theBall.collideEnvironment();
        theBall.draw();
    }

    public void mousePressed(){
        if(theBall.gameState==1||theBall.gameState==2){
            if (theBall.gameEnd==false){
                theBall.spawn();
                loop();
            }
            else text("Game over!", 100, 100);
        }
    }
}
