package gui.fx.app.restclient.dtoReservationService;

import java.util.ArrayList;
import java.util.List;

public class SearchCompanyListDto {
    private List<SearchCompanyDto> content =new ArrayList<>();


    public SearchCompanyListDto() {
    }

    public SearchCompanyListDto(List<SearchCompanyDto> content) {
        this.content = content;
    }

    public List<SearchCompanyDto> getContent() {
        return content;
    }

    public void setContent(List<SearchCompanyDto> content) {
        this.content = content;
    }
}
