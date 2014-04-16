package ar.edu.unq.scenes;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collection;

import ar.edu.unq.components.Ball;
import ar.edu.unq.components.Bar;
import ar.edu.unq.components.Block;
import ar.edu.unq.components.Score;
import ar.edu.unq.utils.Property;

import com.uqbar.vainilla.GameScene;

public class ArkanoidLevel extends GameScene {

	private Collection<Ball> balls = new ArrayList<Ball>();
	private Collection<Block> blocks = new ArrayList<Block>();
	private Bar bar;
	private Score score;

	public ArkanoidLevel(Dimension dimension) {
		this.bar = new Bar();
		this.addComponent(this.bar);

		this.score = new Score(Property.SCORE_X, Property.SCORE_Y, Color.BLACK);
		this.addComponent(this.score);

		this.createBlocks();
	}

	private void createBlocks() {
		Color[] colors = new Color[] { Color.YELLOW, Color.GREEN, Color.DARK_GRAY, Color.MAGENTA };
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 10; j++) {
				this.addBlock(new Block(colors[(j + i) % colors.length], j * Property.BLOCK_WIDTH + 1, 100 + i * Property.BLOCK_HEIGHT + 1, 1));
			}
		}
		Color[] colors2 = new Color[] { Color.BLACK, Color.RED};
		for (int i = 0; i < 10; i++) {
			this.addBlock(new Block(colors2[(i) % colors2.length], i * Property.BLOCK_WIDTH + 1, 220 + Property.BLOCK_HEIGHT + 1, 2));
		}
	}

	public Collection<Ball> getBalls() {
		return this.balls;
	}

	public Collection<Block> getBlocks() {
		return this.blocks;
	}

	public void addBall(Ball ball) {
		this.balls.add(ball);
		this.addComponent(ball);
	}

	public void addBlock(Block block) {
		this.blocks.add(block);
		this.addComponent(block);
	}

	public void removeBlock(Block block) {
		this.blocks.remove(block);
		this.removeComponent(block);
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

	public void updateScore() {
		this.score.sumScore(Property.SCORE_BLOCK);
	}
}
