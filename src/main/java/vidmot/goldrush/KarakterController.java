package vidmot.goldrush;

/**
 * Klasinn vistar hvað notandinn valdi og skilar þeirri breytu
 */
public class KarakterController {
    private String selectedCharacter;
    public String getSelectedCharacter() {
        return selectedCharacter;
    }
    public void setSelectedCharacter(String selectedCharacter) {
        this.selectedCharacter = selectedCharacter;
    }
    private static final KarakterController instance = new KarakterController();
    private KarakterController() {}
    public static KarakterController getInstance() {
        return instance;
    }
}
