package eTest4;

public class Cylinder  extends Shape {
	
	private String shapeName;
	private double surfaceArea, radius, height;

	public Cylinder(String name, double radius, double height) {
		super(name);
		this.radius = radius;
		this.height = height;
		shapeName=name;
	}

	public double area() {
		surfaceArea = Math.PI*(radius*radius)*height;
		return surfaceArea;
	}
	
    public String toString()
    {
       return shapeName;
    }

}
