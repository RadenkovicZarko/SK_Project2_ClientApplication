package gui.fx.app.view;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gui.fx.app.ClientApp;
import gui.fx.app.Main;
import gui.fx.app.controller.SearchNotificationsController;
import gui.fx.app.model.User;
import gui.fx.app.restclient.NotificationServiceRest;
import gui.fx.app.restclient.ReservationCompanyServiceRest;
import gui.fx.app.restclient.UserServiceRest;
import gui.fx.app.restclient.dtoNotificationService.FindAllNotificationsForParametersDto;
import gui.fx.app.restclient.dtoNotificationService.NotificationDto;
import gui.fx.app.restclient.dtoNotificationService.NotificationTypeDto;
import gui.fx.app.restclient.dtoReservationService.ChangeReviewDto;
import gui.fx.app.restclient.dtoReservationService.CompanyDto;
import gui.fx.app.restclient.dtoReservationService.ReviewDto;
import gui.fx.app.restclient.dtoReservationService.SearchCompanyDto;
import gui.fx.app.restclient.dtoUserService.UserDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.xml.soap.Text;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

public class NotificationHistoryView extends VBox {
    private HBox hBox;
    private Label lblTypeOfNotification;
    private Label lblEmail;
    private Label lblDateFrom;
    private Label lblDateTo;

    private ComboBox cbTypeOfNotification;
    private TextField tfEmail;
    private TextField tfDateFrom;
    private TextField tfDateTo;
    private Button btnGoBack;

    private Button btnSearch;
    private NotificationServiceRest notificationServiceRest=new NotificationServiceRest();
    private UserServiceRest userServiceRest=new UserServiceRest();

    private ObservableList<String> notificationTypeDtos;

    private ObservableList<NotificationDto> notificationDtos;
    private TableView<NotificationDto> tableNotification;

    private ObjectMapper objectMapper=new ObjectMapper();

    public NotificationHistoryView(){
        initViewElements();
        addElements();
        addListeners();
        initValues();
        this.setSpacing(15);
    }

    private User getUser(String token) throws JsonProcessingException {
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(token.split("[.]")[1]));
        User userMapper = objectMapper.readValue(payload, User.class);
        return userMapper;
    }

    void initValues()
    {
        try {
            List<String> lista= new ArrayList<>();
            for(NotificationTypeDto notificationTypeDto:notificationServiceRest.findAll())
                lista.add(notificationTypeDto.getNameOfClass());
            notificationTypeDtos.addAll(lista);

            FindAllNotificationsForParametersDto findAllNotificationsForParametersDto=new FindAllNotificationsForParametersDto();
            UserDto userDto=userServiceRest.getUserById(getUser(ClientApp.getInstance().getToken()).getId());
            findAllNotificationsForParametersDto.setEmail(userDto.getEmail());
            findAllNotificationsForParametersDto.setRole(getUser(ClientApp.getInstance().getToken()).getRole());
            findAllNotificationsForParametersDto.setEmailParameter("");
            findAllNotificationsForParametersDto.setFromDate(null);
            findAllNotificationsForParametersDto.setToDate(null);
            findAllNotificationsForParametersDto.setNotificationTypeName("");

            notificationDtos.addAll(notificationServiceRest.getAllNotificationsForParameters(findAllNotificationsForParametersDto));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addListeners() {
        btnSearch.setOnAction(new SearchNotificationsController(this));
        btnGoBack.setOnAction(e->{
            try {
                User user = getUser(ClientApp.getInstance().getToken());
                if(user.getRole().equalsIgnoreCase("ROLE_CLIENT"))
                {
                    Scene sc=new Scene(new ClientChangeDataView(user),500,500);
                    Main.mainStage.setScene(sc);
                    Main.mainStage.show();
                }
                if(user.getRole().equalsIgnoreCase("ROLE_MANAGER"))
                {
                    Scene sc=new Scene(new ManagerChangeDataView(user),700,700);
                    Main.mainStage.setScene(sc);
                    Main.mainStage.show();
                }
                if(user.getRole().equalsIgnoreCase("ROLE_ADMIN"))
                {
                    Scene sc=new Scene(new AdminChangeDataView(user),700,700);
                    Main.mainStage.setScene(sc);
                    Main.mainStage.show();
                }
            } catch (JsonProcessingException jsonProcessingException) {
                jsonProcessingException.printStackTrace();
            }
        });
    }


    private void addElements() {
        String token= ClientApp.getInstance().getToken();
        try {
            this.hBox.getChildren().addAll(lblTypeOfNotification,cbTypeOfNotification,lblEmail,tfEmail,lblDateFrom,tfDateFrom,lblDateTo,tfDateTo,btnSearch);
            this.getChildren().addAll(hBox,tableNotification,btnGoBack);
            this.setSpacing(10);
            this.setAlignment(Pos.CENTER);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void initViewElements() {
        hBox = new HBox(10);
        lblTypeOfNotification=new Label("Type of notification");
        lblEmail=new Label("Email");
        lblDateFrom=new Label("From");
        lblDateTo=new Label("To");

        notificationTypeDtos = FXCollections.observableArrayList();
        cbTypeOfNotification=new ComboBox(notificationTypeDtos);
        tfEmail=new TextField();
        tfDateFrom=new TextField();
        tfDateTo=new TextField();
        btnSearch=new Button("Search");
        btnGoBack=new Button("Go back");


        notificationDtos = FXCollections.observableArrayList();
        tableNotification = new TableView<>(notificationDtos);

        TableColumn<NotificationDto, Long> col1 = new TableColumn<>("Id");
        col1.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<NotificationDto, Long> col2 = new TableColumn<>("idNotificationType");
        col2.setCellValueFactory(new PropertyValueFactory<>("idNotificationType"));
        TableColumn<NotificationDto, String> col3 = new TableColumn<>("email");
        col3.setCellValueFactory(new PropertyValueFactory<>("email"));
        TableColumn<NotificationDto, String> col4 = new TableColumn<>("text");
        col4.setCellValueFactory(new PropertyValueFactory<>("text"));
        TableColumn<NotificationDto, Date> col5 = new TableColumn<>("dateOfSending");
        col5.setCellValueFactory(new PropertyValueFactory<>("dateOfSending"));

        tableNotification.getColumns().add(col1);
        tableNotification.getColumns().add(col2);
        tableNotification.getColumns().add(col3);
        tableNotification.getColumns().add(col4);
        tableNotification.getColumns().add(col5);

    }

    public ComboBox getCbTypeOfNotification() {
        return cbTypeOfNotification;
    }

    public TextField getTfEmail() {
        return tfEmail;
    }

    public TextField getTfDateFrom() {
        return tfDateFrom;
    }

    public TextField getTfDateTo() {
        return tfDateTo;
    }

    public ObservableList<NotificationDto> getNotificationDtos() {
        return notificationDtos;
    }

    public TableView<NotificationDto> getTableNotification() {
        return tableNotification;
    }
}
