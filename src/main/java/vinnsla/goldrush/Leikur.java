package vinnsla.goldrush;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Heldur utan um stigin fyrir hverjan leik sem notandinn byrjar á
 */
public class Leikur {
    private final IntegerProperty stigin = new SimpleIntegerProperty();

    public IntegerProperty stiginProperty(){
        return stigin;
    }

    /**
     * Nær í stigafjöldann
     * @return stigafjöldann
     */
    public int getStigin(){
        return stigin.get();
    }

    /**
     * Hækkar stigin um 1
     */
    public void haekkaStigin(){
        stigin.setValue(stigin.getValue()+1);
    }
    /**
     * Breytir stigafjöldann í 0 þegar að leikurinn byrjar
     */
    public void nyrLeikur(){
        stigin.setValue(0);
    }

    /**
     * Breytir stigafjöldann í 0 þegar að leikurinn endar
     */
    public void leikLokid(){
        stigin.setValue(0);
    }
}
