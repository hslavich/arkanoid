package rules;

import utils.Vector2D;
import ar.edu.unq.components.Ball;
import ar.edu.unq.components.Block;
import ar.edu.unq.scenes.ArkanoidLevel;

import com.uqbar.vainilla.colissions.Bounds;
import com.uqbar.vainilla.colissions.CollisionDetector;

public class BlockCollisionRule implements BallRule {

	private Block blockDetected;

	@Override
	public boolean mustApply(Ball ball, Vector2D newpos, ArkanoidLevel scene) {
		for (Block block : scene.getBlocks()) {
			if (this.collides(ball, block)) {
				this.blockDetected = block;
				return true;
			}
		}
		return false;
	}

	@Override
	public void apply(Ball ball, Vector2D newpos, ArkanoidLevel scene) {
		this.blockDetected.collision();
		if (this.collidesWithBounds(ball, this.blockDetected.getTopBounds())) {
			ball.setY(this.blockDetected.getTopBounds().getTop() - 1 - ball.getAppearance().getHeight());
			ball.inverseY();
		} else if (this.collidesWithBounds(ball, this.blockDetected.getLeftBounds())) {
			ball.setX(this.blockDetected.getLeftBounds().getLeft() - 1);
			ball.inverseX();
		} else if (this.collidesWithBounds(ball, this.blockDetected.getRightBounds())) {
			ball.setX(this.blockDetected.getRightBounds().getRight() + 1 - ball.getAppearance().getWidth());
			ball.inverseX();
		} else if (this.collidesWithBounds(ball, this.blockDetected.getBottomBounds())) {
			ball.setY(this.blockDetected.getBottomBounds().getBottom() + 1);
			ball.inverseY();
		}
	}

	private boolean collidesWithBounds(Ball ball, Bounds bounds) {
		return CollisionDetector.INSTANCE.collidesCircleAgainstRect(
				ball.getX(), ball.getY(), ball.getDiameter() / 2,
				bounds.getLeft(), bounds.getTop(), bounds.getWidth(), bounds.getHeight());
	}

	private boolean collides(Ball ball, Block block) {
		return CollisionDetector.INSTANCE.collidesCircleAgainstRect(
				ball.getX(), ball.getY(), ball.getDiameter() / 2,
				block.getX(), block.getY(), block.getAppearance().getWidth(), block.getAppearance().getHeight());
	}

}
