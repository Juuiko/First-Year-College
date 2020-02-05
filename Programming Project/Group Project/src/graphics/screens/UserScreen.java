package graphics.screens;
import processing.core.PApplet;

class UserScreen extends Screen{
    private Screen comments;
    private Screen posts;
    private int points;
    public UserScreen(PApplet p, int width, int height, int backgroundColor){
        super(p, width, height, backgroundColor);
        //comments = new Screen(this.p, this.width - 160, this.height - 90, this.backgroundColor);
        //posts = new Screen(this.p, this.width - 160, this.height - 90, this.backgroundColor);
    }
}