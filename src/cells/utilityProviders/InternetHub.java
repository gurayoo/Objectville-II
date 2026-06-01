package cells.utilityProviders;

public class InternetHub extends UtilityProvider {
    private static final int MAX_INTERNET = 100;

    @Override
    public int getCapacity() {
        return  MAX_INTERNET;
    }
}