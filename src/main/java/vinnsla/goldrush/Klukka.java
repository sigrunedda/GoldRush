package vinnsla.goldrush;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

public class Klukka {
    private SimpleIntegerProperty time;

    public Klukka(){
        this.time = new SimpleIntegerProperty();
    }

    public void tic(){
        time.set(time.get()-1);
    }

    public SimpleIntegerProperty getTimeProperty(){
        return time;
    }
}
