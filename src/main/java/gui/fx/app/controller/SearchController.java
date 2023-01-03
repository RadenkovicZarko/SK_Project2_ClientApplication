package gui.fx.app.controller;

import gui.fx.app.restclient.ReservationCompanyServiceRest;
import gui.fx.app.restclient.dtoReservationService.SearchDto;
import gui.fx.app.restclient.dtoReservationService.VehicleDto;
import gui.fx.app.view.ReservationView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.List;

public class SearchController implements EventHandler<ActionEvent> {
    private ReservationView reservationView;
    private ReservationCompanyServiceRest reservationCompanyServiceRest;

    public SearchController(ReservationView reservationView) {
        this.reservationView = reservationView;
        reservationCompanyServiceRest=new ReservationCompanyServiceRest();
    }


    @Override
    public void handle(ActionEvent event) {
        try {
            reservationView.getVehicleDtos().clear();
            SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
            SearchDto searchDto=new SearchDto();

            if(!reservationView.getTfFrom().getText().isEmpty())
                searchDto.setFrom(formatter.parse(reservationView.getTfFrom().getText()));

            if(!reservationView.getTfTo().getText().isEmpty())
                searchDto.setTo(formatter.parse(reservationView.getTfTo().getText()));

            if(!reservationView.getTfCompanyName().getText().isEmpty())
                searchDto.setCompanyName(reservationView.getTfCompanyName().getText());

            if(!reservationView.getTfCity().getText().isEmpty())
                searchDto.setCity(reservationView.getTfCity().getText());

            searchDto.setSort(reservationView.getCheckBoxSort().isSelected());


            List<VehicleDto> lista = reservationCompanyServiceRest.findAllAvailableVehicle(searchDto);
            reservationView.getVehicleDtos().addAll(lista);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
