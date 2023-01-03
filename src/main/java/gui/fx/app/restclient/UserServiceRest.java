package gui.fx.app.restclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gui.fx.app.ClientApp;
import gui.fx.app.model.User;
import gui.fx.app.restclient.dtoReservationService.SearchCompanyDto;
import gui.fx.app.restclient.dtoUserService.*;
import okhttp3.*;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

public class UserServiceRest {
    public static final String URL = "http://localhost:8084/userservice/api";
    /// http://localhost:8080/api


    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();
    ObjectMapper objectMapper = new ObjectMapper();


    private User getUser(String token) throws JsonProcessingException {
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(token.split("[.]")[1]));
        User userMapper = objectMapper.readValue(payload, User.class);
        return userMapper;
    }


    public String login(String username, String password) throws IOException {

        TokenRequestDto tokenRequestDto = new TokenRequestDto(username, password);

        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(tokenRequestDto));

        Request request = new Request.Builder()
                .url(URL + "/user/login")
                .post(body)
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();
        System.out.println(response.code());
        if (response.code() >= 200 && response.code() <= 300) {
            String json = response.body().string();
            TokenResponseDto dto = objectMapper.readValue(json, TokenResponseDto.class);

            return dto.getToken();
        }

        throw new RuntimeException("Invalid username or password");
    }

    public ClientDto registerClient(ClientCreateDto clientCreateDto) throws IOException {
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(clientCreateDto));

        Request request = new Request.Builder()
                .url(URL + "/client")
                .post(body)
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();
        System.out.println(response.code());
        if (response.code() >= 200 && response.code() <= 300) {
            String json = response.body().string();
            return objectMapper.readValue(json, ClientDto.class);

        }

        throw new RuntimeException("Something went wrong");
    }

    public ManagerDto registerManager(ManagerCreateDto managerCreateDto) throws IOException{
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(managerCreateDto));

        Request request = new Request.Builder()
                .url(URL + "/manager")
                .post(body)
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();
        System.out.println(response.code());
        if (response.code() >= 200 && response.code() <= 300) {
            String json = response.body().string();
            return objectMapper.readValue(json, ManagerDto.class);

        }

        throw new RuntimeException("Something went wrong");
    }


    public FullClientDto findByIdToUpdateClient(SearchUserDto searchUserDto)throws IOException
    {
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(searchUserDto));

        Request request = new Request.Builder()
                .url(URL + "/client/findByIdToChange")
                .post(body)
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();
        System.out.println(response.code());
        if (response.code() >= 200 && response.code() <= 300) {

            String json = response.body().string();

            return objectMapper.readValue(json, FullClientDto.class);

        }

        throw new RuntimeException("Something went wrong");
    }

    public ClientDto updateClient(ClientChangeParametersDto clientChangeParametersDto)throws IOException
    {
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(clientChangeParametersDto));

        Request request = new Request.Builder()
                .url(URL + "/client/change")
                .post(body)
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();
        System.out.println(response.code());
        if (response.code() >= 200 && response.code() <= 300) {
            String json = response.body().string();
            return objectMapper.readValue(json, ClientDto.class);

        }
        throw new RuntimeException("Something went wrong");
    }


    public FullManagerDto findByIdToUpdateManager(SearchUserDto searchUserDto)throws IOException
    {
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(searchUserDto));

        Request request = new Request.Builder()
                .url(URL + "/manager/findByIdToChange")
                .post(body)
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();
        System.out.println(response.code());
        if (response.code() >= 200 && response.code() <= 300) {

            String json = response.body().string();

            return objectMapper.readValue(json, FullManagerDto.class);

        }

        throw new RuntimeException("Something went wrong");
    }

    public ManagerDto updateManager(ManagerChangeParametersDto managerChangeParametersDto)throws IOException
    {
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(managerChangeParametersDto));

        Request request = new Request.Builder()
                .url(URL + "/manager/change")
                .post(body)
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();
        System.out.println(response.code());
        if (response.code() >= 200 && response.code() <= 300) {
            String json = response.body().string();
            return objectMapper.readValue(json, ManagerDto.class);

        }
        throw new RuntimeException("Something went wrong");
    }

    public FullAdminDto findByIdToUpdateAdmin(SearchUserDto searchUserDto)throws IOException
    {
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(searchUserDto));

        Request request = new Request.Builder()
                .url(URL + "/admin/findByIdToChange")
                .post(body)
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();
        System.out.println(response.code());
        if (response.code() >= 200 && response.code() <= 300) {

            String json = response.body().string();

            return objectMapper.readValue(json, FullAdminDto.class);

        }

        throw new RuntimeException("Something went wrong");
    }

    public AdminDto updateAdmin(AdminChangeParametersDto adminChangeParametersDto)throws IOException
    {
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(adminChangeParametersDto));

        Request request = new Request.Builder()
                .url(URL + "/admin/change")
                .post(body)
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();
        System.out.println(response.code());
        if (response.code() >= 200 && response.code() <= 300) {
            String json = response.body().string();
            return objectMapper.readValue(json, AdminDto.class);

        }
        throw new RuntimeException("Something went wrong");
    }


    public UserDto updatePassword(UserChangePasswordDto userChangePasswordDto)throws IOException
    {
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(userChangePasswordDto));

        Request request = new Request.Builder()
                .url(URL + "/user/updatePassword")
                .post(body)
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();
        System.out.println(response.code());
        if (response.code() >= 200 && response.code() <= 300) {
            String json = response.body().string();
            return objectMapper.readValue(json, UserDto.class);

        }
        throw new RuntimeException("Something went wrong");
    }

    public List<ClientAndManagerDto> getAllClientsAndManagers() throws IOException
    {
        //objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Request request = new Request.Builder()
                .url(URL + "/user/findAllClientsAndManagers")
                .get()
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();

        if (response.code() >= 200 && response.code() <= 300) {
            String json = response.body().string();
            System.out.println(json);
            return objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, ClientAndManagerDto.class));
        }
        throw new RuntimeException();
    }

    public UserDto updateForbiden(ClientForbidenDto clientForbidenDto) throws IOException {
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(clientForbidenDto));
        String token= ClientApp.getInstance().getToken();
        Request request = new Request.Builder()
                .url(URL + "/admin/forbidden")
                .post(body)
                .addHeader("Authorization","Bearer "+token)
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();
        System.out.println(response.code());
        if (response.code() >= 200 && response.code() <= 300) {
            String json = response.body().string();
            return objectMapper.readValue(json, UserDto.class);

        }
        throw new RuntimeException("Something went wrong");
    }

    public RankDto addRank(RankCreateDto rankCreateDto) throws IOException {
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(rankCreateDto));
        String token= ClientApp.getInstance().getToken();
        Request request = new Request.Builder()
                .url(URL + "/admin/rank")
                .post(body)
                .addHeader("Authorization","Bearer "+token)
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();
        System.out.println(response.code());
        if (response.code() >= 200 && response.code() <= 300) {
            String json = response.body().string();
            return objectMapper.readValue(json,RankDto.class);

        }
        throw new RuntimeException("Something went wrong");
    }

}
