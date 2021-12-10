package lab06_12_17.shapes;

/*
 * 2D Points implemented with Cartesian coordinates 
 */
public class PointClass implements Point {
	private double x;
	private double y;

	public PointClass() {
	}

	public PointClass(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public PointClass(Point p) {
		this(p.getX(), p.getY());
	}

	@Override
	public double getX() {
		return this.x;
	}

	@Override
	public double getY() {
		return this.y;
	}

	@Override
	public void move(double dx, double dy) {
		this.x += dx;
		this.y += dy;
	}

	@Override
	public boolean overlaps(Point p) {
		return this.x == p.getX() && this.y == p.getY();
	}

}
