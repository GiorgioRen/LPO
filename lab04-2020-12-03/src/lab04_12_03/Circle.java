package lab04_12_03;

public class Circle implements Shape {
	/* invariant radius > 0 */
	public static final double defaultSize = 1;
	private double radius = Circle.defaultSize;
	private final Point center = new Point();

	/*
	 * Cerchio con centro sull'origine degli assi
	 */    
	private Circle(double radius) {
		// completare
		this.radius = radius;
		this.center.move(0.0, 0.0);
	}

	private Circle(double radius, Point center) {
		// completare
		this.radius = radius;
		this.center.move(center.getX(), center.getY());
	}
    
	/*
	 * Cerchio con dimensioni di default e centro sull'origine degli assi
	 */
	public Circle() {
	}

	/*
	 * Factory method
	 */
	public static Circle ofRadius(double radius) {
		// completare
		return new Circle(radius);
	}

	/*
	 * Factory method
	 */
	public static Circle ofRadiusCenter(double radius, Point center) {
		// completare
		return new Circle(radius, center);
	}
	
	public void move(double dx, double dy) {
		// completare
		this.center.move(dx-this.center.getX(), dy-this.center.getY());
	}

	public void scale(double factor) {		
		// completare
		if (factor > 0) this.radius = (this.radius*factor);
		else if (factor < 0) this.radius = (this.radius/factor);
	}

	public Point getCenter() {
		// completare
		return this.center;
	}

	public double perimeter() {
		// completare
		return (Math.PI*this.radius*2);
	}

	public double area() {
		// completare
		return (Math.PI*this.radius*this.radius);
	}
}
