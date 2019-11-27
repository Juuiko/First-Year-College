import processing.core.PApplet;
import processing.core.PImage;

public class Alien {

    PApplet p;
    public Alien(PApplet p) { this.p = p ; }
    PImage myImage;
    int x=0;
    int y=0;
    int direction=0;
    int alienHeight=30;
    int alienWidth=30;


    public void setup(){
        myImage= p.loadImage("invader.GIF");
    }

    public void draw(){
        p.image(myImage, x, y);
    }

    public void move(){
        if(direction==0){
            if (x>Constants.SCREENX-alienWidth){
                if(alienHeight>0){
                    y++;
                    alienHeight--;
                }
                else direction=1;
            }
            else {
                x++;
                alienHeight=30;
            }
        }
        else{
            if (x<0){
                if(alienHeight>0){
                    y++;
                    alienHeight--;
                }
                else direction=0;
            }
            else {
                x--;
                alienHeight=30;
            }
        }

    }
}
