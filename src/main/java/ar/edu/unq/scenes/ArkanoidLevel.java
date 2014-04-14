package ar.edu.unq.scenes;

import java.util.ArrayList;
import java.util.Collection;

import ar.edu.unq.components.Ball;

import com.uqbar.vainilla.GameScene;

public class ArkanoidLevel extends GameScene {

	private Collection<Ball> balls = new ArrayList<Ball>();

	public Collection<Ball> getBalls() {
		return this.balls;
	}

	public void addBall(Ball ball) {
		this.balls.add(ball);
		this.addComponent(ball);
	}
}
