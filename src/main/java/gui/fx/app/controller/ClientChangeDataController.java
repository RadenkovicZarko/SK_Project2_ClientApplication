package gui.fx.app.controller;

import gui.fx.app.ClientApp;
import gui.fx.app.Main;
import gui.fx.app.model.User;
import gui.fx.app.restclient.UserServiceRest;
import gui.fx.app.restclient.dtoUserService.ClientChangeParametersDto;
import gui.fx.app.restclient.dtoUserService.ClientDto;
import gui.fx.app.view.ClientChangeDataView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientChangeDataController implements EventHandler<ActionEvent> {

    private ClientChangeDataView clientChangeDataView;
    private UserServiceRest userServiceRest;

    public ClientChangeDataController(ClientChangeDataView clientChangeDataView) {
        this.clientChangeDataView = clientChangeDataView;
        userServiceRest=new UserServiceRest();
    }

    @Override
    public void handle(ActionEvent event) {
        try {
            String token=ClientApp.getInstance().getToken();

            ClientChangeParametersDto clientChangeParametersDto=new ClientChangeParametersDto();
            clientChangeParametersDto.setToken(token);
            clientChangeParametersDto.setEmail(clientChangeDataView.getTfEmail().getText());
            clientChangeParametersDto.setFirstName(clientChangeDataView.getTfFirstName().getText());
            clientChangeParametersDto.setLastName(clientChangeDataView.getTfLastName().getText());
            clientChangeParametersDto.setUsername(clientChangeDataView.getTfUsername().getText());
            SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
            clientChangeParametersDto.setDateOfBirth(formatter.parse(clientChangeDataView.getTfBirth().getText()));
            clientChangeParametersDto.setContactNo(clientChangeDataView.getTfPhone().getText());
            clientChangeParametersDto.setPassportNo(Long.parseLong(clientChangeDataView.getTfPassport().getText()));

            ClientDto clientDto=userServiceRest.updateClient(clientChangeParametersDto);
            System.out.println(clientDto.getId());
            System.out.println(token);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
