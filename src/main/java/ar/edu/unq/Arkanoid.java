package ar.edu.unq;

import java.awt.Dimension;

import ar.edu.unq.components.Ball;
import ar.edu.unq.scenes.ArkanoidLevel;

import com.uqbar.vainilla.Game;

public class Arkanoid extends Game {

	private Dimension dimension;

	@Override
	protected void initializeResources() {
		this.dimension = new Dimension(400, 550);
	}

	@Override
	protected void setUpScenes() {
		ArkanoidLevel scene = new ArkanoidLevel();
		scene.addBall(new Ball());

		this.setCurrentScene(scene);
	}

	@Override
	public Dimension getDisplaySize() {
		return this.dimension;
	}

	@Override
	public String getTitle() {
		return "Arkanoid";
	}
}
