public class Angle {
    private int value;

    public Angle(int value) {
        this.value = value;
    }

    public int get() {
        return value;
    }

    public int inverse() {
        if (value > 0) return value - 180;
        else return value + 180;
    }
}
