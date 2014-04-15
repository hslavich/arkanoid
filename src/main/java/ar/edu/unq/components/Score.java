package ar.edu.unq.components;

import java.awt.Color;
import java.awt.Font;

import ar.edu.unq.scenes.ArkanoidLevel;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Label;

public class Score extends GameComponent<ArkanoidLevel> {

	private int score;

	public Score(double x, double y, Color color) {
		super(new Label(new Font("verdana", Font.BOLD, 30), color, "0"), x, y);
		this.score = 0;
	}

	@Override
	public void update(DeltaState deltaState) {
		((Label) this.getAppearance()).setText(Integer.toString(this.score));
		super.update(deltaState);
	}

	public void sumScore(int score) {
		this.score += score;
	}

	public void reset() {
		this.score = 0;
	}

}
