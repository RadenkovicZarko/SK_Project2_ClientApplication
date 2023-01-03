package gui.fx.app.controller;

import gui.fx.app.ClientApp;
import gui.fx.app.restclient.UserServiceRest;
import gui.fx.app.restclient.dtoUserService.ClientChangeParametersDto;
import gui.fx.app.restclient.dtoUserService.ClientDto;
import gui.fx.app.restclient.dtoUserService.ManagerChangeParametersDto;
import gui.fx.app.restclient.dtoUserService.ManagerDto;
import gui.fx.app.view.ClientChangeDataView;
import gui.fx.app.view.ManagerChangeDataView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ManagerChangeDataController implements EventHandler<ActionEvent> {
    private ManagerChangeDataView managerChangeDataView;
    private UserServiceRest userServiceRest;

    public ManagerChangeDataController(ManagerChangeDataView managerChangeDataView) {
        this.managerChangeDataView = managerChangeDataView;
        userServiceRest=new UserServiceRest();
    }

    @Override
    public void handle(ActionEvent event) {
        try {
            String token= ClientApp.getInstance().getToken();

            ManagerChangeParametersDto managerChangeParametersDto=new ManagerChangeParametersDto();
            managerChangeParametersDto.setToken(token);
            managerChangeParametersDto.setEmail(managerChangeDataView.getTfEmail().getText());
            managerChangeParametersDto.setFirstName(managerChangeDataView.getTfFirstName().getText());
            managerChangeParametersDto.setLastName(managerChangeDataView.getTfLastName().getText());
            managerChangeParametersDto.setUsername(managerChangeDataView.getTfUsername().getText());
            SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
            managerChangeParametersDto.setDateOfBirth(formatter.parse(managerChangeDataView.getTfBirth().getText()));
            managerChangeParametersDto.setContactNo(managerChangeDataView.getTfPhone().getText());
            managerChangeParametersDto.setDateOfEmployment(formatter.parse(managerChangeDataView.getTfDateOfEmployment().getText()));

            ManagerDto managerDto =userServiceRest.updateManager(managerChangeParametersDto);
            System.out.println(managerDto.getId());
            System.out.println(token);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
