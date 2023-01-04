package gui.fx.app.view;

import gui.fx.app.Main;
import gui.fx.app.restclient.NotificationServiceRest;
import gui.fx.app.restclient.dtoNotificationService.NotificationTypeChangeDto;
import gui.fx.app.restclient.dtoNotificationService.NotificationTypeCreateDto;
import gui.fx.app.restclient.dtoNotificationService.NotificationTypeDeleteDto;
import gui.fx.app.restclient.dtoNotificationService.NotificationTypeDto;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class NotificationTypeChangeView extends VBox {
    private HBox hBox1;
    private HBox hBox2;
    private HBox hBox3;

    private Label lblAddNotificationType;

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
    private Button btnChangeNotificationType;


    private NotificationServiceRest notificationServiceRest=new NotificationServiceRest();
    private NotificationTypeDto notificationTypeDto;

    public NotificationTypeChangeView(NotificationTypeDto notificationTypeDto){
        this.notificationTypeDto=notificationTypeDto;
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

    }

    private void addListeners() {
        btnChangeNotificationType.setOnAction(e->{
            try {
                NotificationTypeChangeDto notificationTypeChangeDto=new NotificationTypeChangeDto();
                notificationTypeChangeDto.setId(notificationTypeDto.getId());
                notificationTypeChangeDto.setName(this.getCkName().isSelected());
                notificationTypeChangeDto.setLastName(this.getCkLastName().isSelected());
                notificationTypeChangeDto.setLink(this.getCkLink().isSelected());
                notificationTypeChangeDto.setIdVehicle(this.getCkIdVehicle().isSelected());
                notificationTypeChangeDto.setModel(this.getCkModel().isSelected());
                notificationTypeChangeDto.setTypeOfVehicle(this.getCkTypeOfVehicle().isSelected());
                notificationTypeChangeDto.setDateFrom(this.getCkDateFrom().isSelected());
                notificationTypeChangeDto.setDateTo(this.getCkDateTo().isSelected());
                notificationTypeChangeDto.setEmailOfManager(this.getCkEmailOfManager().isSelected());
                notificationTypeChangeDto.setPassword(this.getCkPassword().isSelected());
                notificationTypeChangeDto.setText(this.getTfText().getText());
                NotificationTypeDto ntd = notificationServiceRest.updateNotificationType(notificationTypeChangeDto);
                System.out.println(ntd);
                Main.secondStage.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            System.out.println(notificationTypeDto);
        });
    }


    private void addElements() {
        try {
            this.hBox1.getChildren().addAll(lblName,ckName,lblLastName,ckLastName,lblLink,ckLink);
            this.hBox2.getChildren().addAll(lblIdVehicle,ckIdVehicle,lblModel,ckModel,lblTypeOfVehicle,ckTypeOfVehicle,lblDateFrom,ckDateFrom);
            this.hBox3.getChildren().addAll(lblDateTo,ckDateTo,lblEmailOfManager,ckEmailOfManager,lblPassword,ckPassword,lblText,tfText);
            this.getChildren().addAll(lblAddNotificationType,hBox1,hBox2,hBox3,btnChangeNotificationType);
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
        lblAddNotificationType=new Label("Add notification");

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
        btnChangeNotificationType=new Button("Change notification type");


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
