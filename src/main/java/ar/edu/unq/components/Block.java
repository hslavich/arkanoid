package ar.edu.unq.components;

import java.awt.Color;

import ar.edu.unq.scenes.ArkanoidLevel;
import ar.edu.unq.utils.Property;

import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Rectangle;
import com.uqbar.vainilla.colissions.Bounds;

public class Block extends GameComponent<ArkanoidLevel> {
	
	private int lives;

	public Block(Color color, double x, double y, int lives) {
		super(x, y);
		this.lives = lives;
		this.setAppearance(new Rectangle(color, Property.BLOCK_WIDTH, Property.BLOCK_HEIGHT));
	}

	public Bounds getLeftBounds() {
		return new Bounds(this.getX(), this.getY(), 1, this.getAppearance().getHeight());
	}

	public Bounds getRightBounds() {
		double width = this.getAppearance().getWidth();
		return new Bounds(this.getX() + width, this.getY(), 1, this.getAppearance().getHeight());
	}

	public Bounds getTopBounds() {
		return new Bounds(this.getX(), this.getY(), this.getAppearance().getWidth(), 1);
	}

	public Bounds getBottomBounds() {
		double height = this.getAppearance().getHeight();
		return new Bounds(this.getX(), this.getY() + height, this.getAppearance().getWidth(), 1);
	}

	public void collision() {
		this.setLives(this.getLives() - 1);
		if(this.lives == 0){
			this.getScene().updateScore();
			this.getScene().removeBlock(this);
		}
	}
	
	public void setLives(int l){
		this.lives = l;
	}
	
	public int getLives(){
		return this.lives;
	}
}
