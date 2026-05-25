package cells.services;

public class Hospital extends Service {
    @Override
    public int getRadius() {
        return 3;
    }

    @Override
    public char getChar() {
        return 'D';
    }
}
