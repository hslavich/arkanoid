package ar.edu.unq.scenes;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collection;

import ar.edu.unq.components.Ball;
import ar.edu.unq.components.Bar;
import ar.edu.unq.components.Block;

import com.uqbar.vainilla.GameScene;

public class ArkanoidLevel extends GameScene {

	private Collection<Ball> balls = new ArrayList<Ball>();
	private Collection<Block> blocks = new ArrayList<Block>();
	private Bar bar;

	public ArkanoidLevel(Dimension dimension) {
		this.bar = new Bar();
		this.addComponent(this.bar);

		this.createBlocks();
	}

	private void createBlocks() {
		this.addBlock(new Block(Color.BLACK, 100, 100));
	}

	public Collection<Ball> getBalls() {
		return this.balls;
	}

	public void addBall(Ball ball) {
		this.balls.add(ball);
		this.addComponent(ball);
	}

	public void addBlock(Block block) {
		this.blocks.add(block);
		this.addComponent(block);
	}

	public Bar getBar() {
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
