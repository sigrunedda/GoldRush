module vidmot.goldrush {
    requires javafx.controls;
    requires javafx.fxml;


    opens vidmot.goldrush to javafx.fxml;
    exports vidmot.goldrush;
}