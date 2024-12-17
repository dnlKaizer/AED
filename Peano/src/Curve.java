public class Curve {

    private int nivel;
    private LinkedList<Point> list;

    public Curve(int nivel) {
        this.nivel = nivel;
    }

    public LinkedList<Point> createPoints() {
        // Criar lógica da curva de Peano aqui 
        list = new LinkedList<>();
        int nivel = this.nivel;
        if (nivel >= 1) {
            list.add(new Point(50, 350));
            list.add(new Point(650, 350));
            exec(nivel - 1);
        }
        return list;
    }

    private void exec(int nivel) {
        if (nivel < 1) return;
        int size = list.size();
        for (int i = 0, m = 0; m < size - 1; i += 9, m++) {
            generate(list.get(i), list.get(i + 1), i+1);
        }
        exec(nivel - 1);
    }

    private void generate(Point pi, Point pf, int indexPi) {
        int r = pi.distance(pf) / 3;

        int thetaX = 0;
        int thetaY = 0;

        if (pf.getX() - pi.getX() != 0)
            thetaX = (pf.getX() - pi.getX()) / Math.abs(pf.getX() - pi.getX());
        else 
            thetaY = (pi.getY() - pf.getY()) / Math.abs(pi.getY() - pf.getY());

        int one = 90 - thetaX * 90 - 90 * Math.abs(thetaY) - 90 * thetaY;
        int two = 90 + thetaY * 90 - 90 * Math.abs(thetaX) - 90 * thetaX;

        int[] theta = {
            one,
            two,
            one,
            inverse(two),
            inverse(one),
            inverse(two),
            one,
            two
        }; 

        Point aux = pi;
        for (int i = 0; i < 8; i++) {
            aux = aux.create(r, theta[i]);
            list.add(aux, indexPi);
            indexPi++;
        }
    }

    private int inverse(int angle) {
        if (angle > 0) return angle - 180;
        else return angle + 180;
    } 
    
}
