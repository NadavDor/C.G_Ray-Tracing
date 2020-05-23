package edu.cg.scene.camera;

import edu.cg.UnimplementedMethodException;
import edu.cg.algebra.Point;
import edu.cg.algebra.Vec;

public class PinholeCamera {
	// TODO Add your fields
	Point cameraPosition; //P_0
	Point centerPosition; // P_c
	Vec towardsVec;
	Vec upVec;
	Vec vecRight;
	Vec vecUp;

	double distanceToPlain;
	double plainW;
	int height = 200;
	int width = 200;
	double ratio; // pixel width
	double viewAngle = 90;

	/**
	 * Initializes a pinhole camera model with default resolution 200X200 (RxXRy)
	 * and View Angle 90.
	 * 
	 * @param cameraPosition  - The position of the camera.
	 * @param towardsVec      - The towards vector of the camera (not necessarily
	 *                        normalized).
	 * @param upVec           - The up vector of the camera.
	 * @param distanceToPlain - The distance of the camera (position) to the center
	 *                        point of the image-plain.
	 * 
	 */
	public PinholeCamera(Point cameraPosition, Vec towardsVec, Vec upVec, double distanceToPlain) {
		this.cameraPosition = cameraPosition;
		this.towardsVec = towardsVec;
		this.upVec = upVec;
		this.distanceToPlain = distanceToPlain;

        this.centerPosition = cameraPosition.add(distanceToPlain, towardsVec);
        this.vecRight = towardsVec.cross(upVec).normalize();
        this.vecUp = vecRight.cross(towardsVec).normalize();

    }

	/**
	 * Initializes the resolution and width of the image.
	 * 
	 * @param height    - the number of pixels in the y direction.
	 * @param width     - the number of pixels in the x direction.
	 * @param viewAngle - the view Angle.
	 */
	public void initResolution(int height, int width, double viewAngle) {
		this.height = height;
		this.width = width;
		this.viewAngle = viewAngle;

        this.plainW = Math.tan(viewAngle / 2) * distanceToPlain * 2;
        this.ratio = plainW / width;
	}

	/**
	 * Transforms from pixel coordinates to the center point of the corresponding
	 * pixel in model coordinates.
	 * 
	 * @param x - the pixel index in the x direction.
	 * @param y - the pixel index in the y direction.
	 * @return the middle point of the pixel (x,y) in the model coordinates.
	 */
	public Point transform(int x, int y) {
        Vec uv = vecUp.mult(y - height/2);
        Vec rv = vecRight.mult(x - width/2);

        return centerPosition.add(rv.add(uv.neg()));
		//throw new UnimplementedMethodException("PinholeCamera.transform is not implemented.");
	}

	/**
	 * Returns the camera position
	 * 
	 * @return a new point representing the camera position.
	 */
	public Point getCameraPosition() {
        return new Point(cameraPosition.x, cameraPosition.y, cameraPosition.z);
		//throw new UnimplementedMethodException("PinholeCamera.getCameraPosition");
	}
}
