package gui.fx.app.restclient.dtoReservationService;

public class SearchCompanyDto {
    private String companyName;

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
}
