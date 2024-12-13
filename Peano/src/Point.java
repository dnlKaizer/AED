public class Point {
    private int x;
    private int y; 

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point create(int r, int theta) {
        
        int nx = (int) (x + r * Math.cos(Math.toRadians(theta)));
        int ny = (int) (y + r * Math.sin(Math.toRadians(theta)));
        
        return new Point(nx, ny);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int distance(Point p) {
        return (int) Math.round(Math.sqrt(Math.pow(x - p.getX(), 2) + Math.pow(y - p.getY(), 2)));
    }

    @Override
    public String toString() {
        return "[" + x + "," + y + "]";
    }

}
