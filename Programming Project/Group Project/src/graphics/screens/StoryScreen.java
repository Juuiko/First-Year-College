package graphics.screens;
import processing.core.PApplet;

class StoryScreen extends Screen{
    private Screen comments;
    public StoryScreen(PApplet p, int width, int height, int backgroundColor){
        super(p, width, height, backgroundColor);
        //comments = new Screen(this.p, this.width - 160, this.height - 90, this.backgroundColor);
    }
}