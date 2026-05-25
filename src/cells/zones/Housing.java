package cells.zones;

public class Housing extends Zone {
    @Override
    public int getM() {  //specific rule of getm in industrial
        return Math.min(getWater(), Math.min(getElectricity(),getInternet()));
    }

    @Override
    public void calculateLevel() { //
        int minlevel =0;
        if (getElectricity() > 0 && getWater() > 0 && getInternet() > 0){
            minlevel++;
            if (isHasSecurity() == true && isHasEducation() == true && isHasHealth() ==true){
                minlevel++;

                if (resources.getLifestyle() > 0){
                    minlevel++;
                }
            }
        } if (minlevel > getLevel()) setLevel(getLevel() +1); //guaranties increase level only 1 per tick
        else if (minlevel < getLevel()) setLevel(getLevel() -1);//guaranties decrease level only 1 per tick

    }

    @Override
    public void calculateOutput(int a) { // specified for industrial
        if (getLevel() == 0) setCurrentOutput(0);
        if (getLevel() == 1) setCurrentOutput(a);
        if (getLevel() == 2) setCurrentOutput(2*a);
        if (getLevel() == 3) setCurrentOutput((2*a)+ resources.getLifestyle());
    }

    @Override
    public char getChar() {
        return 'H';
    }
}
