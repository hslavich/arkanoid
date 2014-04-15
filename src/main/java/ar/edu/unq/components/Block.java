package ar.edu.unq.components;

import java.awt.Color;

import ar.edu.unq.scenes.ArkanoidLevel;
import ar.edu.unq.utils.Property;

import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Rectangle;

public class Block extends GameComponent<ArkanoidLevel> {

	public Block(Color color, double x, double y) {
		super(x, y);
		this.setAppearance(new Rectangle(color, Property.BLOCK_WIDTH, Property.BLOCK_HEIGHT));
	}
}
