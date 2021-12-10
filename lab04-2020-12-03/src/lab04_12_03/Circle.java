package lab04_12_03;

public class Circle implements Shape {
	/* invariant radius > 0 */
	public static final double defaultSize = 1;
	private double radius = Circle.defaultSize;
	private final Point center = new Point();

	private static double requirePositive(double size) {
		if (size <= 0)
			throw new IllegalArgumentException();
		return size;
	}

	/*
	 * Cerchio con centro sull'origine degli assi
	 */
	private Circle(double radius) {
		this.radius = Circle.requirePositive(radius);
	}

	private Circle(double radius, Point center) {
		this(radius);
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
		return new Circle(radius);
	}

	/*
	 * Factory method
	 */
	public static Circle ofRadiusCenter(double radius, Point center) {
		return new Circle(radius, center);
	}
	
	public void move(double dx, double dy) {
		this.center.move(dx, dy);
	}

	public void scale(double factor) {		
		this.radius *= Circle.requirePositive(factor);
	}

	public Point getCenter() {
		return new Point(center);
	}

	public double perimeter() {
		return Math.PI * this.radius * 2;
	}

	public double area() {
		return Math.PI * this.radius * this.radius;
	}
}
