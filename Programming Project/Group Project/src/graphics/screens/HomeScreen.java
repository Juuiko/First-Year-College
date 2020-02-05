package graphics.screens;
import processing.core.PApplet;

class HomeScreen extends Screen{
    private Screen stories;
    public HomeScreen(PApplet p, int width, int height, int backgroundColor){
        super(p, width, height, backgroundColor);
        //stories = new Screen(this.p, this.width - 160, this.height - 90, this.backgroundColor);
    }
}