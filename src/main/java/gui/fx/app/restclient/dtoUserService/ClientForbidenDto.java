package gui.fx.app.restclient.dtoUserService;

public class ClientForbidenDto {
    private Long id;
    private boolean forbidden;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isForbidden() {
        return forbidden;
    }

    public void setForbidden(boolean forbidden) {
        this.forbidden = forbidden;
    }
}
