package gui.fx.app.restclient.dtoUserService;

public class SearchUserDto {
    private Long id;

    public SearchUserDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
