package ar.edu.unq.components;

import java.awt.Color;

import ar.edu.unq.scenes.ArkanoidLevel;
import ar.edu.unq.utils.Property;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.events.constants.Key;

public class Bar extends GameComponent<ArkanoidLevel> {

	private int width = Property.BAR_WIDTH;
	private int height = Property.BAR_HEIGHT;
	private Color color = Property.BAR_COLOR;
	private double speed = Property.BAR_SPEED;
	private double maxX;
	private double minX;

	public Bar(double minX, double maxX) {
		this.minX = minX;
		this.maxX = (maxX - this.width);
		this.setAppearance(new com.uqbar.vainilla.appearances.Rectangle(this.color, this.width, this.height));
	}

	@Override
	public void onSceneActivated() {
		this.setX(this.getGame().getDisplayWidth() / 2);
		this.setY(this.getGame().getDisplayHeight() - 30);

		super.onSceneActivated();
	}

	@Override
	public void update(DeltaState deltaState) {
		int direction = 0;
		if (deltaState.isKeyBeingHold(Key.LEFT) && (this.getX() > this.minX)) {
			direction = -1;
		} else if (deltaState.isKeyBeingHold(Key.RIGHT) && (this.getX() < this.maxX)) {
			direction = 1;
		}

		this.setX(this.getX() + direction * this.speed * deltaState.getDelta());

		super.update(deltaState);
	}

}
