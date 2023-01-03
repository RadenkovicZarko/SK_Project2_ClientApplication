package gui.fx.app.view;

import gui.fx.app.Main;
import gui.fx.app.controller.ClientChangeDataController;
import gui.fx.app.controller.ManagerChangeDataController;
import gui.fx.app.model.User;
import gui.fx.app.restclient.UserServiceRest;
import gui.fx.app.restclient.dtoUserService.FullManagerDto;
import gui.fx.app.restclient.dtoUserService.SearchUserDto;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.text.SimpleDateFormat;

public class ManagerChangeDataView extends GridPane {
    private Label lblFirstName;
    private Label lblLastName;
    private Label lblEmail;
    private Label lblUsername;
    private Label lblPhone;
    private Label lblBirth;
    private Label lblDateOfEmployment;

    private TextField tfFirstName;
    private TextField tfLastName;
    private TextField tfEmail;
    private TextField tfUsername;
    private TextField tfPhone;
    private TextField tfBirth;
    private TextField tfDateOfEmployment;

    private Button btnChange;
    private Button btnChangePassword;

    private User user;
    private UserServiceRest userServiceRest=new UserServiceRest();
    private FullManagerDto fullManagerDto;


    public ManagerChangeDataView(User user){
        this.user=user;
        initValues();
        initViewElements();
        addElements();
        addListeners();
        this.setAlignment(Pos.CENTER);
        this.setVgap(10);
        this.setHgap(10);

    }

    private void addListeners() {
        this.btnChange.setOnAction(new ManagerChangeDataController(this));
        btnChangePassword.setOnAction(e->{
            Scene sc = new Scene(new ChangePasswordView(user.getId(), fullManagerDto.getPassword()), 200, 200);
            Main.secondStage.setScene(sc);
            Main.secondStage.show();
            Main.secondStage.setTitle("Password change");
        });

    }
    private void initValues()
    {
        try {
            fullManagerDto = userServiceRest.findByIdToUpdateManager(new SearchUserDto(user.getId()));
            System.out.println("Prosao "+ fullManagerDto.getFirstName());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void addElements() {
        this.addRow(0, lblFirstName, tfFirstName);
        this.addRow(1, lblLastName, tfLastName);
        this.addRow(2, lblEmail, tfEmail);
        this.addRow(3, lblUsername, tfUsername);
        this.addRow(5, lblBirth, tfBirth);
        this.addRow(6, lblPhone, tfPhone);
        this.addRow(7, lblDateOfEmployment, tfDateOfEmployment);
        this.addRow(8, btnChange);
        this.addRow(9,btnChangePassword);
    }

    private void initViewElements() {

        lblFirstName = new Label("First name:");
        lblLastName = new Label("Last name:");
        lblEmail = new Label("Email:");
        lblUsername = new Label("Username:");
        lblBirth = new Label("Date of birth:");
        lblPhone = new Label("Phone:");
        lblDateOfEmployment = new Label("Date of employment:");

        tfFirstName = new TextField(fullManagerDto.getFirstName());
        tfLastName = new TextField(fullManagerDto.getLastName());
        tfEmail = new TextField(fullManagerDto.getEmail());
        tfUsername = new TextField(fullManagerDto.getUsername());
        SimpleDateFormat formatter2=new SimpleDateFormat("dd-MM-yyyy");
        tfBirth = new TextField(formatter2.format(fullManagerDto.getDateOfBirth()));
        tfPhone = new TextField(fullManagerDto.getContactNo());
        tfDateOfEmployment = new TextField(formatter2.format(fullManagerDto.getDateOfEmployment()));

        btnChange = new Button("Change");
        btnChangePassword=new Button("Change password");
    }

    public TextField getTfFirstName() {
        return tfFirstName;
    }

    public TextField getTfLastName() {
        return tfLastName;
    }

    public TextField getTfEmail() {
        return tfEmail;
    }

    public TextField getTfUsername() {
        return tfUsername;
    }

    public TextField getTfPhone() {
        return tfPhone;
    }

    public TextField getTfBirth() {
        return tfBirth;
    }

    public TextField getTfDateOfEmployment() {
        return tfDateOfEmployment;
    }
}
