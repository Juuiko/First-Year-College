package eTest4;

public class PaintThings {

	public static void main(String[] args) {
		
		Paint paint = new Paint(350);
		Shape deck = new Rectangle("Rectangle",20.0,35.0);
		Shape bigBall = new Sphere("Sphere",15.0);
		Shape tank = new Cylinder("Cylinder",10.0,30.0);
		
		System.out.println("Computing the amount for " + deck.toString() + " of length 20.0 and width 35.0");
		System.out.println("Amount of paint reqired is " + paint.amount(deck));
		System.out.println("Computing the amount for " + bigBall.toString() + " of radius 15.0");
		System.out.println("Amount of paint reqired is " + paint.amount(bigBall));
		System.out.println("Computing the amount for " + tank.toString() + " of radius 10.0 and height 30.0");
		System.out.println("Amount of paint reqired is " + paint.amount(tank));
	}

}
