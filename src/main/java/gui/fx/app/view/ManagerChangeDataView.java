package gui.fx.app.view;

import gui.fx.app.Main;
import gui.fx.app.controller.ClientChangeDataController;
import gui.fx.app.controller.ManagerChangeDataController;
import gui.fx.app.model.User;
import gui.fx.app.restclient.ReservationCompanyServiceRest;
import gui.fx.app.restclient.UserServiceRest;
import gui.fx.app.restclient.dtoReservationService.*;
import gui.fx.app.restclient.dtoUserService.ClientAndManagerDto;
import gui.fx.app.restclient.dtoUserService.FullManagerDto;
import gui.fx.app.restclient.dtoUserService.SearchUserDto;
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

public class ManagerChangeDataView extends GridPane {
    private Label lblFirstName;
    private Label lblLastName;
    private Label lblEmail;
    private Label lblUsername;
    private Label lblPhone;
    private Label lblBirth;
    private Label lblDateOfEmployment;
    private Label lblCity;
    private Label lblDescription;
    private Label lblNameOfCompany;
    private Label lblType;
    private Label lblModel;
    private Label lblPrice;


    private TextField tfFirstName;
    private TextField tfLastName;
    private TextField tfEmail;
    private TextField tfUsername;
    private TextField tfPhone;
    private TextField tfBirth;
    private TextField tfDateOfEmployment;
    private TextField tfCity;
    private TextField tfDescription;
    private TextField tfNameOfCompany;
    private ObservableList<String> vehicleTypeDtos;
    private ComboBox cbType;
    private ObservableList<String> vehicleModelDtos;
    private ComboBox cbModel;
    private TextField tfPrice;

    private Button btnChange;
    private Button btnChangePassword;
    private Button btnChangeCompanyDetails;
    private Button btnAddVehicle;
    private Button btnReservationView;
    private Button btnNotificationView;


    private User user;
    private UserServiceRest userServiceRest=new UserServiceRest();
    private ReservationCompanyServiceRest reservationCompanyServiceRest=new ReservationCompanyServiceRest();
    private FullManagerDto fullManagerDto;
    private CompanyInformationDto companyInformationDto;



    public ManagerChangeDataView(User user){
        this.user=user;
        initValues();
        initViewElements();
        addElements();
        addListeners();
        initModelsAndTypes();
        this.setAlignment(Pos.CENTER);
        this.setVgap(10);
        this.setHgap(10);

    }
    private void initModelsAndTypes()
    {
        try {
            List<String> lista= new ArrayList<>();
            for(ModelDto modelDto:reservationCompanyServiceRest.getModels())
                lista.add(modelDto.getId()+" "+modelDto.getName());
            vehicleModelDtos.addAll(lista);

            lista= new ArrayList<>();
            for(TypeDto typeDto:reservationCompanyServiceRest.getTypes())
                lista.add(typeDto.getId()+" "+typeDto.getName());
            vehicleTypeDtos.addAll(lista);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void addListeners() {
        this.btnChange.setOnAction(new ManagerChangeDataController(this));
        btnChangePassword.setOnAction(e->{
            Scene sc = new Scene(new ChangePasswordView(user.getId(), fullManagerDto.getPassword()), 200, 200);
            Main.secondStage.setScene(sc);
            Main.secondStage.show();
            Main.secondStage.setTitle("Password change");
        });
        this.btnChangeCompanyDetails.setOnAction(e->{
            CompanyInformationDto cid=new CompanyInformationDto();
            cid.setId(this.companyInformationDto.getId());
            cid.setId_manager(this.companyInformationDto.getId_manager());
            cid.setName(this.companyInformationDto.getName());
            cid.setCity(this.tfCity.getText());
            cid.setDescription(this.tfDescription.getText());
            try {
                CompanyInformationDto cid1=reservationCompanyServiceRest.updateCompanyDetails(cid);
                System.out.println(cid1);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        this.btnAddVehicle.setOnAction(e->{
            VehicleCreateDto vehicleCreateDto=new VehicleCreateDto();
            String modelString=cbModel.getSelectionModel().getSelectedItem().toString().split(" ")[0];
            String typeString=cbType.getSelectionModel().getSelectedItem().toString().split(" ")[0];
            vehicleCreateDto.setModel(Long.parseLong(modelString));
            vehicleCreateDto.setType(Long.parseLong(typeString));
            vehicleCreateDto.setPrice(Integer.parseInt(this.tfPrice.getText()));
            vehicleCreateDto.setCompany(this.companyInformationDto.getId());
            try {
                VehicleCreateDto v1=reservationCompanyServiceRest.addVehicle(vehicleCreateDto);
                System.out.println(v1);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        this.btnReservationView.setOnAction(e->{
            Scene sc = new Scene(new ReservationView(), 900, 700);
            Main.mainStage.setScene(sc);
            Main.mainStage.show();
            Main.mainStage.setTitle("Reservation view");

        });

        this.btnNotificationView.setOnAction(e->{
            Scene sc = new Scene(new NotificationHistoryView(), 700, 700);
            Main.secondStage.setScene(sc);
            Main.secondStage.show();
            Main.secondStage.setTitle("Notification history");});

    }
    private void initValues()
    {
        try {
            companyInformationDto=reservationCompanyServiceRest.getCompany(new FindCompanyByManagerDto(user.getId()));
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
        
        this.addRow(11,lblCity,tfCity);
        this.addRow(12,lblDescription,tfDescription);
        this.addRow(14,btnChangeCompanyDetails);

        this.addRow(15,lblModel,cbModel);
        this.addRow(16,lblType,cbType);
        this.addRow(17,lblPrice,tfPrice);
        this.addRow(18,btnAddVehicle);
        this.addRow(19,btnReservationView);
        this.addRow(20,btnNotificationView);
    }

    private void initViewElements() {

        lblFirstName = new Label("First name:");
        lblLastName = new Label("Last name:");
        lblEmail = new Label("Email:");
        lblUsername = new Label("Username:");
        lblBirth = new Label("Date of birth:");
        lblPhone = new Label("Phone:");
        lblDateOfEmployment = new Label("Date of employment:");
        lblCity=new Label("City of company:");
        lblDescription=new Label("Description of company:");
        lblPrice=new Label("Price of vehicle:");
        lblModel=new Label("Model:");
        lblType=new Label("Type:");

        tfFirstName = new TextField(fullManagerDto.getFirstName());
        tfLastName = new TextField(fullManagerDto.getLastName());
        tfEmail = new TextField(fullManagerDto.getEmail());
        tfUsername = new TextField(fullManagerDto.getUsername());
        SimpleDateFormat formatter2=new SimpleDateFormat("dd-MM-yyyy");
        tfBirth = new TextField(formatter2.format(fullManagerDto.getDateOfBirth()));
        tfPhone = new TextField(fullManagerDto.getContactNo());
        tfDateOfEmployment = new TextField(formatter2.format(fullManagerDto.getDateOfEmployment()));
        tfCity=new TextField(companyInformationDto.getCity());
        tfDescription=new TextField(companyInformationDto.getDescription());

        vehicleTypeDtos= FXCollections.observableArrayList();
        cbType=new ComboBox(vehicleTypeDtos);
        vehicleModelDtos=FXCollections.observableArrayList();
        cbModel=new ComboBox(vehicleModelDtos);
        tfPrice=new TextField();

        btnChange = new Button("Change");
        btnChangePassword=new Button("Change password");
        btnChangeCompanyDetails=new Button("Change company details");
        btnAddVehicle=new Button("Add vehicle");
        btnReservationView=new Button("Reserve vehicle");
        btnNotificationView=new Button("Notification history");
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

    public TextField getTfCity() {
        return tfCity;
    }

    public TextField getTfDescription() {
        return tfDescription;
    }

    public TextField getTfNameOfCompany() {
        return tfNameOfCompany;
    }
}
