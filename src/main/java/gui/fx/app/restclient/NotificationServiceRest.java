package gui.fx.app.restclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import gui.fx.app.restclient.dtoNotificationService.*;
import gui.fx.app.restclient.dtoReservationService.CompanyInformationDto;
import gui.fx.app.restclient.dtoReservationService.ModelDto;
import gui.fx.app.restclient.dtoReservationService.SearchCompanyDto;
import gui.fx.app.restclient.dtoReservationService.SetManagerDto;
import okhttp3.*;

import java.io.IOException;
import java.util.List;

public class NotificationServiceRest {

    public static final String URL = "http://localhost:8084/notificationservice/api";
    /// http://localhost:8080/api


    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();
    ObjectMapper objectMapper = new ObjectMapper();


    public NotificationTypeDto addNotificationType(NotificationTypeCreateDto notificationTypeCreateDto) throws IOException
    {
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(notificationTypeCreateDto));
        System.out.println(body.toString());
        Request request = new Request.Builder()
                .url(URL + "/notificationType/add")
                .post(body)
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();
        System.out.println(response.code());
        if (response.code() >= 200 && response.code() <= 300) {
            String json = response.body().string();
            return objectMapper.readValue(json, NotificationTypeDto.class);
        }
        throw new RuntimeException("Something went wrong");
    }

    public List<NotificationTypeDto> findAll() throws IOException
    {
        //objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Request request = new Request.Builder()
                .url(URL + "/notificationType/findAll")
                .get()
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        if (response.code() >= 200 && response.code() <= 300) {
            String json = response.body().string();
            System.out.println(json);
            return objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, NotificationTypeDto.class));
        }
        throw new RuntimeException();
    }

    public NotificationTypeDto updateNotificationType(NotificationTypeChangeDto notificationTypeChangeDto) throws IOException
    {
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(notificationTypeChangeDto));
        System.out.println(body.toString());
        Request request = new Request.Builder()
                .url(URL + "/notificationType/update")
                .post(body)
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();
        System.out.println(response.code());
        if (response.code() >= 200 && response.code() <= 300) {
            String json = response.body().string();
            return objectMapper.readValue(json, NotificationTypeDto.class);
        }
        throw new RuntimeException("Something went wrong");
    }

    public NotificationTypeDto deleteNotificationType(NotificationTypeDeleteDto notificationTypeDeleteDto) throws IOException
    {
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(notificationTypeDeleteDto));
        System.out.println(body.toString());
        Request request = new Request.Builder()
                .url(URL + "/notificationType/delete")
                .post(body)
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();
        System.out.println(response.code());
        if (response.code() >= 200 && response.code() <= 300) {
            String json = response.body().string();
            return objectMapper.readValue(json, NotificationTypeDto.class);
        }
        throw new RuntimeException("Something went wrong");
    }


    public List<NotificationDto> getAllNotificationsForParameters(FindAllNotificationsForParametersDto findAllNotificationsForParametersDto) throws IOException
    {
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(findAllNotificationsForParametersDto));
        //objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Request request = new Request.Builder()
                .url(URL + "/notification/findAllForParameters")
                .post(body)
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        if (response.code() >= 200 && response.code() <= 300) {
            String json = response.body().string();
            System.out.println(json);
            return objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, NotificationDto.class));
        }
        throw new RuntimeException();
    }


}
