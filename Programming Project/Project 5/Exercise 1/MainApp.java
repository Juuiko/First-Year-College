import processing.core.PApplet;
import processing.core.PImage;

public class MainApp extends PApplet {

    PImage myImage;
    PImage mySecondImage;
    PImage myThirdImage;
    Alien theAliens[];
    Player thePlayer;
    Bullet theBullet;
    PowerUps thePowerUps[];
    Bomb theBombs[];
    float alienXpos=0f;
    float alienYpos=0f;
    boolean shotFired=false;
    int numberOfAliens=50;

    public static void main(String[] args){PApplet.main("MainApp", args);}

    public void settings(){
        size(Constants.SCREENX, Constants.SCREENY);
    }

    public void setup(){
        theAliens = new Alien[numberOfAliens];
        thePowerUps = new PowerUps[numberOfAliens];
        theBombs = new Bomb[numberOfAliens];
        thePlayer = new Player();
        myImage= loadImage("invader.GIF");
        mySecondImage= loadImage("exploding.GIF");
        myThirdImage= loadImage("invader2.PNG");
        init_array(theAliens, thePowerUps);
    }

    public void draw(){
        background(255);
        thePlayer.draw(200);
        thePlayer.move(mouseX);
        if (shotFired==true){
            theBullet.draw();
            theBullet.move();
            theBullet.thiccBullets(thePlayer);
            theBullet.fireRatePlusPlus(thePlayer);
            if(theBullet.y+theBullet.bulletHeight<0) shotFired=false;
            for(int i=0; i<theAliens.length; i++){
                theBullet.collide(theAliens[i]);
                theBullet.doubleTapp(theAliens[i], thePlayer);
            }
        }
        draw_array(theAliens, thePowerUps);
        move_array(theAliens, thePowerUps);
    }

    public void mousePressed(){
        if(shotFired==false) {
            theBullet = new Bullet(thePlayer.xpos);
            shotFired = true;
        }
    }

    void init_array(Alien theAliens[], PowerUps thePowerUps[]){
        for(int i=0; i<theAliens.length; i++){
            theAliens[i] = new Alien(alienXpos, alienYpos);
            if(theAliens[i].alienColour<40) thePowerUps[i] = new PowerUps(alienXpos, alienYpos);
            if(theAliens[i].alienColour>40) theBombs[i] = new Bomb(alienXpos, alienYpos);
            alienXpos=alienXpos-40;
        }
    }

    void draw_array(Alien theAliens[], PowerUps thePowerUps[]){
        for(int i=0; i<theAliens.length; i++) {
            if (theAliens[i].alienColour<40) thePowerUps[i].draw();
            if (theAliens[i].alienColour>40) theBombs[i].draw();
            theAliens[i].draw(myImage, mySecondImage, myThirdImage);
        }
    }

    void move_array(Alien theAliens[], PowerUps thePowerUps[]){
        for(int i=0; i<theAliens.length; i++){
            theAliens[i].move();
            if (theAliens[i].alienColour<40) {
                thePowerUps[i].move(theAliens[i].x, theAliens[i].y, theAliens[i].exploded);
                thePowerUps[i].collide(thePlayer);
            }
            if (theAliens[i].alienColour>40) {
                theBombs[i].move(theAliens[i].x, theAliens[i].y, theAliens[i].exploded);
                theBombs[i].collide(thePlayer);
            }
        }
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
        float alienColour = random( 100);
        float angle = 0;
        float movementModifier = 0;

        Alien(float xpos, float ypos){
            x=xpos;
            y=ypos;
        }

        public void draw(PImage Alien, PImage Exploded, PImage Alien2){
            if (exploded==false&&alienColour>40){
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
        boolean fireRatePlusPlusOn=false;
        boolean doubleTappOn=false; boolean thiccBulletsOn=false;

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
        int x; int y; int speedModifer=1; int doubleTapY;
        int bulletWidth=5; int bulletHeight=7;


        Bullet(int xpos){
            x=xpos+(Constants.PADDLEWIDTH/2);
            y=Constants.SCREENY-Constants.MARGIN-Constants.PADDLEHEIGHT;
        }

        void move(){
            y-=4*speedModifer;
        }
        void draw(){
            fill(100);
            rect(x, y, bulletWidth, bulletHeight);
        }

        void collide(Alien theAlien){
            if(x>theAlien.x&&y>=theAlien.y&&
                    x<theAlien.x+Constants.ALIEN_SIZE&&y<=theAlien.y+Constants.ALIEN_SIZE||
                    x+bulletWidth>theAlien.x&&y+bulletHeight>=theAlien.y&&
                            x+bulletWidth<theAlien.x+Constants.ALIEN_SIZE&&y+bulletWidth<=theAlien.y+Constants.ALIEN_SIZE){
                theAlien.exploded=true;
            }
        }

        void fireRatePlusPlus(Player thePlayer){
            if (thePlayer.fireRatePlusPlusOn==true){
                theBullet.speedModifer=3;
            }
            else theBullet.speedModifer=1;
        }

        void doubleTapp(Alien theAlien, Player thePlayer){
            if(thePlayer.doubleTappOn==true) {
                doubleTapY=y-20;
                fill(100);
                rect(x, doubleTapY, bulletWidth, bulletHeight);
                if(x>theAlien.x&&doubleTapY>=theAlien.y&&
                        x<theAlien.x+Constants.ALIEN_SIZE&&doubleTapY<=theAlien.y+Constants.ALIEN_SIZE||
                        x+bulletWidth>theAlien.x&&doubleTapY+bulletHeight>=theAlien.y&&
                                x+bulletWidth<theAlien.x+Constants.ALIEN_SIZE&&doubleTapY+bulletWidth<=theAlien.y+Constants.ALIEN_SIZE){
                    theAlien.exploded=true;
                }
            }
        }

        void thiccBullets(Player thePlayer){
            if(thePlayer.thiccBulletsOn==true) {
                bulletWidth = 15;
                bulletHeight = 21;
            }
        }
    }

    public class Bomb {

        float x;
        float y;
        int bombSize=10;


        Bomb(float xpos, float ypos){
            x = xpos;
            y = ypos;
        }

        public void draw(){
            fill(0, 200, 0);
            rect(x, y, bombSize, bombSize);
        }

        public void move(float alienXpos, float alienYpos, boolean alienExploded){
            if(alienExploded==false){
                x=alienXpos+(Constants.ALIEN_SIZE/2)-(bombSize/2); y=alienYpos+(Constants.ALIEN_SIZE/2)-(bombSize/2);
            }
            else y+=2;
        }

        boolean offScreen(){
            if(y>Constants.SCREENY) return true;
            else return false;
        }

        void collide(Player thePlayer){
            if(x+bombSize>thePlayer.xpos&&x<thePlayer.xpos+Constants.PADDLEWIDTH&&
                    y>Constants.SCREENY-Constants.MARGIN-Constants.PADDLEHEIGHT) {
                print("Hit!");
            }
        }

    }

    public class PowerUps {

        float x;
        float y;
        int powerUpSize=10;
        float powerUpType;
        boolean powerUpActive=false;

        PowerUps(float xpos, float ypos){
            x = xpos;
            y = ypos;
        }

        public void draw(){
            if(powerUpActive==false) {
                fill(200, 0, 0);
                rect(x, y, powerUpSize, powerUpSize);
            }
        }

        public void move(float alienXpos, float alienYpos, boolean alienExploded){
            if(alienExploded==false){
                x=alienXpos+(Constants.ALIEN_SIZE/2)-(powerUpSize/2); y=alienYpos+(Constants.ALIEN_SIZE/2)-(powerUpSize/2);
            }
            else y+=2;
        }

        void collide(Player thePlayer){
            if(x+powerUpSize>thePlayer.xpos&&x<thePlayer.xpos+Constants.PADDLEWIDTH&&
                    y>Constants.SCREENY-Constants.MARGIN-Constants.PADDLEHEIGHT) {
                if(powerUpActive==false){
                    while(powerUpActive==false){
                        powerUpType=random(3);
                        if(powerUpType>=0&&powerUpType<1&&thePlayer.thiccBulletsOn==false){
                            powerUpActive=true;
                            thePlayer.thiccBulletsOn=true;
                        }
                        if(powerUpType>=1&&powerUpType<2&&thePlayer.fireRatePlusPlusOn==false){
                            powerUpActive=true;
                            thePlayer.fireRatePlusPlusOn=true;
                        }
                        if(powerUpType>=2&&powerUpType<3&&thePlayer.doubleTappOn==false){
                            powerUpActive=true;
                            thePlayer.doubleTappOn=true;
                        }
                        if(thePlayer.doubleTappOn==true&&thePlayer.fireRatePlusPlusOn==true&&thePlayer.thiccBulletsOn==true) powerUpActive=true;
                    }
                }
            }
        }
    }
}