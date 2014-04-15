package ar.edu.unq.components;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import rules.BallRule;
import rules.BlockCollisionRule;
import rules.ColisionRule;
import rules.LateralCollisionRule;
import rules.MovementRule;
import utils.Vector2D;
import ar.edu.unq.scenes.ArkanoidLevel;
import ar.edu.unq.utils.Property;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Circle;

public class Ball extends GameComponent<ArkanoidLevel> {

	private int radius = Property.BALL_RADIUS;
	private Color color = Property.BALL_COLOR;
	private double speed = Property.BALL_SPEED;
	private Vector2D direction;
	private List<BallRule> rules = new ArrayList<BallRule>();

	public Ball() {
		this.direction = new Vector2D(0.5, -0.5);
		this.setAppearance(new Circle(this.color, 2 * this.radius));
	}

	private void initRules() {
		this.rules.add(new ColisionRule(this.getScene().getBar()));
		this.rules.add(new LateralCollisionRule());
		this.rules.add(new BlockCollisionRule());
		this.rules.add(new MovementRule());
	}

	private List<BallRule> getRules() {
		if (this.rules.isEmpty()) {
			this.initRules();
		}
		return this.rules;
	}

	@Override
	public void onSceneActivated() {
		this.setX(this.getGame().getDisplayWidth() / 2);
		this.setY(this.getGame().getDisplayHeight() - 50);

		super.onSceneActivated();
	}

	@Override
	public void update(DeltaState deltaState) {
		Vector2D nuevaPosicion = this.direction.producto(this.speed * deltaState.getDelta()).suma(new Vector2D(this.getX(), this.getY()));
		for (BallRule rule : this.getRules()) {
			if (rule.mustApply(this, nuevaPosicion, this.getScene())) {
				rule.apply(this, nuevaPosicion, this.getScene());
				break;
			}
		}

		super.update(deltaState);
	}

	public void setDirection(Vector2D vector2d) {
		this.direction = vector2d.asVersor();
	}

	public Vector2D getDireccion() {
		return this.direction;
	}

	public double getDiameter() {
		return this.radius * 2;
	}

	public void inverseX() {
		this.setDirection(new Vector2D(-this.getDireccion().getX(), this.getDireccion().getY()));
	}

	public void inverseY() {
		this.setDirection(new Vector2D(this.getDireccion().getX(), -this.getDireccion().getY()));
	}
}
