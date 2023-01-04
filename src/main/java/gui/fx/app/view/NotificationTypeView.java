package gui.fx.app.view;

import com.fasterxml.jackson.core.JsonProcessingException;
import gui.fx.app.ClientApp;
import gui.fx.app.Main;
import gui.fx.app.model.User;
import gui.fx.app.restclient.NotificationServiceRest;
import gui.fx.app.restclient.ReservationCompanyServiceRest;
import gui.fx.app.restclient.dtoNotificationService.NotificationTypeCreateDto;
import gui.fx.app.restclient.dtoNotificationService.NotificationTypeDeleteDto;
import gui.fx.app.restclient.dtoNotificationService.NotificationTypeDto;
import gui.fx.app.restclient.dtoReservationService.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


public class NotificationTypeView extends VBox {

    private HBox hBox1;
    private HBox hBox2;
    private HBox hBox3;
    private HBox hBox4;
    private Label lblAddNotificationType;
    private Label lblNameOfClass;
    private Label lblName;
    private Label lblLastName;
    private Label lblLink;
    private Label lblIdVehicle;
    private Label lblModel;
    private Label lblTypeOfVehicle;
    private Label lblDateFrom;
    private Label lblDateTo;
    private Label lblEmailOfManager;
    private Label lblPassword;
    private Label lblText;

    private TextField tfNameOfClass;
    private CheckBox ckName;
    private CheckBox ckLastName;
    private CheckBox ckLink;
    private CheckBox ckIdVehicle;
    private CheckBox ckModel;
    private CheckBox ckTypeOfVehicle;
    private CheckBox ckDateFrom;
    private CheckBox ckDateTo;
    private CheckBox ckEmailOfManager;
    private CheckBox ckPassword;
    private TextField tfText;
    private Button btnAddNotificationType;
    private Button btnChangeNotificationType;
    private Button btnDeleteNotificationType;

    private ObservableList<NotificationTypeDto> notificationTypeDtos;
    private TableView<NotificationTypeDto> tableNotificationTypes;





    private NotificationServiceRest notificationServiceRest=new NotificationServiceRest();
    public NotificationTypeView(){
        initViewElements();
        addElements();
        addListeners();
        initValues();
        this.setSpacing(15);
    }
//    private User getUser(String token) throws JsonProcessingException {
//        Base64.Decoder decoder = Base64.getUrlDecoder();
//        String payload = new String(decoder.decode(token.split("[.]")[1]));
//        User userMapper = objectMapper.readValue(payload, User.class);
//        return userMapper;
//    }

    private void initValues() {

        try {
            notificationTypeDtos.addAll(notificationServiceRest.findAll());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addListeners() {
        btnAddNotificationType.setOnAction(e->{
            try {
                NotificationTypeCreateDto notificationTypeCreateDto=new NotificationTypeCreateDto();
                notificationTypeCreateDto.setNameOfClass(this.getTfNameOfClass().getText());
                notificationTypeCreateDto.setName(this.getCkName().isSelected());
                notificationTypeCreateDto.setLastName(this.getCkLastName().isSelected());
                notificationTypeCreateDto.setLink(this.getCkLink().isSelected());
                notificationTypeCreateDto.setIdVehicle(this.getCkIdVehicle().isSelected());
                notificationTypeCreateDto.setModel(this.getCkModel().isSelected());
                notificationTypeCreateDto.setTypeOfVehicle(this.getCkTypeOfVehicle().isSelected());
                notificationTypeCreateDto.setDateFrom(this.getCkDateFrom().isSelected());
                notificationTypeCreateDto.setDateTo(this.getCkDateTo().isSelected());
                notificationTypeCreateDto.setEmailOfManager(this.getCkEmailOfManager().isSelected());
                notificationTypeCreateDto.setPassword(this.getCkPassword().isSelected());
                notificationTypeCreateDto.setText(this.getTfText().getText());
                NotificationTypeDto notificationTypeDto=notificationServiceRest.addNotificationType(notificationTypeCreateDto);
                System.out.println(notificationTypeDto);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        btnDeleteNotificationType.setOnAction(e->{
            try {
                NotificationTypeDto notificationTypeDto=this.getTableNotificationTypes().getSelectionModel().getSelectedItem();
                NotificationTypeDeleteDto notificationTypeDeleteDto=new NotificationTypeDeleteDto();
                notificationTypeDeleteDto.setId(notificationTypeDto.getId());
                NotificationTypeDto notificationTypeDto1=notificationServiceRest.deleteNotificationType(notificationTypeDeleteDto);
                System.out.println(notificationTypeDto1);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        btnChangeNotificationType.setOnAction( e->{
            Scene sc = new Scene(new NotificationTypeChangeView(this.getTableNotificationTypes().getSelectionModel().getSelectedItem()), 600, 200);
            Main.secondStage.setScene(sc);
            Main.secondStage.show();
            Main.secondStage.setTitle("Notification type change");
        });
    }


    private void addElements() {
        try {
            this.hBox1.getChildren().addAll(lblNameOfClass,tfNameOfClass,lblName,ckName,lblLastName,ckLastName,lblLink,ckLink);
            this.hBox2.getChildren().addAll(lblIdVehicle,ckIdVehicle,lblModel,ckModel,lblTypeOfVehicle,ckTypeOfVehicle,lblDateFrom,ckDateFrom);
            this.hBox3.getChildren().addAll(lblDateTo,ckDateTo,lblEmailOfManager,ckEmailOfManager,lblPassword,ckPassword,lblText,tfText);
            this.hBox4.getChildren().addAll(btnChangeNotificationType,btnDeleteNotificationType);
            this.getChildren().addAll(lblAddNotificationType,hBox1,hBox2,hBox3,btnAddNotificationType,tableNotificationTypes,hBox4);


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void initViewElements() {
        hBox1 = new HBox(10);
        hBox2 = new HBox(10);
        hBox3 = new HBox(10);
        hBox4 = new HBox(10);
        lblAddNotificationType=new Label("Add notification");
        lblNameOfClass=new Label("Name of type");
        lblName=new Label("Name");
        lblLastName=new Label("Last name");
        lblLink=new Label("Link");
        lblIdVehicle=new Label("id vehicle");
        lblModel=new Label("Model");
        lblTypeOfVehicle=new Label("Type of vehicle");
        lblDateFrom=new Label("Date from");
        lblDateTo=new Label("Date to");
        lblEmailOfManager=new Label("Email of manager");
        lblPassword=new Label("Password");
        lblText=new Label("Text");

        tfNameOfClass=new TextField();
        ckName=new CheckBox();
        ckLastName=new CheckBox();
        ckLink=new CheckBox();
        ckIdVehicle=new CheckBox();
        ckModel=new CheckBox();
        ckTypeOfVehicle=new CheckBox();
        ckDateFrom=new CheckBox();
        ckDateTo=new CheckBox();
        ckEmailOfManager=new CheckBox();
        ckPassword=new CheckBox();
        tfText=new TextField();
        btnAddNotificationType=new Button("Add notification type");
        btnChangeNotificationType=new Button("Change notification type");
        btnDeleteNotificationType=new Button("Delete notification type");

        notificationTypeDtos = FXCollections.observableArrayList();
        tableNotificationTypes = new TableView<>(notificationTypeDtos);

        TableColumn<NotificationTypeDto, String> col1 = new TableColumn<>("Name of type");
        col1.setCellValueFactory(new PropertyValueFactory<>("nameOfClass"));
        TableColumn<NotificationTypeDto, Boolean> col2 = new TableColumn<>("Name");
        col2.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<NotificationTypeDto, Boolean> col3 = new TableColumn<>("Last name");
        col3.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        TableColumn<NotificationTypeDto, Boolean> col4 = new TableColumn<>("Link");
        col4.setCellValueFactory(new PropertyValueFactory<>("link"));
        TableColumn<NotificationTypeDto, Boolean> col5 = new TableColumn<>("Id vehicle");
        col5.setCellValueFactory(new PropertyValueFactory<>("idVehicle"));
        TableColumn<NotificationTypeDto, Boolean> col6 = new TableColumn<>("Model");
        col6.setCellValueFactory(new PropertyValueFactory<>("model"));
        TableColumn<NotificationTypeDto, Boolean> col7 = new TableColumn<>("Type of vehicle");
        col7.setCellValueFactory(new PropertyValueFactory<>("typeOfVehicle"));
        TableColumn<NotificationTypeDto, Boolean> col8 = new TableColumn<>("Date from");
        col8.setCellValueFactory(new PropertyValueFactory<>("dateFrom"));
        TableColumn<NotificationTypeDto, Boolean> col9 = new TableColumn<>("Date to");
        col9.setCellValueFactory(new PropertyValueFactory<>("dateTo"));
        TableColumn<NotificationTypeDto, Boolean> col10 = new TableColumn<>("Email of manager");
        col10.setCellValueFactory(new PropertyValueFactory<>("emailOfManager"));
        TableColumn<NotificationTypeDto, Boolean> col11 = new TableColumn<>("Password");
        col11.setCellValueFactory(new PropertyValueFactory<>("password"));
        TableColumn<NotificationTypeDto, String> col12 = new TableColumn<>("Text");
        col12.setCellValueFactory(new PropertyValueFactory<>("text"));



        tableNotificationTypes.getColumns().add(col1);
        tableNotificationTypes.getColumns().add(col2);
        tableNotificationTypes.getColumns().add(col3);
        tableNotificationTypes.getColumns().add(col4);
        tableNotificationTypes.getColumns().add(col5);
        tableNotificationTypes.getColumns().add(col6);
        tableNotificationTypes.getColumns().add(col7);
        tableNotificationTypes.getColumns().add(col8);
        tableNotificationTypes.getColumns().add(col9);
        tableNotificationTypes.getColumns().add(col10);
        tableNotificationTypes.getColumns().add(col11);
        tableNotificationTypes.getColumns().add(col12);




    }

    public ObservableList<NotificationTypeDto> getNotificationTypeDtos() {
        return notificationTypeDtos;
    }

    public TableView<NotificationTypeDto> getTableNotificationTypes() {
        return tableNotificationTypes;
    }

    public TextField getTfNameOfClass() {
        return tfNameOfClass;
    }

    public CheckBox getCkName() {
        return ckName;
    }

    public CheckBox getCkLastName() {
        return ckLastName;
    }

    public CheckBox getCkLink() {
        return ckLink;
    }

    public CheckBox getCkIdVehicle() {
        return ckIdVehicle;
    }

    public CheckBox getCkModel() {
        return ckModel;
    }

    public CheckBox getCkTypeOfVehicle() {
        return ckTypeOfVehicle;
    }

    public CheckBox getCkDateFrom() {
        return ckDateFrom;
    }

    public CheckBox getCkDateTo() {
        return ckDateTo;
    }

    public CheckBox getCkEmailOfManager() {
        return ckEmailOfManager;
    }

    public CheckBox getCkPassword() {
        return ckPassword;
    }

    public TextField getTfText() {
        return tfText;
    }
}
