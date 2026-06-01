package cells.utilityProviders;

public class PowerPlant extends UtilityProvider {
    private static final int MAX_ELECTRICITY = 100;

    @Override
    public int getCapacity() {
        return MAX_ELECTRICITY;
    }
}