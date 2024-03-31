package vinnsla.goldrush;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Leikur {
    private final IntegerProperty stigin = new SimpleIntegerProperty();

    public IntegerProperty stiginProperty(){
        return stigin;
    }

    public int getStigin(){
        return stigin.get();
    }

    public void haekkaStigin(){
        stigin.setValue(stigin.getValue()+1);
    }

    public void nyrLeikur(){
        stigin.setValue(0);
    }

    public void leikLokid(){
        stigin.setValue(0);
    }
}
