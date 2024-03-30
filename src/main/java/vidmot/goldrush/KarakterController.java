package vidmot.goldrush;

/**
 * @author Sylvía Tyrfingsdóttir
 * Póstur : sht15@hi.is
 * Lýsing :
 */
public class KarakterController {
    private String selectedCharacter;

    public String getSelectedCharacter() {
        return selectedCharacter;
    }

    public void setSelectedCharacter(String selectedCharacter) {
        this.selectedCharacter = selectedCharacter;
    }

    // Singleton pattern to ensure only one instance of CharacterController exists
    private static final KarakterController instance = new KarakterController();

    private KarakterController() {}

    public static KarakterController getInstance() {
        return instance;
    }
}
