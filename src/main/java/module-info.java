module com.example.miniproyecto1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.almasb.fxgl.all;

    opens com.example.miniproyecto1 to javafx.fxml;
    exports com.example.miniproyecto1;
}