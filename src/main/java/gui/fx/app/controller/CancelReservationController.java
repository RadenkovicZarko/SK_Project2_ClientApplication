package gui.fx.app.controller;

import gui.fx.app.restclient.ReservationCompanyServiceRest;
import gui.fx.app.restclient.dtoReservationService.ReservationCancelDto;
import gui.fx.app.restclient.dtoReservationService.ReservationDto;
import gui.fx.app.restclient.dtoReservationService.VehicleDto;
import gui.fx.app.view.ReservationView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.IOException;

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

        ReservationCancelDto reservationCancelDto=new ReservationCancelDto();
        reservationCancelDto.setId(selected.getId_vehicle());
        reservationCancelDto.setUserId(selected.getUserId());
        reservationCancelDto.setDate_from(selected.getDate_from());
        reservationCancelDto.setDate_to(selected.getDate_to());
        reservationCancelDto.setPrice(selected.getPrice());

        try {
            ReservationDto reservationDto=reservationCompanyServiceRest.cancelReservations(reservationCancelDto);
            System.out.println(reservationDto);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
