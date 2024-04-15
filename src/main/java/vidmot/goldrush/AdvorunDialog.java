package vidmot.goldrush;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

/**
 * Þetta er klasinn sem sér um að birta að viðvörun þegar að leikurinn endar
 * Viðvörunin birtir tvo hnappa; reyna aftur og aftur á forsíðu
 */
public class AdvorunDialog extends Alert {
    private static final String REYNA_AFTUR = "Reyna aftur";
    public static final ButtonType BTYPE = new ButtonType(REYNA_AFTUR,
            ButtonBar.ButtonData.OK_DONE);
    private static final String AFTUR_A_FORSIDU = "Aftur á forsíðu";
    public static final ButtonType HTYPE = new ButtonType(AFTUR_A_FORSIDU,
            ButtonBar.ButtonData.CANCEL_CLOSE); // ButtonType er merktur með CANCEL_CLOSE (er enum);

    public AdvorunDialog(String titill, String haus, String spurning){
        super(AlertType.NONE, spurning, BTYPE, HTYPE);
        setTitle(titill);
        setHeaderText(haus);
    }
}
