package edu.cg.scene.objects;

import edu.cg.UnimplementedMethodException;
import edu.cg.algebra.Hit;
import edu.cg.algebra.Point;
import edu.cg.algebra.Ray;
import edu.cg.algebra.Vec;

public class Sphere extends Shape {
	private Point center;
	private double radius;

	public Sphere(Point center, double radius) {
		this.center = center;
		this.radius = radius;
	}

	public Sphere() {
		this(new Point(0, -0.5, -6), 0.5);
	}

	@Override
	public String toString() {
		String endl = System.lineSeparator();
		return "Sphere:" + endl +
				"Center: " + center + endl +
				"Radius: " + radius + endl;
	}

	public Sphere initCenter(Point center) {
		this.center = center;
		return this;
	}

	public Sphere initRadius(double radius) {
		this.radius = radius;
		return this;
	}

	@Override
	public Hit intersect(Ray ray) {
		Vec V = ray.direction();
		Point P0 = ray.source();
		// a = Vx^2 + Vy^2 +Vz^2
		double a = V.dot(V);
		// b = 2Vx(P0x - Qx) + 2Vy(p0y - Qy) + 2Vx(P0z - Qz))
		double b = (V.mult(2)).dot(P0.sub(this.center));
		//c = (
		Vec tempVector = P0.sub(this.center);
		double c = tempVector.dot(tempVector) - this.radius * this.radius;

		//Find quadratic Equation Roots
		//root = t in the equation
		double secondRoot = 0, firstRoot = 0, root;
		double determinant = (b * b) - (4 * a * c);
		double sqrt = Math.sqrt(determinant);
		if (determinant >= 0) {
			if (determinant > 0) {
				firstRoot = (-b + sqrt) / (2 * a);
				secondRoot = (-b - sqrt) / (2 * a);
				root = Math.min(firstRoot, secondRoot);
			} else {
				root = ((-b + sqrt) / (2 * a));
			}
		}else{
			return null;
		}
		Point intersection = P0.add(V.mult(root));
		Vec centerToIntersection = new Vec(intersection.x - this.center.x, intersection.y - this.center.y, intersection.z - this.center.z);
		Vec normalToIntersection = centerToIntersection.normalize();
		return new Hit(root,normalToIntersection);
	}

}
