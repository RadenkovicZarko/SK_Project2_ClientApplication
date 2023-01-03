package gui.fx.app.controller;

import gui.fx.app.Main;
import gui.fx.app.restclient.ReservationCompanyServiceRest;
import gui.fx.app.restclient.UserServiceRest;
import gui.fx.app.restclient.dtoReservationService.SetManagerDto;
import gui.fx.app.restclient.dtoUserService.ManagerCreateDto;
import gui.fx.app.restclient.dtoUserService.ManagerDto;
import gui.fx.app.view.RegisterManagerView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.IOException;
import java.sql.Date;

public class RegisterManagerController implements EventHandler<ActionEvent> {
    private RegisterManagerView registerManagerView;
    private UserServiceRest userServiceRestClient;
    private ReservationCompanyServiceRest reservationCompanyServiceRest;

    public RegisterManagerController(RegisterManagerView registerManagerView) {
        this.registerManagerView = registerManagerView;
        this.userServiceRestClient = new UserServiceRest();
        this.reservationCompanyServiceRest = new ReservationCompanyServiceRest();
    }

    @Override
    public void handle(ActionEvent event) {
        ManagerCreateDto managerCreateDto = new ManagerCreateDto();
        managerCreateDto.setFirstName(registerManagerView.getTfFirstName().getText());
        managerCreateDto.setLastName(registerManagerView.getTfLastName().getText());
        managerCreateDto.setEmail(registerManagerView.getTfEmail().getText());
        managerCreateDto.setUsername(registerManagerView.getTfUsername().getText());
        managerCreateDto.setPassword(registerManagerView.getTfPassword().getText());
        managerCreateDto.setDateOfBirth(Date.valueOf(registerManagerView.getTfBirth().getText()));
        managerCreateDto.setContactNo(registerManagerView.getTfPhone().getText());
        managerCreateDto.setNameOfCompany(registerManagerView.getComboBox().getSelectionModel().getSelectedItem().toString());
        managerCreateDto.setDateOfEmployment(Date.valueOf(registerManagerView.getTfDateOfEmployment().getText()));

        try {
            ManagerDto managerDto = userServiceRestClient.registerManager(managerCreateDto);
            SetManagerDto setManagerDto =new SetManagerDto();
            setManagerDto.setId_manager(managerDto.getId());
            setManagerDto.setNameOfcompany(registerManagerView.getComboBox().getSelectionModel().getSelectedItem().toString());
            reservationCompanyServiceRest.setManagerForCompany(setManagerDto);
            System.out.println(managerDto);
            Main.secondStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
