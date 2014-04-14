package ar.edu.unq;

import java.awt.Color;
import java.awt.Dimension;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.Game;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.appearances.Circle;

public class Arkanoid extends Game {

	private Dimension dimension;

	@Override
	protected void initializeResources() {
		dimension = new Dimension(800, 600);

	}

	@Override
	protected void setUpScenes() {
		GameScene scene = new GameScene();
		final int diameter = 50;
		scene.addComponent(new GameComponent<GameScene>(new Circle(Color.blue, diameter), 0, 290){
			int direction = 1;
			double velocity = 100; 
			@Override
			public void update(DeltaState deltaState) {
				this.setX(this.getX() + direction*velocity*deltaState.getDelta());
				if(this.getX() <= 0 ) {
					this.setX(0);
					direction = -direction;
				} else if(this.getX() + diameter >=dimension.width) {
					this.setX(dimension.width - diameter);
					direction = -direction;					
				}
			}
		});
		this.setCurrentScene(scene);
	}

	@Override
	public Dimension getDisplaySize() {
		return dimension;
	}

	@Override
	public String getTitle() {
		return "Arkanoid";
	}


}
