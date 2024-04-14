module vidmot.goldrush {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens vidmot.goldrush to javafx.fxml;
    exports vidmot.goldrush;
}