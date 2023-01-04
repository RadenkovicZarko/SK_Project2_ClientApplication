package gui.fx.app.restclient.dtoReservationService;

public class SearchCompanyDto {
    private String companyName;
    private Long id;
    public SearchCompanyDto() {
    }

    public SearchCompanyDto(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return "SearchCompanyDto{" +
                "companyName='" + companyName + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
