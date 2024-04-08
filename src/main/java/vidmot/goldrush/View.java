package vidmot.goldrush;

public enum View {
    START("start-view.fxml"),
    KARAKTER("karakter-view.fxml"),
    LEIKREGLUR("leikreglur-view.fxml"),
    LEIKBORD("gold-rush-view.fxml");

    private final String fileName;

    View(String fileName){
        this.fileName = fileName;
    }
    public String getFileName(){
        return fileName;
    }
}
