package rules;

import utils.Vector2D;
import ar.edu.unq.components.Ball;
import ar.edu.unq.scenes.ArkanoidLevel;

public class LateralCollisionRule implements BallRule {

	@Override
	public boolean mustApply(Ball ball, Vector2D newpos, ArkanoidLevel scene) {
		return (newpos.getX() <= scene.getLeftLimit()) ||
				((newpos.getX() + (ball.getDiameter())) >= scene.getRightLimit()) ||
				(newpos.getY() <= scene.getTopLimit());
	}

	@Override
	public void apply(Ball ball, Vector2D newpos, ArkanoidLevel scene) {
		if (newpos.getX() <= scene.getLeftLimit()) {
			ball.setX(scene.getLeftLimit());
			ball.inverseX();
		} else if ((newpos.getX() + (ball.getDiameter())) >= scene.getRightLimit()) {
			ball.setX(scene.getGame().getDisplayWidth() - ball.getDiameter());
			ball.inverseX();
		} else if (newpos.getY() <= scene.getTopLimit()) {
			ball.setY(scene.getTopLimit());
			ball.inverseY();
		}
	}

}
