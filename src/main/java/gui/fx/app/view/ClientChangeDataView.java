package gui.fx.app.view;

import gui.fx.app.Main;
import gui.fx.app.controller.ClientChangeDataController;
import gui.fx.app.model.User;
import gui.fx.app.restclient.UserServiceRest;
import gui.fx.app.restclient.dtoUserService.FullClientDto;
import gui.fx.app.restclient.dtoUserService.SearchUserDto;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.text.SimpleDateFormat;

public class ClientChangeDataView extends GridPane {
    private Label lblFirstName;
    private Label lblLastName;
    private Label lblEmail;
    private Label lblUsername;
    private Label lblPhone;
    private Label lblBirth;
    private Label lblPassport;

    private TextField tfFirstName;
    private TextField tfLastName;
    private TextField tfEmail;
    private TextField tfUsername;
    private TextField tfPhone;
    private TextField tfBirth;
    private TextField tfPassport;

    private Button btnChange;
    private Button btnChangePassword;
    private Button btnReservationView;
    private Button btnReviewView;

    private User user;
    private UserServiceRest userServiceRest=new UserServiceRest();
    private FullClientDto fullClientDto;
    public ClientChangeDataView(User user){
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
        this.btnChange.setOnAction(new ClientChangeDataController(this));
        btnChangePassword.setOnAction(e->{
            Scene sc = new Scene(new ChangePasswordView(user.getId(),fullClientDto.getPassword()), 200, 200);
            Main.secondStage.setScene(sc);
            Main.secondStage.show();
            Main.secondStage.setTitle("Password change");
        });
        this.btnReservationView.setOnAction(e->{
            Scene sc = new Scene(new ReservationView(), 900, 700);
            Main.mainStage.setScene(sc);
            Main.mainStage.show();
            Main.mainStage.setTitle("Reservation view");

        });
        this.btnReviewView.setOnAction(e->{
            Scene sc = new Scene(new ReviewView(), 900, 700);
            Main.mainStage.setScene(sc);
            Main.mainStage.show();
            Main.mainStage.setTitle("Reservation view");
        });

    }
    private void initValues()
    {
        try {
            fullClientDto = userServiceRest.findByIdToUpdateClient(new SearchUserDto(user.getId()));
            System.out.println("Prosao "+fullClientDto.getFirstName());
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
        this.addRow(7, lblPassport, tfPassport);
        this.addRow(8, btnChange);
        this.addRow(9,btnChangePassword);
        this.addRow(10,btnReservationView);
        this.addRow(11,btnReviewView);
    }

    private void initViewElements() {

        lblFirstName = new Label("First name:");
        lblLastName = new Label("Last name:");
        lblEmail = new Label("Email:");
        lblUsername = new Label("Username:");
        lblBirth = new Label("Date of birth:");
        lblPhone = new Label("Phone:");
        lblPassport = new Label("Passport number:");

        tfFirstName = new TextField(fullClientDto.getFirstName());
        tfLastName = new TextField(fullClientDto.getLastName());
        tfEmail = new TextField(fullClientDto.getEmail());
        tfUsername = new TextField(fullClientDto.getUsername());
        SimpleDateFormat formatter2=new SimpleDateFormat("dd-MM-yyyy");
        tfBirth = new TextField(formatter2.format(fullClientDto.getDateOfBirth()));
        tfPhone = new TextField(fullClientDto.getContactNo());
        tfPassport = new TextField(fullClientDto.getPassportNo().toString());

        btnChange = new Button("Change");
        btnChangePassword=new Button("Change password");
        btnReservationView=new Button("Reserve vehicle");
        btnReviewView=new Button("Reviews");
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

    public TextField getTfPassport() {
        return tfPassport;
    }

    public Button getBtnChange() {
        return btnChange;
    }

    public User getUser() {
        return user;
    }

    public UserServiceRest getUserServiceRest() {
        return userServiceRest;
    }

    public FullClientDto getFullClientDto() {
        return fullClientDto;
    }
}
