module com.example.thegame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.thegame to javafx.fxml;
    exports com.example.thegame;
}