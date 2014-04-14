package ar.edu.unq.components;

import java.awt.Color;

import ar.edu.unq.scenes.ArkanoidLevel;
import ar.edu.unq.utils.Property;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Circle;

public class Ball extends GameComponent<ArkanoidLevel> {

	private int radius = Property.BALL_RADIUS;
	private Color color = Property.BALL_COLOR;
	private double speed = Property.BALL_SPEED;
	private double i, j;

	public Ball() {
		this.setAppearance(new Circle(this.color, 2 * this.radius));
	}

	@Override
	public void onSceneActivated() {
		this.setX(this.getGame().getDisplayWidth() / 2);
		this.setY(this.getGame().getDisplayHeight() - 50);
		this.i = 0.5;
		this.j = -0.5;

		super.onSceneActivated();
	}

	@Override
	public void update(DeltaState deltaState) {
		double advanced = this.speed * deltaState.getDelta();
		this.move(this.i * advanced, this.j * advanced);

		if (this.getX() <= 0) {
			this.setX(0);
			this.i = -this.i;
		} else if ((this.getX() + (this.radius * 2)) >= this.getGame().getDisplayWidth()) {
			this.setX(this.getGame().getDisplayWidth() - (this.radius * 2));
			this.i = -this.i;
		} else if (this.getY() <= 0) {
			this.setY(0);
			this.j = -this.j;
		} else if ((this.getY() + (this.radius * 2)) >= this.getGame().getDisplayHeight()) {
			this.setY(this.getGame().getDisplayHeight() - (this.radius * 2));
			this.j = -this.j;
		}

		super.update(deltaState);
	}
}
