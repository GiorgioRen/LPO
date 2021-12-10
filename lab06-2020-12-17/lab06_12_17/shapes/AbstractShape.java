package lab06_12_17.shapes;

public abstract class AbstractShape implements Shape {

	private final Point center = new PointClass();

	protected static final double defaultSize = 1;

	protected static double requirePositive(double size) {
	    // to be completed
	}

	protected AbstractShape(Point center) {
	    // to be completed
	}

	protected AbstractShape() {
	}

	@Override
	public void move(double dx, double dy) {
	    // to be completed
	}

	@Override
	public Point getCenter() {
	    // to be completed
	}

}
