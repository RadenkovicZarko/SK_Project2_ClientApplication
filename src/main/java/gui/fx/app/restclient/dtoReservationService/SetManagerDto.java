package gui.fx.app.restclient.dtoReservationService;

public class SetManagerDto {
    private String nameOfcompany;
    private Long id_manager;

    public String getNameOfcompany() {
        return nameOfcompany;
    }

    public void setNameOfcompany(String nameOfcompany) {
        this.nameOfcompany = nameOfcompany;
    }

    public Long getId_manager() {
        return id_manager;
    }

    public void setId_manager(Long id_manager) {
        this.id_manager = id_manager;
    }
}
