package gui.fx.app.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gui.fx.app.ClientApp;
import gui.fx.app.Main;
import gui.fx.app.model.User;
import gui.fx.app.restclient.UserServiceRest;
import gui.fx.app.view.AdminChangeDataView;
import gui.fx.app.view.ClientChangeDataView;
import gui.fx.app.view.ManagerChangeDataView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

import javax.jws.soap.SOAPBinding;
import java.io.IOException;
import java.util.Base64;

public class LoginController implements EventHandler<ActionEvent> {

    private TextField email;
    private TextField password;
    private UserServiceRest userServiceRest;
    private ObjectMapper objectMapper=new ObjectMapper();

    public LoginController(TextField email, TextField password) {
        this.email = email;
        this.password = password;
        userServiceRest = new UserServiceRest();
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
            String token = userServiceRest.login(email.getText(), password.getText());
            ClientApp.getInstance().setToken(token);
            User user=getUser(token);

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
//            Scene sc = new Scene(new ReservationView(), 800, 800);
//            Main.mainStage.setScene(sc);
//            Main.mainStage.show();
            System.out.println(token);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
