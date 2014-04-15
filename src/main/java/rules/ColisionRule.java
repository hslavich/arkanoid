package rules;

import utils.Vector2D;
import ar.edu.unq.components.Ball;
import ar.edu.unq.components.Bar;
import ar.edu.unq.scenes.ArkanoidLevel;

import com.uqbar.vainilla.colissions.CollisionDetector;

public class ColisionRule implements BallRule {

	private static double anguloDelta = 0.1;
	private static double anguloMayor = -Math.PI / 3 + anguloDelta;
	private static double anguloMenor = Math.PI / 3 - anguloDelta;
	private Bar bar;

	public ColisionRule(Bar bar) {
		super();
		this.bar = bar;
	}

	@Override
	public boolean mustApply(Ball ball, Vector2D nuevaPosicion, ArkanoidLevel scene) {
		return this.colisiona(this.bar, ball, nuevaPosicion);
	}

	private boolean colisiona(Bar bar, Ball ball, Vector2D nuevaPosicion) {
		return CollisionDetector.INSTANCE.collidesCircleAgainstRect(
				nuevaPosicion.getX(), nuevaPosicion.getY(), ball
						.getAppearance().getWidth() / 2, bar.getX(),
				bar.getY(), bar.getAppearance().getWidth(), bar
						.getAppearance().getHeight());
	}

	@Override
	public void apply(Ball ball, Vector2D nuevaPosicion, ArkanoidLevel scene) {
		double puntoDeColision = this.getPuntoColision(this.bar, ball,
				nuevaPosicion);

		double signoY = Math.signum(ball.getDireccion().getY());

		double anguloNuevo = ((anguloMenor - anguloMayor) / this.bar
				.getAppearance().getWidth())
				* puntoDeColision
				+ anguloMayor;
		// aprovecho e invierto el signo que traia Y con el truquito de
		// multiplicarlo por -1
		ball.setDirection(new Vector2D(Math.sin(anguloNuevo), -1 * signoY
				* Math.cos(anguloNuevo)));

		// pelota.setX(nuevaPosicion.getX());
		ball.setY(signoY > 0 ? this.bar.getY()
				- ball.getAppearance().getHeight() - 1 : this.bar.getY()
				+ this.bar.getAppearance().getHeight() + 1);
	}

	private double getPuntoColision(Bar bar, Ball ball, Vector2D nuevaPosicion) {
		if (ball.getX() > bar.getX()
				&& ball.getX() + ball.getAppearance().getWidth() < bar.getX() + bar.getAppearance().getWidth()) {
			double xCentroPelota = nuevaPosicion.getX() + ball.getAppearance().getWidth() / 2;
			return xCentroPelota - bar.getX();
		} else if (ball.getX() < bar.getX()) {
			return 0;
		} else {
			return bar.getAppearance().getWidth();
		}
	}

}
