package cells.utilityProviders;

public class WaterPumpingStation extends UtilityProvider {
    private static final int MAX_WATER = 100;

    @Override
    public int getCapacity() {
        return MAX_WATER;
    }
}