package gui.fx.app.restclient.dtoUserService;

public class RankDto {
    private Long id;
    private Integer minNumberOfRentingDays;
    private Integer maxNumberOfRentingDays;
    private Integer discount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMinNumberOfRentingDays() {
        return minNumberOfRentingDays;
    }

    public void setMinNumberOfRentingDays(Integer minNumberOfRentingDays) {
        this.minNumberOfRentingDays = minNumberOfRentingDays;
    }

    public Integer getMaxNumberOfRentingDays() {
        return maxNumberOfRentingDays;
    }

    public void setMaxNumberOfRentingDays(Integer maxNumberOfRentingDays) {
        this.maxNumberOfRentingDays = maxNumberOfRentingDays;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }
}
