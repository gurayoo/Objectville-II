package cells.utilityProviders;

public class WaterPumpingStation extends UtilityProvider {
    private static final int MAX_WATER = 100;

    @Override
    public void tick() {//reset the capacity after every tick
        resetCapacity();
    }
    @Override
    public void resetCapacity() {
        currentCapacity = MAX_WATER;
    }

    @Override
    public char getChar() {
        return 'W';
    }
}