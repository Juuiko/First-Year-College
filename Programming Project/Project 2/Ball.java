import processing.core.PApplet;

public class Ball {
    PApplet p;
    public Ball(PApplet p) {this.p = p;}

    float x;
    float y;
    float dx;
    float dy;
    int radius;
    int gameState=0;
    boolean gameEnd=false;
    int playerLives=3;
    int computerLives=3;
    float ballSpeedModifier = 1.005f;
    float bounceAngle = 1.5f;
    int paddleColour = 255;
    float percentagePower = 1;

    void spawn() {
        x = p.random(Constants.SCREENX / 4, Constants.SCREENX / 2);
        y = p.random(Constants.SCREENY / 4, Constants.SCREENY / 2);
        dx = p.random(1, 2);
        dy = p.random(1, 2);
        radius = 5;
    }

    void move(){
        dy = dy * ballSpeedModifier;
        x = x+dx; y = y+dy;
    }

    void draw(){
        int ballColor = p.color(200, 100, 50);
      //  if(paddleColour<=70){
        //    percentagePower=paddleColour/255*2;
       // }
        p.fill(ballColor);
        p.ellipse(x, y, radius, radius);
        p.text(playerLives, 10, 20);
        p.text(computerLives, 300, 20);
        p.text(dx, 40, 20);
        p.text(Math.abs(dy), 200, 20);
    //    p.text(percentagePower, 200, 200);

    }

    void collideEnvironment (){
        if(x-radius <=0) dx=-dx;
        else if(x+radius>=Constants.SCREENX) dx=-dx;

        if(y+radius>Constants.SCREENY){
            gameState=1; dx=0; dy=0;
        }
        else if(y-radius<0){
            gameState=2; dx=0; dy=0;
        }
        else gameState=0;

        if (gameState==1){
            computerLives--;
            if(computerLives==0){
                p.text("Game Over Computer wins", 70, 100);
            }
            else p.text("Point to the Computer ", 80, 100);
            p.noLoop();
        }
        else if (gameState==2){
            playerLives--;
            if(playerLives==0){
                p.text("Game Over Player wins", 70, 100);
            }
            else p.text("Point to the Player", 80, 100);
            p.noLoop();
        }

        if (computerLives==0){
            gameEnd=true;
        }
        else if (playerLives==0){
            gameEnd=true;
        }
    }

    void collideP1(Player tp, int mouseX, int pMouseX){
        if(y+radius >= tp.ypos && y-radius<tp.ypos+Constants.PADDLEHEIGHT){
            paddleColour = 255;
            if(x >= tp.xpos && x <= tp.xpos+Constants.PADDLEWIDTH*2/5){
                dy=-dy*percentagePower;
                dx=dx-bounceAngle;
            }
            else if (x >= tp.xpos+Constants.PADDLEWIDTH*2/5 && x <= tp.xpos+Constants.PADDLEWIDTH*3/5){
                dy=-dy*percentagePower;
            }
            else if (x >= tp.xpos+Constants.PADDLEWIDTH*3/5 && x <= tp.xpos+Constants.PADDLEWIDTH){
                dy=-dy*percentagePower;
                dx=dx+bounceAngle;
            }
        }
  //      if(pMouseX==mouseX){
  //          if (paddleColour<=10){
  //              paddleColour=10;
  //          }
  //          else paddleColour=paddleColour-10;
  //      }
    }

    void collideP2(Computer tp){
        if(y+radius >= tp.ypos && y-radius<tp.ypos+Constants.PADDLEHEIGHT && x >= tp.xpos && x <= tp.xpos+Constants.PADDLEWIDTH){
            dy=-dy;
        }
    }
}