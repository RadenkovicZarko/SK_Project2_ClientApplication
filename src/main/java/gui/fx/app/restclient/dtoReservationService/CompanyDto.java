package gui.fx.app.restclient.dtoReservationService;

public class CompanyDto {
    private String companyName;
    private Long rating;

    public CompanyDto() {
    }

    public CompanyDto(String companyName, Long rating) {
        this.companyName = companyName;
        this.rating = rating;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }
}
