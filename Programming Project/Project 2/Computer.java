import processing.core.PApplet;

public class Computer{
    PApplet p;
    public Computer(PApplet p) { this.p = p ; }

    float xpos; float ypos;
    float aiSmarts=1.2f;
    float aiSmartsModifier=0.005f;

    void spawn(){
        xpos=Constants.SCREENX/2;
        ypos=30;
    }

    void move( float ballX ){
        if(xpos>Constants.SCREENX - Constants.PADDLEWIDTH) xpos = Constants.SCREENX - Constants.PADDLEWIDTH;
        else {
            if (xpos + Constants.PADDLEWIDTH / 2 > ballX) {
                xpos = xpos - aiSmarts;
            } else xpos = xpos + aiSmarts;
        }
    }

    void draw(){
        if(aiSmarts<=5){
            aiSmarts=aiSmarts+aiSmartsModifier;
        }
        p.fill(255);
        p.rect(xpos, ypos, Constants.PADDLEWIDTH, Constants.PADDLEHEIGHT);
    }
}