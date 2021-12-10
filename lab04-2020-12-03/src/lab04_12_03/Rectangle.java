package lab04_12_03;

/*
 * Implementa rettangoli con lati paralleli agli assi
 */
public class Rectangle implements Shape {
	/* invariant width > 0 && height > 0 */
	public static final double defaultSize = 1;
	private double width = Rectangle.defaultSize;
	private double height = Rectangle.defaultSize;

	private final Point center = new Point();

	private static double requirePositive(double size) {
		if (size <= 0)
			throw new IllegalArgumentException();
		return size;
	}

	/*
	 * Rettangolo con centro sull'origine degli assi
	 */
	private Rectangle(double width, double height) {
		this.width = Rectangle.requirePositive(width);
		this.height = Rectangle.requirePositive(height);
	}

	private Rectangle(double width, double height, Point center) {
		this(width, height);
		this.center.move(center.getX(), center.getY());
	}

	/*
	 * Rettangolo con dimensioni di default e centro sull'origine degli assi
	 */
	public Rectangle() {
	}

	/*
	 * Factory method
	 */
	public static Rectangle ofWidthHeight(double width, double height) {
		return new Rectangle(width, height);
	}

	/*
	 * Factory method
	 */
	public static Rectangle ofWidthHeightCenter(double width, double height, Point center) {
		return new Rectangle(width, height, center);
	}

	public void move(double dx, double dy) {
		this.center.move(dx, dy);
	}

	public void scale(double factor) {
		if (factor > 0) {
			this.height = (this.height*factor);
			this.width = (this.width*factor);
		} else if (factor < 0) {
			this.height = (this.height/factor);
			this.width = (this.width/factor);
		};
	}

	public Point getCenter() {
		return this.center;
	}

	public double perimeter() {
		return 2 * (this.width + this.height);
	}

	public double area() {
		return this.width * this.height;
	}
}
