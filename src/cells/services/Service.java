package cells.services;

import cells.Cell;

public abstract class Service implements Cell {
    //other services classes will inherit from here.
    public abstract int getRadius();

    @Override
    public void tick() { // this is empty because services does not have to update anything per tick

    }
}
