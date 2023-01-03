package gui.fx.app.view;

import gui.fx.app.Main;
import gui.fx.app.controller.AdminChangeDataController;
import gui.fx.app.controller.ClientChangeDataController;
import gui.fx.app.controller.UpdateClientManagerForbbidenController;
import gui.fx.app.model.User;
import gui.fx.app.restclient.ReservationCompanyServiceRest;
import gui.fx.app.restclient.UserServiceRest;
import gui.fx.app.restclient.dtoReservationService.SearchCompanyDto;
import gui.fx.app.restclient.dtoUserService.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AdminChangeDataView extends GridPane {
    private Label lblFirstName;
    private Label lblLastName;
    private Label lblEmail;
    private Label lblUsername;
    private Label lblPhone;
    private Label lblBirth;
    private Label lblForbid;
    private Label lblMinNumberOfRentingDays;
    private Label lblMaxNumberOfRentingDays;
    private Label lblDiscount;

    private TextField tfFirstName;
    private TextField tfLastName;
    private TextField tfEmail;
    private TextField tfUsername;
    private TextField tfPhone;
    private TextField tfBirth;
    private ComboBox comboBoxForbid;
    private ObservableList<String> forbidUserDtos;
    private TextField tfMinNumberOfRentingDays;
    private TextField tfMaxNumberOfRentingDays;
    private TextField tfDiscount;


    private Button btnChange;
    private Button btnChangePassword;
    private Button btnForbid;
    private Button btnAddRank;

    private User user;
    private UserServiceRest userServiceRest=new UserServiceRest();
    private FullAdminDto fullAdminDto;
    public  AdminChangeDataView(User user){
        this.user=user;
        initValues();
        initViewElements();
        addElements();
        addListeners();
        initManagersAndClients();
        this.setAlignment(Pos.CENTER);
        this.setVgap(10);
        this.setHgap(10);

    }

    private void initManagersAndClients()
    {
        try {
            List<String> lista= new ArrayList<>();
            for(ClientAndManagerDto clientAndManagerDto:userServiceRest.getAllClientsAndManagers())
                lista.add(clientAndManagerDto.toString());
            forbidUserDtos.addAll(lista);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }



    private void addListeners() {
        this.btnChange.setOnAction(new AdminChangeDataController(this));
        btnChangePassword.setOnAction(e->{
            Scene sc = new Scene(new ChangePasswordView(user.getId(),fullAdminDto.getPassword()), 200, 200);
            Main.secondStage.setScene(sc);
            Main.secondStage.show();
            Main.secondStage.setTitle("Password change");
        });

        this.btnForbid.setOnAction(new UpdateClientManagerForbbidenController(user.getId(),this));

        this.btnAddRank.setOnAction(e->{
            RankCreateDto rankCreateDto=new RankCreateDto();
            rankCreateDto.setMinNumberOfRentingDays((Integer.parseInt(this.getTfMinNumberOfRentingDays().getText())));
            rankCreateDto.setMaxNumberOfRentingDays((Integer.parseInt(this.getTfMaxNumberOfRentingDays().getText())));
            rankCreateDto.setDiscount(Integer.parseInt(this.getTfDiscount().getText()));
            try {
                RankDto rankDto=userServiceRest.addRank(rankCreateDto);
                System.out.println(rankDto);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        });
    }
    private void initValues()
    {
        try {
            fullAdminDto = userServiceRest.findByIdToUpdateAdmin(new SearchUserDto(user.getId()));
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
        this.addRow(8, btnChange);
        this.addRow(9,btnChangePassword);

        this.addRow(11,lblForbid,comboBoxForbid,btnForbid);

        this.addRow(13,lblMinNumberOfRentingDays,tfMinNumberOfRentingDays);
        this.addRow(14,lblMaxNumberOfRentingDays,tfMaxNumberOfRentingDays);
        this.addRow(15,lblDiscount,tfDiscount);
        this.addRow(16,btnAddRank);
    }

    private void initViewElements() {

        lblFirstName = new Label("First name:");
        lblLastName = new Label("Last name:");
        lblEmail = new Label("Email:");
        lblUsername = new Label("Username:");
        lblBirth = new Label("Date of birth:");
        lblPhone = new Label("Phone:");
        lblForbid=new Label("User:");
        lblMinNumberOfRentingDays=new Label("Min number of renting days:");
        lblMaxNumberOfRentingDays=new Label("Max number of renting days:");
        lblDiscount=new Label("Discount:");

        tfFirstName = new TextField(fullAdminDto.getFirstName());
        tfLastName = new TextField(fullAdminDto.getLastName());
        tfEmail = new TextField(fullAdminDto.getEmail());
        tfUsername = new TextField(fullAdminDto.getUsername());
        SimpleDateFormat formatter2=new SimpleDateFormat("dd-MM-yyyy");
        tfBirth = new TextField(formatter2.format(fullAdminDto.getDateOfBirth()));
        tfPhone = new TextField(fullAdminDto.getContactNo());
        tfMinNumberOfRentingDays=new TextField();
        tfMaxNumberOfRentingDays=new TextField();
        tfDiscount=new TextField();

        forbidUserDtos= FXCollections.observableArrayList();
        comboBoxForbid=new ComboBox(forbidUserDtos);


        btnChange = new Button("Change");
        btnChangePassword=new Button("Change password");
        btnForbid=new Button("Change forbidden status of user");
        btnAddRank=new Button("Add rank");
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

    public ComboBox getComboBoxForbid() {
        return comboBoxForbid;
    }

    public TextField getTfMinNumberOfRentingDays() {
        return tfMinNumberOfRentingDays;
    }

    public TextField getTfMaxNumberOfRentingDays() {
        return tfMaxNumberOfRentingDays;
    }

    public TextField getTfDiscount() {
        return tfDiscount;
    }

    public TextField getTfPhone() {
        return tfPhone;
    }

    public TextField getTfBirth() {
        return tfBirth;
    }
}
