package graphics.screens;
import processing.core.PApplet;

public class Screen {
    private int width;
    private int height;
    private int backgroundColor;
    public Screen(PApplet p, int width, int height, int backgroundColor) {
        this.width = width;
        this.height = height;
        this.backgroundColor = backgroundColor;
    }
}
