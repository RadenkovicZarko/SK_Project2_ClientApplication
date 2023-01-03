package gui.fx.app.controller;

import gui.fx.app.Main;
import gui.fx.app.restclient.UserServiceRest;
import gui.fx.app.restclient.dtoUserService.ClientCreateDto;
import gui.fx.app.restclient.dtoUserService.ClientDto;
import gui.fx.app.view.RegisterClientView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class RegisterClientController implements EventHandler<ActionEvent> {

    private RegisterClientView registerClientView;
    private UserServiceRest userServiceRestClient;

    public RegisterClientController(RegisterClientView registerClientView) {
        this.registerClientView = registerClientView;
        this.userServiceRestClient=new UserServiceRest();
    }

    @Override
    public void handle(ActionEvent event) {
        ClientCreateDto clientCreateDto = new ClientCreateDto();
        clientCreateDto.setFirstName(registerClientView.getTfFirstName().getText());
        clientCreateDto.setLastName(registerClientView.getTfLastName().getText());
        clientCreateDto.setEmail(registerClientView.getTfEmail().getText());
        clientCreateDto.setUsername(registerClientView.getTfUsername().getText());
        clientCreateDto.setPassword(registerClientView.getTfPassword().getText());
        SimpleDateFormat formatter=new SimpleDateFormat("dd-MMM-yyyy");
        clientCreateDto.setDateOfBirth(Date.valueOf(registerClientView.getTfBirth().getText()));
        clientCreateDto.setContactNo(registerClientView.getTfPhone().getText());
        clientCreateDto.setPassportNo(Long.parseLong(registerClientView.getTfPassport().getText()));

        try {
            ClientDto client = userServiceRestClient.registerClient(clientCreateDto);
            System.out.println(client);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
