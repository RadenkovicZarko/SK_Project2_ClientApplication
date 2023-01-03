package gui.fx.app.restclient.dtoReservationService;

public class FindCompanyByManagerDto {
    Long manager_id;

    public Long getManager_id() {
        return manager_id;
    }

    public void setManager_id(Long manager_id) {
        this.manager_id = manager_id;
    }

    public FindCompanyByManagerDto(Long manager_id) {
        this.manager_id = manager_id;
    }
}
