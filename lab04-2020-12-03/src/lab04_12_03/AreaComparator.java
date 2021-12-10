package lab04_12_03;

import java.lang.module.ModuleDescriptor.Requires;
import java.util.concurrent.ExecutionException;

/*
 * Classe singleton, ossia con una sola istanza.
 * 
 * Confronta le figure basandosi sulle loro aree.
 */

public class AreaComparator implements ShapeComparator {

	public static final AreaComparator instance = new AreaComparator();

	private AreaComparator() {
	}

	/* requires shape1 != null && shape2 != null */    
	public int compare(Shape shape1, Shape shape2) {
		// completare
		double diff = shape1.area() - shape2.area();
		if (shape1 == null || shape2 == null) throw new RuntimeException("shape/s are null!");
		if (diff == 0) return 0;
		else if (diff < 0) return -1;
		else return 1;
	}

}
