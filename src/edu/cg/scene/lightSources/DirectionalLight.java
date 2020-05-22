package edu.cg.scene.lightSources;

import edu.cg.UnimplementedMethodException;
import edu.cg.algebra.Point;
import edu.cg.algebra.Ray;
import edu.cg.algebra.Vec;
import edu.cg.scene.objects.Surface;

public class DirectionalLight extends Light {
	private Vec direction = new Vec(0, -1, -1);

	public DirectionalLight(Vec dirVec, Vec intensity) {
		this.direction = dirVec.normalize();
		this.intensity = intensity;
	}

	public DirectionalLight initDirection(Vec direction) {
		this.direction = direction.normalize();
		return this;
	}

	@Override
	public String toString() {
		String endl = System.lineSeparator();
		return "Directional Light:" + endl + super.toString() + "Direction: " + direction + endl;
	}

	@Override
	public DirectionalLight initIntensity(Vec intensity) {
		return (DirectionalLight) super.initIntensity(intensity);
	}

	@Override
	public Ray rayToLight(Point fromPoint) {
		// TODO: Implement this.
		throw new UnimplementedMethodException("DirectionalLight.rayToLight");
	}

	@Override
	public boolean isOccludedBy(Surface surface, Ray rayToLight) {
		// TODO: Implement this.
		throw new UnimplementedMethodException("DirectionalLight.isOccludedBy");
	}

	@Override
	public Vec intensity(Point hittingPoint, Ray rayToLight) {
		// TODO: Implement this.
		throw new UnimplementedMethodException("DirectionalLight.intensity");
	}

}
