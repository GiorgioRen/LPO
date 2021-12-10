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

	/*
	 * Rettangolo con centro sull'origine degli assi
	 */
	private Rectangle(double width, double height) {
		// completare
		this.width = width;
		this.height = height;
	}

	private Rectangle(double width, double height, Point center) {
		// completare
		this.width = width;
		this.height = height;
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
		// completare
		return new Rectangle(width, height);
	}

	/*
	 * Factory method
	 */
	public static Rectangle ofWidthHeightCenter(double width, double height, Point center) {
		// completare
		return new Rectangle(width, height, center);
	}

	public void move(double dx, double dy) {
		// completare
		this.center.move(dx-this.center.getX(), dy-this.center.getY());
	}

	public void scale(double factor) {
		// completare
		if (factor > 0) {
			this.height = (this.height*factor);
			this.width = (this.width*factor);
		} else if (factor < 0) {
			this.height = (this.height/factor);
			this.width = (this.width/factor);
		};
	}

	public Point getCenter() {
		// completare
		return this.center;
	}

	public double perimeter() {
		// completare
		return ((this.width*2)+(this.height*2));
	}

	public double area() {
		// completare
		return (this.width*this.height);
	}
}