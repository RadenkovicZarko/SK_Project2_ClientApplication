package gui.fx.app.controller;

import gui.fx.app.ClientApp;
import gui.fx.app.restclient.UserServiceRest;
import gui.fx.app.restclient.dtoUserService.AdminChangeParametersDto;
import gui.fx.app.restclient.dtoUserService.AdminDto;
import gui.fx.app.restclient.dtoUserService.ClientChangeParametersDto;
import gui.fx.app.restclient.dtoUserService.ClientDto;
import gui.fx.app.view.AdminChangeDataView;
import gui.fx.app.view.ClientChangeDataView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AdminChangeDataController implements EventHandler<ActionEvent> {
    private AdminChangeDataView adminChangeDataView;
    private UserServiceRest userServiceRest;

    public AdminChangeDataController(AdminChangeDataView adminChangeDataView) {
        this.adminChangeDataView = adminChangeDataView;
        userServiceRest=new UserServiceRest();
    }

    @Override
    public void handle(ActionEvent event) {
        try {
            String token= ClientApp.getInstance().getToken();

            AdminChangeParametersDto adminChangeParametersDto=new AdminChangeParametersDto();
            adminChangeParametersDto.setToken(token);
            adminChangeParametersDto.setEmail(adminChangeDataView.getTfEmail().getText());
            adminChangeParametersDto.setFirstName(adminChangeDataView.getTfFirstName().getText());
            adminChangeParametersDto.setLastName(adminChangeDataView.getTfLastName().getText());
            adminChangeParametersDto.setUsername(adminChangeDataView.getTfUsername().getText());
            SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
            adminChangeParametersDto.setDateOfBirth(formatter.parse(adminChangeDataView.getTfBirth().getText()));
            adminChangeParametersDto.setContactNo(adminChangeDataView.getTfPhone().getText());


            AdminDto admintDto=userServiceRest.updateAdmin(adminChangeParametersDto);
            System.out.println(admintDto.getId());
            System.out.println(token);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
