package cells.utilityProviders;

public class InternetHub extends UtilityProvider {
    private static final int MAX_INTERNET = 100;

    @Override
    public void tick() {//reset the capacity after every tick
        resetCapacity();
    }
    @Override
    public void resetCapacity() {
        currentCapacity = MAX_INTERNET;
    }
    @Override
    public char getChar() {
        return 'T';
    }
}