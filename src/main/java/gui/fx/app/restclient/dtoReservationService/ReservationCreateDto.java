package gui.fx.app.restclient.dtoReservationService;

import java.util.Date;

public class ReservationCreateDto {

    private Long vehicleId;
    private Long userId;
    private Date date_from;
    private Date date_to;


    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getDate_from() {
        return date_from;
    }

    public void setDate_from(Date date_from) {
        this.date_from = date_from;
    }

    public Date getDate_to() {
        return date_to;
    }


    public void setDate_to(Date date_to) {
        this.date_to = date_to;
    }

}
