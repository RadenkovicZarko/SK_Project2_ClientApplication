package gui.fx.app.controller;

import gui.fx.app.Main;
import gui.fx.app.restclient.UserServiceRest;
import gui.fx.app.restclient.dtoUserService.UserChangePasswordDto;
import gui.fx.app.restclient.dtoUserService.UserDto;
import gui.fx.app.view.ChangePasswordView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.IOException;

public class ClientChangePasswordController implements EventHandler<ActionEvent> {
    private UserServiceRest userServiceRest;
    private ChangePasswordView changePasswordView;
    private Long id;

    public ClientChangePasswordController(Long id,ChangePasswordView changePasswordView) {
        this.id=id;
        this.changePasswordView= changePasswordView;
        userServiceRest=new UserServiceRest();
    }

    @Override
    public void handle(ActionEvent event) {
        try
        {
            UserChangePasswordDto userChangePasswordDto=new UserChangePasswordDto();
            userChangePasswordDto.setId(id);
            userChangePasswordDto.setNewPassword(changePasswordView.getTfPassword().getText());
            System.out.println(changePasswordView.getTfPassword().getText());
            UserDto userDto=userServiceRest.updatePassword(userChangePasswordDto);
            System.out.println(userDto.toString());
            Main.secondStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
