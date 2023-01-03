package gui.fx.app.view;

import gui.fx.app.controller.ClientChangePasswordController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class ChangePasswordView extends GridPane {

    private Long id;
    private String password;
    private Label lblPassword;
    private TextField tfPassword;
    private Button btnChange;


    public ChangePasswordView(Long id, String password){
        this.id = id;
        this.password = password;
        initViewElements();
        addElements();
        addListeners();
        this.setAlignment(Pos.CENTER);
        this.setVgap(10);
        this.setHgap(10);

    }

    private void addListeners() {
        this.btnChange.setOnAction(new ClientChangePasswordController(id,this));
    }

    private void addElements() {
        this.addRow(0, lblPassword, tfPassword);
        this.addRow(1,btnChange);

    }

    private void initViewElements() {
        lblPassword = new Label("New password:");
        tfPassword = new TextField(password);
        btnChange = new Button("Change");
    }


    public TextField getTfPassword() {
        return tfPassword;
    }

    public void setTfPassword(TextField tfPassword) {
        this.tfPassword = tfPassword;
    }
}
