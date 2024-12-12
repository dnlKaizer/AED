public class Curve {

    private int nivel;

    public Curve(int nivel) {
        this.nivel = nivel;
    }

    public LinkedList<Point> createPoints() {
        // Criar lógica da curva de Peano aqui 
        LinkedList<Point> list = new LinkedList<>();

        list.add(new Point(50, 350));
        list.add(new Point(250, 350));
        list.add(new Point(450, 350));
        list.add(new Point(650, 350));

        return list;
    }
    
}
