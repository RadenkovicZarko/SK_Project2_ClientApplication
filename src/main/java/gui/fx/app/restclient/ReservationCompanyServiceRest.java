package gui.fx.app.restclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import gui.fx.app.ClientApp;
import gui.fx.app.model.User;
import gui.fx.app.restclient.dtoReservationService.*;
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


    public List<SearchCompanyDto> getCompaniesAvailable() throws IOException
    {
        //objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Request request = new Request.Builder()
                .url(URL + "/company/findAllAvailable")
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

    public CompanyInformationDto getCompany(FindCompanyByManagerDto findCompanyByManagerDto) throws IOException
    {
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(findCompanyByManagerDto));
        System.out.println(body.toString());
        Request request = new Request.Builder()
                .url(URL + "/company/findByIdOfManager")
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

    public CompanyInformationDto updateCompanyDetails(CompanyInformationDto companyInformationDto) throws IOException
    {
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(companyInformationDto));
        System.out.println(body.toString());
        Request request = new Request.Builder()
                .url(URL + "/company/updateCompanyDetails")
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

    public List<ModelDto> getModels() throws IOException
    {
        //objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Request request = new Request.Builder()
                .url(URL + "/vehicle/findModels")
                .get()
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        if (response.code() >= 200 && response.code() <= 300) {
            String json = response.body().string();
            System.out.println(json);
            return objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, ModelDto.class));
        }
        throw new RuntimeException();
    }

    public List<TypeDto> getTypes() throws IOException
    {
        //objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Request request = new Request.Builder()
                .url(URL + "/vehicle/findTypes")
                .get()
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        if (response.code() >= 200 && response.code() <= 300) {
            String json = response.body().string();
            System.out.println(json);
            return objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, TypeDto.class));
        }
        throw new RuntimeException();
    }

    public VehicleCreateDto addVehicle(VehicleCreateDto vehicleCreateDto) throws IOException
    {
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(vehicleCreateDto));
        System.out.println(body.toString());
        Request request = new Request.Builder()
                .url(URL + "/vehicle/addVehicle")
                .post(body)
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();
        System.out.println(response.code());
        if (response.code() >= 200 && response.code() <= 300) {
            String json = response.body().string();
            return objectMapper.readValue(json, VehicleCreateDto.class);
        }

        throw new RuntimeException("Something went wrong");
    }

    public List<VehicleDto> findAllAvailableVehicle(SearchDto searchDto) throws IOException
    {
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(searchDto));
        //objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Request request = new Request.Builder()
                .url(URL + "/vehicle/findAvailable")
                .post(body)
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        if (response.code() >= 200 && response.code() <= 300) {
            String json = response.body().string();
            System.out.println(json);
            return objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class,VehicleDto.class));
        }
        throw new RuntimeException();
    }

    public ReservationDto makeReservation(ReservationCreateDto reservationCreateDto) throws IOException
    {
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(reservationCreateDto));
        System.out.println(body.toString());
        Request request = new Request.Builder()
                .url(URL + "/reservation/makeReservation")
                .post(body)
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();
        System.out.println(response.code());
        if (response.code() >= 200 && response.code() <= 300) {
            String json = response.body().string();
            return objectMapper.readValue(json, ReservationDto.class);
        }

        throw new RuntimeException("Something went wrong");
    }


    public List<ReservationDto> getReservations(FindReservationsDto findReservationsDto) throws IOException
    {
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(findReservationsDto));
        System.out.println(body.toString());
        Request request = new Request.Builder()
                .url(URL + "/reservation/getReservation")
                .post(body)
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();
        System.out.println(response.code());
        if (response.code() >= 200 && response.code() <= 300) {
            String json = response.body().string();
            return objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class,ReservationDto.class));
        }

        throw new RuntimeException("Something went wrong");
    }


    public ReservationDto cancelReservations(ReservationCancelDto reservationCancelDto) throws IOException
    {
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(reservationCancelDto));
        System.out.println(body.toString());
        Request request = new Request.Builder()
                .url(URL + "/reservation/cancelReservation")
                .post(body)
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();
        System.out.println(response.code());
        if (response.code() >= 200 && response.code() <= 300) {
            String json = response.body().string();
            return objectMapper.readValue(json, ReservationDto.class);
        }

        throw new RuntimeException("Something went wrong");
    }

    public ReviewDto addReview(ReviewCreateDto reviewCreateDto) throws IOException
    {
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(reviewCreateDto));
        System.out.println(body.toString());
        Request request = new Request.Builder()
                .url(URL + "/review/addReview")
                .post(body)
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();
        System.out.println(response.code());
        if (response.code() >= 200 && response.code() <= 300) {
            String json = response.body().string();
            return objectMapper.readValue(json, ReviewDto.class);
        }
        throw new RuntimeException("Something went wrong");
    }

    public List<ReviewTableDto> getReviews(ReviewSearchDto reviewSearchDto) throws IOException
    {
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(reviewSearchDto));
        System.out.println(body.toString());
        Request request = new Request.Builder()
                .url(URL + "/review/findReviews")
                .post(body)
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();
        System.out.println(response.code());
        if (response.code() >= 200 && response.code() <= 300) {
            String json = response.body().string();
            return objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class,ReviewTableDto.class));
        }

        throw new RuntimeException("Something went wrong");
    }
    public ReviewDto changeReview(ChangeReviewDto changeReviewDto) throws IOException
    {
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(changeReviewDto));
        System.out.println(body.toString());
        Request request = new Request.Builder()
                .url(URL + "/review/changeReview")
                .post(body)
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();
        System.out.println(response.code());
        if (response.code() >= 200 && response.code() <= 300) {
            String json = response.body().string();
            return objectMapper.readValue(json, ReviewDto.class);
        }
        throw new RuntimeException("Something went wrong");
    }

    public DeleteReviewDto deleteReview(DeleteReviewDto deleteReviewDto) throws IOException
    {
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(deleteReviewDto));
        System.out.println(body.toString());
        Request request = new Request.Builder()
                .url(URL + "/review/deleteReview")
                .post(body)
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();
        System.out.println(response.code());
        if (response.code() >= 200 && response.code() <= 300) {
            String json = response.body().string();
            return objectMapper.readValue(json, DeleteReviewDto.class);
        }
        throw new RuntimeException("Something went wrong");
    }

    public List<CompanyDto> averageRating() throws IOException
    {
        Request request = new Request.Builder()
                .url(URL + "/company/averageRating")
                .get()
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();
        System.out.println(response.code());
        if (response.code() >= 200 && response.code() <= 300) {
            String json = response.body().string();
            return objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class,CompanyDto.class));
        }

        throw new RuntimeException("Something went wrong");
    }



}
