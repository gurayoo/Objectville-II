package cells;

public class EmptyCell implements Cell {
    @Override
    public char getChar() {
        return 'E';
    }

    @Override
    public void tick() {

    }
}
