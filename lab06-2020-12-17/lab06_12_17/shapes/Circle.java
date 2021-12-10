package lab06_12_17.shapes;

public class Circle extends AbstractShape {
	/* invariant radius > 0 */
	private double radius = Circle.defaultSize;
    
        // private auxiliary method to be used in the constructors
	private void setRadius(double radius) {
		this.radius = Circle.requirePositive(radius);
	}

	/*
	 * Cerchio con centro sull'origine degli assi
	 */
	protected Circle(double radius) {
	    // to be completed
	}

	protected Circle(double radius, Point center) {
	    // to be completed
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
	    // to be completed
	}

	/*
	 * Factory method
	 */
	public static Circle ofRadiusCenter(double radius, Point center) {
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
