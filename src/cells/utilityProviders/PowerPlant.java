package cells.utilityProviders;

public class PowerPlant extends UtilityProvider {
    private static final int MAX_ELECTRICITY = 100;

    @Override
    public void tick() {//reset the capacity after every tick
        resetCapacity();
    }

    @Override
    public void resetCapacity() {
        currentCapacity = MAX_ELECTRICITY;
    }

    @Override
    public char getChar() {
        return 'P';
    }

    @Override
    public int getCapacity() {
        return MAX_ELECTRICITY;
    }
}