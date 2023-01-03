package gui.fx.app.restclient.dtoReservationService;

import java.util.Date;

public class SearchDto {

    private Date from;
    private Date to;
    private String companyName;
    private Boolean sort;
    private String city;

    public SearchDto() {
    }

    public SearchDto(Date from, Date to, String companyName, Boolean sort, String city) {
        this.from = from;
        this.to = to;
        this.companyName = companyName;
        this.sort = sort;
        this.city = city;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Boolean getSort() {
        return sort;
    }

    public void setSort(Boolean sort) {
        this.sort = sort;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
