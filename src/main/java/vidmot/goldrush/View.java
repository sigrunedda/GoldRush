package vidmot.goldrush;

public enum View {
    START("start-view.fxml"),
    KARAKTER("karakter-test.fxml"),
    LEIKREGLUR("leikreglur-view.fxml"),
    LEIKBORD("gold-rush-view.fxml");

    private String fileName;

    View(String fileName){
        this.fileName = fileName;
    }
    public String getFileName(){
        return fileName;
    }
}
