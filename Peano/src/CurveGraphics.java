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
		createCurve((Graphics2D) g);
	} // paint

	private void createCurve(Graphics2D g) {
		for (int i = 0; i < list.size() - 1;) {
			Point pi = list.get(i);
			i++;
			Point pf = list.get(i);
			g.drawLine(pi.getX(), pi.getY(), pf.getX(), pf.getY());
		}
	}

}
