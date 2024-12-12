import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Draw extends Canvas {

	public Draw() {

	}

	public void set() {
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		setBackground(Color.WHITE);

		createCurve((Graphics2D) g);
	} // paint

	private void createCurve(Graphics2D g) {

	}
}
