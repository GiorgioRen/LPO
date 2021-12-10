package lab06_12_17.shapes;

/*
 * Implementa rettangoli con lati paralleli agli assi
 */
public class Rectangle extends AbstractShape {
	/* invariant width > 0 && height > 0 */
	private double width = Rectangle.defaultSize;
	private double height = Rectangle.defaultSize;

         // private auxiliary method to be used in the constructors   
	private void setWidthHeight(double width, double height) {
		this.width = Rectangle.requirePositive(width);
		this.height = Rectangle.requirePositive(height);
	}

	/*
	 * Rettangolo con centro sull'origine degli assi
	 */
	protected Rectangle(double width, double height) {
	    // to be completed
	}

	protected Rectangle(double width, double height, Point center) {
	    // to be completed
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
	    // to be completed
	}

	/*
	 * Factory method
	 */
	public static Rectangle ofWidthHeightCenter(double width, double height, Point center) {
	    // to be completed
	}

	@Override
	public void scale(double factor) {
	    // to be completed
	}

	@Override
	public double perimeter() {
	    // to be completed
	}

	@Override
	public double area() {
	    // to be completed
	}

}
