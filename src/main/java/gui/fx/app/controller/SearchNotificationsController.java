package gui.fx.app.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gui.fx.app.ClientApp;
import gui.fx.app.model.User;
import gui.fx.app.restclient.NotificationServiceRest;
import gui.fx.app.restclient.UserServiceRest;
import gui.fx.app.restclient.dtoNotificationService.FindAllNotificationsForParametersDto;
import gui.fx.app.restclient.dtoUserService.UserDto;
import gui.fx.app.view.NotificationHistoryView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.text.SimpleDateFormat;
import java.util.Base64;

public class SearchNotificationsController implements EventHandler<ActionEvent> {
    private UserServiceRest userServiceRest;
    private NotificationServiceRest notificationServiceRest;
    private NotificationHistoryView notificationHistoryView;
    private ObjectMapper objectMapper=new ObjectMapper();

    public SearchNotificationsController(NotificationHistoryView notificationHistoryView) {
        this.notificationHistoryView = notificationHistoryView;
        userServiceRest=new UserServiceRest();
        notificationServiceRest=new NotificationServiceRest();
    }
    private User getUser(String token) throws JsonProcessingException {
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(token.split("[.]")[1]));
        User userMapper = objectMapper.readValue(payload, User.class);
        return userMapper;
    }
    @Override
    public void handle(ActionEvent event) {
        try {
            FindAllNotificationsForParametersDto findAllNotificationsForParametersDto=new FindAllNotificationsForParametersDto();
            UserDto userDto= null;
            userDto = userServiceRest.getUserById(this.getUser(ClientApp.getInstance().getToken()).getId());
            findAllNotificationsForParametersDto.setEmail(userDto.getEmail());
            findAllNotificationsForParametersDto.setRole(getUser(ClientApp.getInstance().getToken()).getRole());
            findAllNotificationsForParametersDto.setEmailParameter(this.notificationHistoryView.getTfEmail().getText());
            SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
            if(!this.notificationHistoryView.getTfDateFrom().getText().isEmpty())
            findAllNotificationsForParametersDto.setFromDate(formatter.parse(this.notificationHistoryView.getTfDateFrom().getText()));
            if(!this.notificationHistoryView.getTfDateTo().getText().isEmpty())
            findAllNotificationsForParametersDto.setToDate(formatter.parse(this.notificationHistoryView.getTfDateTo().getText()));
            findAllNotificationsForParametersDto.setNotificationTypeName(this.notificationHistoryView.getCbTypeOfNotification().getSelectionModel().getSelectedItem().toString());
            this.notificationHistoryView.getNotificationDtos().clear();
            this.notificationHistoryView.getNotificationDtos().addAll(notificationServiceRest.getAllNotificationsForParameters(findAllNotificationsForParametersDto));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
