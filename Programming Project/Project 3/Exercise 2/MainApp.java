import processing.core.PApplet;
import processing.core.PImage;

public class MainApp extends PApplet {

    PImage myImage;
    PImage mySecondImage;
    Alien theAliens[];
    float xpos=0f;
    float ypos=0f;

    public static void main(String[] args){PApplet.main("MainApp", args);}

    public void settings(){
        size(Constants.SCREENX, Constants.SCREENY);
    }

    public void setup(){
        theAliens = new Alien[10];
        myImage= loadImage("invader.GIF");
        mySecondImage= loadImage("exploding.GIF");
        init_array(theAliens);
    }

    public void draw(){
        background(255);
        draw_array(theAliens);
        move_array(theAliens);
    }

    void init_array(Alien theAliens[]){
        for(int i=0; i<theAliens.length; i++){
            theAliens[i] = new Alien(xpos, ypos);
        xpos=xpos+30;}
    }

    void draw_array(Alien theAliens[]){
        for(int i=0; i<theAliens.length; i++)
            theAliens[i].draw(myImage, mySecondImage);
    }

    void move_array(Alien theAliens[]){
        for(int i=0; i<theAliens.length; i++){
            theAliens[i].move();
            theAliens[i].explode(); }
    }



    public class Alien {

        float x=0f;
        float y=0f;
        int direction=0;
        int alienHeight=30;
        int alienWidth=30;
        boolean exploded = false;
        int debrisTimeout=100;

        Alien(float xpos, float ypos){
            x=xpos;
            y=ypos;
        }

        public void draw(PImage Alien, PImage Exploded){
            if (exploded==false){
                image(Alien, x, y);
            }
            else if (debrisTimeout!=0){
                image(Exploded, x, y);
                debrisTimeout--;
            }

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

        public void explode () {
            random(100);
            if (random(100000)>99900f) exploded=true;
        }
    }
}