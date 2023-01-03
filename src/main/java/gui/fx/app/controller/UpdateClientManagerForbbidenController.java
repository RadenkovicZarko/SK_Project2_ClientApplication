package gui.fx.app.controller;

import gui.fx.app.ClientApp;
import gui.fx.app.restclient.UserServiceRest;
import gui.fx.app.restclient.dtoUserService.AdminChangeParametersDto;
import gui.fx.app.restclient.dtoUserService.AdminDto;
import gui.fx.app.restclient.dtoUserService.ClientForbidenDto;
import gui.fx.app.restclient.dtoUserService.UserDto;
import gui.fx.app.view.AdminChangeDataView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class UpdateClientManagerForbbidenController implements EventHandler<ActionEvent> {

    private AdminChangeDataView adminChangeDataView;
    private UserServiceRest userServiceRest;
    private Long id;

    public UpdateClientManagerForbbidenController(Long id,AdminChangeDataView adminChangeDataView) {
        this.id=id;
        this.adminChangeDataView = adminChangeDataView;
        userServiceRest=new UserServiceRest();
    }


    @Override
    public void handle(ActionEvent event) {
        try {
            System.out.println("USAOOO");
            String token= ClientApp.getInstance().getToken();
            ClientForbidenDto clientForbidenDto=new ClientForbidenDto();
            String[] user=adminChangeDataView.getComboBoxForbid().getSelectionModel().getSelectedItem().toString().split(" ");

            clientForbidenDto.setId(Long.valueOf(user[0]));
            clientForbidenDto.setForbidden(!Boolean.parseBoolean(user[2]));
            System.out.println(clientForbidenDto.getId()+" "+clientForbidenDto.isForbidden());
            UserDto userDto=userServiceRest.updateForbiden(clientForbidenDto);
            System.out.println(userDto);


        } catch (Exception e ) {
            e.printStackTrace();
        }
    }

}
