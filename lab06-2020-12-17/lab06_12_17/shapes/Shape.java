package lab06_12_17.shapes;

public interface Shape {
	/*
	 * restituisce un punto con le stesse coordinate del centro della figura, ma diverso come oggetto
	 */
	Point getCenter();

	/*
	 * restituisce il perimetro della figura
	 */
	double perimeter();

	/*
	 * restituisce l'area della figura
	 */
	double area();

	/*
	 * trasla la figura lungo il vettore (dx,dy)
	 */
	void move(double dx, double dy);

	/*
	 * scala la figura del fattore factor > 0, senza traslare il suo centro
	 */
	void scale(double factor);

}
