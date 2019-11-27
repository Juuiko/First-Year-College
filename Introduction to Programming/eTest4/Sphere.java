package eTest4;

public class Sphere extends Shape {
	
	private String shapeName;
	private double surfaceArea, radius;
	
	public Sphere(String name, double radius) {
		super(name);
		this.radius=radius;
		shapeName=name;
	}
	
	public double area() {
		surfaceArea=4*Math.PI*(radius*radius);
		return surfaceArea;
	}
	
    public String toString()
    {
       return shapeName;
    }

}
