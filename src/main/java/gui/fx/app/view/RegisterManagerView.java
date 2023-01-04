package gui.fx.app.view;

import gui.fx.app.controller.RegisterClientController;
import gui.fx.app.controller.RegisterManagerController;
import gui.fx.app.restclient.ReservationCompanyServiceRest;
import gui.fx.app.restclient.dtoReservationService.SearchCompanyDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RegisterManagerView extends GridPane {

    private Label lblFirstName;
    private Label lblLastName;
    private Label lblEmail;
    private Label lblPassword;
    private Label lblUsername;
    private Label lblPhone;
    private Label lblBirth;
    private Label lblNameOfCompany;
    private Label lblDateOfEmployment;

    private TextField tfFirstName;
    private TextField tfLastName;
    private TextField tfEmail;
    private TextField tfPassword;
    private TextField tfUsername;
    private TextField tfPhone;
    private TextField tfBirth;
    private TextField tfDateOfEmployment;

    private Button btnRegister;


    private ObservableList<String> searchCompanyDtos;
    private ComboBox comboBox;
    private ReservationCompanyServiceRest reservationCompanyServiceRest;

    public RegisterManagerView(){
        initViewElements();
        addElements();
        addListeners();
        initCompanies();
        this.setAlignment(Pos.CENTER);
        this.setVgap(10);
        this.setHgap(10);
    }

    private void initCompanies() {
        reservationCompanyServiceRest=new ReservationCompanyServiceRest();
        try {
            List<String> lista= new ArrayList<>();
            for(SearchCompanyDto searchCompanyDto:reservationCompanyServiceRest.getCompaniesAvailable())
                lista.add(searchCompanyDto.getCompanyName());
            searchCompanyDtos.addAll(lista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addListeners() {
        this.btnRegister.setOnAction(new RegisterManagerController(this));
    }

    private void addElements() {
        this.addRow(0, lblFirstName, tfFirstName);
        this.addRow(1, lblLastName, tfLastName);
        this.addRow(2, lblEmail, tfEmail);
        this.addRow(3, lblUsername, tfUsername);
        this.addRow(4, lblPassword, tfPassword);
        this.addRow(5, lblBirth, tfBirth);
        this.addRow(6, lblPhone, tfPhone);
        this.addRow(7, lblNameOfCompany, comboBox);
        this.addRow(8,lblDateOfEmployment,tfDateOfEmployment);
        this.addRow(10, btnRegister);
    }

    private void initViewElements() {
        lblFirstName = new Label("First name:");
        lblLastName = new Label("Last name:");
        lblEmail = new Label("Email:");
        lblUsername = new Label("Username:");
        lblPassword = new Label("Password:");
        lblBirth = new Label("Date of birth:");
        lblPhone = new Label("Phone:");
        lblNameOfCompany = new Label("Name of company:");
        lblDateOfEmployment=new Label("Date of employment");
        searchCompanyDtos= FXCollections.observableArrayList();
        comboBox=new ComboBox(searchCompanyDtos);

        tfFirstName = new TextField();
        tfLastName = new TextField();
        tfEmail = new TextField();
        tfUsername = new TextField();
        tfPassword = new TextField();
        tfBirth = new TextField();
        tfPhone = new TextField();
        tfDateOfEmployment=new TextField();

        btnRegister = new Button("Register");
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

    public TextField getTfPassword() {
        return tfPassword;
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

    public ComboBox getComboBox() {
        return comboBox;
    }

    public TextField getTfDateOfEmployment() {
        return tfDateOfEmployment;
    }
}
