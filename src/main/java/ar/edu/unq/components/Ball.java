package ar.edu.unq.components;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import rules.BallRule;
import rules.ColisionRule;
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
	private double i, j;
	
	private Vector2D direction;
	private List<BallRule> rules = new ArrayList<BallRule>();
	

	public Ball() {
		this.direction = new Vector2D(10, 10).asVersor();
		this.setAppearance(new Circle(this.color, 2 * this.radius));
	}
	
	private void initRules() {
		this.rules.add(new ColisionRule(this.getScene().getBar()));	
	}	
	
	private List<BallRule> getRules() {
		if(this.rules.isEmpty()) {
			this.initRules();
		}
		return this.rules;
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
		
		
		if (this.getX() <= this.getScene().getLeftLimit()) {
			this.setX(0);
			this.i = -this.i;
		} else if ((this.getX() + (this.radius * 2)) >= this.getScene().getRightLimit()) {
			this.setX(this.getGame().getDisplayWidth() - (this.radius * 2));
			this.i = -this.i;
		} else if (this.getY() <= this.getScene().getTopLimit()) {
			this.setY(0);
			this.j = -this.j;
		} else if ((this.getY() + (this.radius * 2)) >= this.getGame().getDisplayHeight()) {
			this.setY(this.getGame().getDisplayHeight() - (this.radius * 2));
			this.j = -this.j;
		}
		
		Vector2D nuevaPosicion = this.direction.producto(speed * deltaState.getDelta()).suma(new Vector2D(this.getX(), this.getY()));
		for(BallRule rule : this.getRules()) {
			if(rule.mustApply(this, nuevaPosicion, this.getScene())) {
				rule.apply(this, nuevaPosicion, this.getScene());
				break;
			}
		}		

		super.update(deltaState);
	}
	
	public void faster() {
		this.speed = this.speed + Property.BALL_SPEED_STEP;
	}	
	
	public void setDireccion(Vector2D vector2d) {
		this.direction = vector2d.asVersor();
	}

	public Vector2D getDireccion() {
		return this.direction;
	}	
}
