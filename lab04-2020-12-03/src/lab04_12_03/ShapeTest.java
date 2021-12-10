package lab04_12_03;

public class ShapeTest {

	private static void checkAll(Shape[] shapes, Point[] centers, double totalArea, Shape max) {
		assert shapes.length == centers.length;
		for (int i = 0; i < shapes.length; i++)
			assert shapes[i].getCenter().overlaps(centers[i]);
		assert Shapes.totalArea(shapes) == totalArea;
		assert Shapes.max(shapes, AreaComparator.instance) == max;
	}

	public static void main(String[] args) {
		Shape s1 = Circle.ofRadiusCenter(2, new Point(1, 1));
		Shape s2 = Circle.ofRadius(1);
		Shape s3 = Rectangle.ofWidthHeightCenter(1, 2, new Point(2, 2));
		Shape[] shapes = { s1, s2, s3 };
		Point[] centers = new Point[3];
		double totalArea = Shapes.totalArea(shapes);
		Shapes.moveAll(shapes, -1, -1);
		for (int i = 0; i < centers.length; i++)
			centers[i] = shapes[i].getCenter();
		ShapeTest.checkAll(shapes, centers, totalArea, s1);
		Shapes.scaleAll(shapes, .5); // non deve spostare i centri delle figure!
		ShapeTest.checkAll(shapes, centers, totalArea * .25, s1);
		for (Shape s : shapes)
			s.getCenter().move(1, 1); // non deve spostare le figure!
		ShapeTest.checkAll(shapes, centers, totalArea * .25, s1);
	}

}
