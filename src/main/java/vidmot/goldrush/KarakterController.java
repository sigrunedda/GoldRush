package vidmot.goldrush;


public class KarakterController {
    private String selectedCharacter;

    /**
     * Skilar hvaða karakter er valin
     * @return valinn karakter
     */
    public String getSelectedCharacter() {
        return selectedCharacter;
    }

    /**
     * Setter fyrir valinn karakter
     * @param selectedCharacter
     */
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
