package rules;

import utils.Vector2D;
import ar.edu.unq.components.Ball;
import ar.edu.unq.scenes.ArkanoidLevel;

public class MovementRule implements BallRule {

	@Override
	public boolean mustApply(Ball ball, Vector2D newpos, ArkanoidLevel scene) {
		return true;
	}

	@Override
	public void apply(Ball ball, Vector2D newpos, ArkanoidLevel scene) {
		ball.setX(newpos.getX());
		ball.setY(newpos.getY());
	}
}
