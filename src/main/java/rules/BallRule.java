package rules;

import utils.Vector2D;
import ar.edu.unq.components.Ball;
import ar.edu.unq.scenes.ArkanoidLevel;

public interface BallRule {

	boolean mustApply(Ball pelota, Vector2D nuevaPosicion, ArkanoidLevel scene);
	void apply(Ball pelota, Vector2D nuevaPosicion, ArkanoidLevel scene);
}
