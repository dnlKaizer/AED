import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class CurveGraphics extends Canvas {

	private LinkedList<Point> list;

	public CurveGraphics() {
		this.list = new LinkedList<>();
	}
	
	public void set(LinkedList<Point> list) {
		this.list = list;
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		setBackground(Color.WHITE);

		createCurve((Graphics2D) g, 0);
	} // paint

	private void createCurve(Graphics2D g, int index) {
		if (index >= list.size()) return;

		Point pi = list.get(index);
		index++;
		Point pf = list.get(index);
		g.drawLine(pi.getX(), pi.getY(), pf.getX(), pf.getY());
		createCurve(g, index);
	}

}
