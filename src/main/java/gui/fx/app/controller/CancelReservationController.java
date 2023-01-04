package gui.fx.app.controller;

import gui.fx.app.restclient.ReservationCompanyServiceRest;
import gui.fx.app.restclient.dtoReservationService.ReservationCancelDto;
import gui.fx.app.restclient.dtoReservationService.ReservationDto;

import gui.fx.app.view.ReservationView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.util.Date;

public class CancelReservationController implements EventHandler<ActionEvent> {
    private ReservationView reservationView;
    private ReservationCompanyServiceRest reservationCompanyServiceRest;

    public CancelReservationController(ReservationView reservationView) {
        this.reservationView = reservationView;
        reservationCompanyServiceRest=new ReservationCompanyServiceRest();
    }

    @Override
    public void handle(ActionEvent event) {
        ReservationDto selected = reservationView.getUserReservationtable().getSelectionModel().getSelectedItem();

        Date date=new Date();
        if(selected.getDate_from().getTime()<date.getTime())
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("You cannot cancel a reservation that has passed");
            alert.showAndWait();
            return;
        }
        ReservationCancelDto reservationCancelDto=new ReservationCancelDto();
        reservationCancelDto.setId(selected.getId());
        reservationCancelDto.setId_vehicle(selected.getId_vehicle());
        reservationCancelDto.setUserId(selected.getUserId());
        reservationCancelDto.setDate_from(selected.getDate_from());
        reservationCancelDto.setDate_to(selected.getDate_to());
        reservationCancelDto.setPrice(selected.getPrice());

        try {
            ReservationDto reservationDto=reservationCompanyServiceRest.cancelReservations(reservationCancelDto);
            reservationView.getReservationList().remove(reservationView.getUserReservationtable().getSelectionModel().getFocusedIndex());
            System.out.println(reservationDto);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
