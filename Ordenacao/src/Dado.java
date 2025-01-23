public class Dado implements Item {
    private int chave;

    public Dado(int chave) {
        this.chave = chave;
    }

    @Override
    public int getChave() {
        return this.chave;
    }

    @Override
    public int compara(Item item) {
        if (this.chave < item.getChave()) return -1;
        else if (this.chave > item.getChave()) return 1;
        else return 0;
    }
    
}
