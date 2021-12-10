package lab04_12_03;

public class Shapes {

	/*
	 * restituisce la prima figura maggiore o uguale alle altre in shapes rispetto al comparator comp,
	 * null se shapes e` vuoto
	 * requires shapes != null && comp != null
	 */    
	public static Shape max(Shape[] shapes, ShapeComparator comp) {
	    // completare
	}

	/*
	 * trasla tutte le figure lungo il vettore (dx,dy)
	 * requires shapes != null
	 */
	public static void moveAll(Shape[] shapes, double dx, double dy) {
	    // completare
	}
    
	/*
	 * scala tutte le figure del fattore factor, senza traslare il loro centro
	 * requires shapes != null && factor > 0
	 */
	public static void scaleAll(Shape[] shapes, double factor) {
	    // completare
	}
    
	/*
	 * restituisce l'area totale di tutte le figure in shapes
	 * requires shapes != null
	 */
	public static double totalArea(Shape[] shapes) {
	    // completare
	}
}
