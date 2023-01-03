package gui.fx.app.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gui.fx.app.ClientApp;
import gui.fx.app.model.User;
import gui.fx.app.restclient.ReservationCompanyServiceRest;
import gui.fx.app.restclient.dtoReservationService.ReservationCreateDto;
import gui.fx.app.restclient.dtoReservationService.ReservationDto;
import gui.fx.app.restclient.dtoReservationService.VehicleDto;
import gui.fx.app.view.ReservationView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;

public class BookController implements EventHandler<ActionEvent> {

    private ReservationView reservationView;
    private ReservationCompanyServiceRest reservationCompanyServiceRest;
    private ObjectMapper objectMapper=new ObjectMapper();

    public BookController(ReservationView reservationView) {
        this.reservationView = reservationView;
        this.reservationCompanyServiceRest=new ReservationCompanyServiceRest();
    }
    private User getUser(String token) throws JsonProcessingException {
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(token.split("[.]")[1]));
        User userMapper = objectMapper.readValue(payload, User.class);
        return userMapper;
    }

    @Override
    public void handle(ActionEvent event) {
        VehicleDto selected = reservationView.getTableVehicles().getSelectionModel().getSelectedItem();
        try {
            if(this.reservationView.getTfFrom().getText().isEmpty() || this.reservationView.getTfTo().getText().isEmpty())
                return;
            User user =getUser(ClientApp.getInstance().getToken());
            ReservationCreateDto reservationCreateDto=new ReservationCreateDto();
            reservationCreateDto.setUserId(user.getId());
            reservationCreateDto.setVehicleId(selected.getId());
            SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
            reservationCreateDto.setDate_from(formatter.parse(this.reservationView.getTfFrom().getText()));
            reservationCreateDto.setDate_to(formatter.parse(this.reservationView.getTfTo().getText()));

            ReservationDto reservation = reservationCompanyServiceRest.makeReservation(reservationCreateDto);
            reservationView.getReservationList().add(reservation);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
