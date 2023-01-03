package gui.fx.app.restclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import gui.fx.app.ClientApp;
import gui.fx.app.model.User;
import gui.fx.app.restclient.dtoReservationService.CompanyInformationDto;
import gui.fx.app.restclient.dtoReservationService.SearchCompanyDto;
import gui.fx.app.restclient.dtoReservationService.SearchCompanyListDto;
import gui.fx.app.restclient.dtoReservationService.SetManagerDto;
import okhttp3.*;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

public class ReservationCompanyServiceRest {
    public static final String URL = "http://localhost:8084/reservationservice/api";
    /// http://localhost:8080/api


    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();
    ObjectMapper objectMapper = new ObjectMapper();


    public List<SearchCompanyDto> getCompanies() throws IOException
    {
        //objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Request request = new Request.Builder()
                .url(URL + "/company/findAll")
                .get()
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        if (response.code() >= 200 && response.code() <= 300) {
            String json = response.body().string();
            System.out.println(json);
            return objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, SearchCompanyDto.class));
        }
        throw new RuntimeException();
    }

    private User getUser(String token) throws JsonProcessingException {
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(token.split("[.]")[1]));
        User userMapper = objectMapper.readValue(payload, User.class);
        return userMapper;
    }

    public CompanyInformationDto setManagerForCompany(SetManagerDto setManagerDto) throws IOException
    {
        System.out.println(setManagerDto.getNameOfcompany()+" Prosa");
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(setManagerDto));
        System.out.println(body.toString());
        Request request = new Request.Builder()
                .url(URL + "/company/setManager")
                .post(body)
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();
        System.out.println(response.code());
        if (response.code() >= 200 && response.code() <= 300) {
            String json = response.body().string();
            return objectMapper.readValue(json, CompanyInformationDto.class);
        }

        throw new RuntimeException("Something went wrong");
    }
}
