package ar.edu.unq.scenes;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collection;

import ar.edu.unq.components.Ball;
import ar.edu.unq.components.Bar;

import com.uqbar.vainilla.GameScene;

public class ArkanoidLevel extends GameScene {

	private Collection<Ball> balls = new ArrayList<Ball>();

	private Bar bar;

	public ArkanoidLevel(Dimension dimension) {
		this.bar = new Bar();

		this.addComponent(this.bar);
	}

	public Collection<Ball> getBalls() {
		return this.balls;
	}

	public void addBall(Ball ball) {
		this.balls.add(ball);
		this.addComponent(ball);
	}
	
	public Bar getBar(){
		return this.bar;
	}
	
	public double getLeftLimit() {
		return 0;
	}
	
	public double getRightLimit() {
		return this.getGame().getDisplayWidth();
	}

	public double getTopLimit() {
		return 0;
	}
}
