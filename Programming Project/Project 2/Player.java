import processing.core.PApplet;

public class Player{
    PApplet p;
    public Player(PApplet p) { this.p = p ; }

    int xpos; int ypos;

    void move(int x){
        xpos=Constants.SCREENX/2;
        ypos=Constants.SCREENY-30;
        if(x>Constants.SCREENX - Constants.PADDLEWIDTH) xpos = Constants.SCREENX - Constants.PADDLEWIDTH;
        else xpos=x;
    }
    void draw(int paddleColour){
        p.fill(paddleColour);
        p.rect(xpos, ypos, Constants.PADDLEWIDTH, Constants.PADDLEHEIGHT);
    }
}