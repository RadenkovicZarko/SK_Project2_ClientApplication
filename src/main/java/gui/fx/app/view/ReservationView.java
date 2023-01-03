package gui.fx.app.view;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gui.fx.app.ClientApp;
import gui.fx.app.controller.BookController;
import gui.fx.app.controller.CancelReservationController;
import gui.fx.app.controller.SearchController;
import gui.fx.app.model.User;
import gui.fx.app.restclient.ReservationCompanyServiceRest;
import gui.fx.app.restclient.dtoReservationService.FindReservationsDto;
import gui.fx.app.restclient.dtoReservationService.ReservationDto;
import gui.fx.app.restclient.dtoReservationService.SearchDto;
import gui.fx.app.restclient.dtoReservationService.VehicleDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;

public class ReservationView extends VBox {

    private HBox hBoxFilers;

    private Label lblCity;
    private Label lblCompanyName;
    private Label lblFrom;
    private Label lblTo;
    private Label lblReservation;
    private Label lblSort;
    private TextField tfCity;
    private TextField tfCompanyName;
    private TextField tfFrom;
    private TextField tfTo;
    private CheckBox checkBoxSort;
    private Button btnSearch;
    private Button btnBook;
    private Button btnCancelReservation;

    private ObservableList<VehicleDto> vehicleDtos;
    private TableView<VehicleDto> tableVehicles;
    private ObservableList<ReservationDto> reservationList;
    private TableView<ReservationDto>  userReservationtable;
    private ObjectMapper objectMapper=new ObjectMapper();

    private ReservationCompanyServiceRest reservationCompanyServiceRest=new ReservationCompanyServiceRest();

    public ReservationView(){
        initViewElements();
        addElements();
        addListeners();
        initReservations();
    }
    private User getUser(String token) throws JsonProcessingException {
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(token.split("[.]")[1]));
        User userMapper = objectMapper.readValue(payload, User.class);
        return userMapper;
    }

    private void initReservations() {
        try {
            String token= ClientApp.getInstance().getToken();
            User user=getUser(token);
            //vehicleDtos.addAll(reservationCompanyServiceRest.findAllAvailableVehicle(new SearchDto()));
            reservationList.addAll(reservationCompanyServiceRest.getReservations(new FindReservationsDto(user.getId(),user.getRole())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addListeners() {
        btnSearch.setOnAction(new SearchController(this));
        btnBook.setOnAction(new BookController(this));
        btnCancelReservation.setOnAction(new CancelReservationController(this));
    }

    private void addElements() {
        this.hBoxFilers.getChildren().addAll(lblCity, tfCity, lblCompanyName, tfCompanyName, lblFrom, tfFrom, lblTo, tfTo, btnSearch,lblSort,checkBoxSort);
        this.getChildren().addAll(hBoxFilers, tableVehicles, btnBook, lblReservation,userReservationtable,btnCancelReservation);
        this.setSpacing(10);
        this.setAlignment(Pos.CENTER);
    }

    private void initViewElements() {
        hBoxFilers = new HBox(5);
        lblCity = new Label("City");
        lblCompanyName = new Label("Company name");
        lblFrom = new Label("From");
        lblTo = new Label("To");
        lblReservation = new Label("Your reservations");
        tfCity = new TextField();
        tfCompanyName = new TextField();
        tfFrom = new TextField();
        tfTo = new TextField();
        btnSearch = new Button("Search");
        btnBook = new Button("Book");
        lblSort=new Label("Sort:");
        checkBoxSort=new CheckBox();
        btnCancelReservation=new Button("Cancel reservation");


        vehicleDtos = FXCollections.observableArrayList();
        tableVehicles = new TableView<>(vehicleDtos);
        reservationList = FXCollections.observableArrayList();
        userReservationtable = new TableView<>(reservationList);
        TableColumn<VehicleDto, String> col1 = new TableColumn<>("Company");
        col1.setCellValueFactory(new PropertyValueFactory<>("company"));
        TableColumn<VehicleDto, Double> col2 = new TableColumn<>("Price");
        col2.setCellValueFactory(new PropertyValueFactory<>("price"));
        TableColumn<VehicleDto, String> col3 = new TableColumn<>("Model");
        col3.setCellValueFactory(new PropertyValueFactory<>("model"));
        TableColumn<VehicleDto, String> col4 = new TableColumn<>("Type");
        col4.setCellValueFactory(new PropertyValueFactory<>("type"));
        tableVehicles.getColumns().add(col1);
        tableVehicles.getColumns().add(col2);
        tableVehicles.getColumns().add(col3);
        tableVehicles.getColumns().add(col4);

        TableColumn<ReservationDto, Long> col11 = new TableColumn<>("Id of vehicle");
        col11.setCellValueFactory(new PropertyValueFactory<>("id_vehicle"));
        TableColumn<ReservationDto, String> col22 = new TableColumn<>("Model");
        col22.setCellValueFactory(new PropertyValueFactory<>("modelName"));
        TableColumn<ReservationDto, String> col33 = new TableColumn<>("Type");
        col33.setCellValueFactory(new PropertyValueFactory<>("typeName"));
        TableColumn<ReservationDto, Date> col44 = new TableColumn<>("From");
        col44.setCellValueFactory(new PropertyValueFactory<>("date_from"));
        TableColumn<ReservationDto, Date> col55 = new TableColumn<>("To");
        col55.setCellValueFactory(new PropertyValueFactory<>("date_to"));
        TableColumn<ReservationDto, Long> col66 = new TableColumn<>("Price per day");
        col66.setCellValueFactory(new PropertyValueFactory<>("price"));
        userReservationtable.getColumns().add(col11);
        userReservationtable.getColumns().add(col22);
        userReservationtable.getColumns().add(col33);
        userReservationtable.getColumns().add(col44);
        userReservationtable.getColumns().add(col55);
        userReservationtable.getColumns().add(col66);
    }


    public ObservableList<VehicleDto> getVehicleDtos() {
        return vehicleDtos;
    }

    public TextField getTfCity() {
        return tfCity;
    }

    public void setTfCity(TextField tfCity) {
        this.tfCity = tfCity;
    }

    public TableView<VehicleDto> getTableVehicles() {
        return tableVehicles;
    }

    public TextField getTfCompanyName() {
        return tfCompanyName;
    }

    public void setTfCompanyName(TextField tfCompanyName) {
        this.tfCompanyName = tfCompanyName;
    }

    public TextField getTfFrom() {
        return tfFrom;
    }

    public void setTfFrom(TextField tfFrom) {
        this.tfFrom = tfFrom;
    }

    public TextField getTfTo() {
        return tfTo;
    }

    public void setTfTo(TextField tfTo) {
        this.tfTo = tfTo;
    }

    public CheckBox getCheckBoxSort() {
        return checkBoxSort;
    }

    public void setCheckBoxSort(CheckBox checkBoxSort) {
        this.checkBoxSort = checkBoxSort;
    }

    public ObservableList<ReservationDto> getReservationList() {
        return reservationList;
    }

    public TableView<ReservationDto> getUserReservationtable() {
        return userReservationtable;
    }
}
