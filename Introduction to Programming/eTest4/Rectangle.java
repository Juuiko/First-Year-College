package eTest4;

public class Rectangle  extends Shape {

	private String shapeName;
	private double area, width, length;
	
	public Rectangle(String name, double width, double length) {
		super(name);
		this.width = width;
		this.length = length;
		shapeName = name;
	}

	public double area() {
		area = width*length;
		return area;
	}
	
    public String toString()
    {
       return shapeName;
    }

}
