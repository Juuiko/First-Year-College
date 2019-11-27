import processing.core.PApplet;
import processing.core.PImage;

public class MainApp extends PApplet {

    PImage myImage;
    PImage mySecondImage;
    PImage myThirdImage;
    Alien theAliens[];
    Player thePlayer;
    Bullet theBullet;
    float alienXpos=0f;
    float alienYpos=0f;
    boolean shotFired=false;

    public static void main(String[] args){PApplet.main("MainApp", args);}

    public void settings(){
        size(Constants.SCREENX, Constants.SCREENY);
    }

    public void setup(){
        theAliens = new Alien[10];
        thePlayer = new Player();
        myImage= loadImage("invader.GIF");
        mySecondImage= loadImage("exploding.GIF");
        myThirdImage= loadImage("invader2.PNG");
        init_array(theAliens);
    }

    public void draw(){
        background(255);
        thePlayer.draw(200);
        thePlayer.move(mouseX);
        if (shotFired==true){
            theBullet.draw();
            theBullet.move();
            if(theBullet.y+Constants.BULLETHEIGHT<0) shotFired=false;
            for(int i=0; i<theAliens.length; i++){
                theBullet.collide(theAliens[i]);}
        }
        draw_array(theAliens);
        move_array(theAliens);
    }

    public void mousePressed(){
        if(shotFired==false) {
            theBullet = new Bullet(thePlayer.xpos);
            shotFired = true;
        }
    }

    void init_array(Alien theAliens[]){
        for(int i=0; i<theAliens.length; i++){
            theAliens[i] = new Alien(alienXpos, alienYpos);
            alienXpos=alienXpos-40;}
    }

    void draw_array(Alien theAliens[]){
        for(int i=0; i<theAliens.length; i++)
            theAliens[i].draw(myImage, mySecondImage, myThirdImage);
    }

    void move_array(Alien theAliens[]){
        for(int i=0; i<theAliens.length; i++){
            theAliens[i].move();}
    }



    public class Alien {

        float x;
        float y;
        float speed=1f;
        int direction=0;
        float alienHeight=30;
        int alienWidth=30;
        boolean exploded = false;
        int debrisTimeout=100;
        float alienColour = random(100);
        float angle = 0;
        float movementModifier = 0;

        Alien(float xpos, float ypos){
            x=xpos;
            y=ypos;
        }

        public void draw(PImage Alien, PImage Exploded, PImage Alien2){
            if (exploded==false&&alienColour>50){
                image(Alien, x, y);
            }
            else if (exploded==false){
                image(Alien2, x, y);
            }
            else if (debrisTimeout!=0){
                image(Exploded, x, y);
                debrisTimeout--;
            }

        }

        public void move(){
            angle=angle+0.1f;
            movementModifier = sin(angle);
            y=y+(movementModifier*2);
            speed=speed+0.0025f;
            if(direction==0){
                if (x>Constants.SCREENX-alienWidth){
                    if(alienHeight>0){
                        y=y+speed;
                        alienHeight=alienHeight-speed;
                    }
                    else direction=1;
                }
                else {
                    x=x+speed;
                    alienHeight=30;
                }
            }
            else{
                if (x<0){
                    if(alienHeight>0){
                        y=y+speed;
                        alienHeight=alienHeight-speed;
                    }
                    else direction=0;
                }
                else {
                    x=x-speed;
                    alienHeight=30;
                }
            }

        }
    }



    public class Player {
        int xpos; int ypos;

        void move(int x){
            xpos=Constants.SCREENX/2;
            ypos=Constants.SCREENY-Constants.MARGIN;
            if(x>Constants.SCREENX - Constants.PADDLEWIDTH) xpos = Constants.SCREENX - Constants.PADDLEWIDTH;
            else xpos=x;
        }
        void draw(int paddleColour){
            fill(paddleColour);
            rect(xpos, ypos, Constants.PADDLEWIDTH, Constants.PADDLEHEIGHT);
        }
    }



    public class Bullet {
        int x; int y;

        Bullet(int xpos){
            x=xpos+(Constants.PADDLEWIDTH/2);
            y=Constants.SCREENY-Constants.MARGIN-Constants.PADDLEHEIGHT;
        }

        void move(){
            y-=4;
        }
        void draw(){
            fill(100);
            rect(x, y, Constants.BULLETWIDTH, Constants.BULLETHEIGHT);
        }

        void collide(Alien theAlien){
            if(x>theAlien.x&&y>=theAlien.y&&
                    x+5<theAlien.x+30&&y+7<=theAlien.y+30){
                theAlien.exploded=true;
            }

        }
    }
}