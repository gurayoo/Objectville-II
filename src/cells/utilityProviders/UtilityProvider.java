package cells.utilityProviders;
import cells.Cell;
public abstract class UtilityProvider implements Cell{
    //other utilityProviders classes will inherit from here.
    int currentCapacity;

    public abstract void resetCapacity() ;
}