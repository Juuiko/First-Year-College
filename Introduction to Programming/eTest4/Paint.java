package eTest4;

public class Paint {
	
	private double coverage;
	
	public Paint(double coverage) {
		this.coverage = coverage;
	}
	
	public double amount(Shape shape) {
		double amount = shape.area()/coverage;
		return amount;
	}
}
