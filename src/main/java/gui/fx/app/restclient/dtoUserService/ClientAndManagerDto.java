package gui.fx.app.restclient.dtoUserService;

public class ClientAndManagerDto {
    private Long id;
    private String email;
    private boolean forbidden;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isForbidden() {
        return forbidden;
    }

    public void setForbidden(boolean forbidden) {
        this.forbidden = forbidden;
    }

    @Override
    public String toString() {
        return
                 id+" " +
                 email  +
                " " + forbidden;
    }
}
