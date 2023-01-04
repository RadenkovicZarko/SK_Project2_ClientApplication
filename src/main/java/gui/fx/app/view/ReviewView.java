package gui.fx.app.view;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gui.fx.app.ClientApp;
import gui.fx.app.Main;
import gui.fx.app.model.User;
import gui.fx.app.restclient.ReservationCompanyServiceRest;
import gui.fx.app.restclient.dtoReservationService.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class ReviewView extends VBox {
    private HBox hBoxFilers;

    private Label lblAddReview;
    private Label lblDescription;
    private Label lblCompany;
    private Label lblRating;
    private TextField tfDescription;
    private ComboBox cbCompany;
    private ComboBox cbRating;
    private Button btnAddReview;
    private ObservableList<String> searchCompanyDtos;


    private HBox hBoxFilers2;
    private Label lblCity;
    private Label lblCompanyName;
    private TextField tfCity;
    private TextField tfCompanyName;

    private ObservableList<ReviewTableDto> reviewDtos;
    private TableView<ReviewTableDto> tableReviews;

    private ObservableList<CompanyDto> companyDtos;
    private TableView<CompanyDto> tableCompanyList;

    private HBox hBox3;
    private Button btnDeleteReview;
    private Button btnUpdateReview;
    private Button btnGoBack;


    private ReservationCompanyServiceRest reservationCompanyServiceRest=new ReservationCompanyServiceRest();
    private ObjectMapper objectMapper=new ObjectMapper();

    public ReviewView(){
        initViewElements();
        addElements();
        addListeners();
        initValues();
        this.setSpacing(15);
    }
    private User getUser(String token) throws JsonProcessingException {
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(token.split("[.]")[1]));
        User userMapper = objectMapper.readValue(payload, User.class);
        return userMapper;
    }

    private void initValues() {
        reservationCompanyServiceRest=new ReservationCompanyServiceRest();
        try {
            List<String> lista= new ArrayList<>();
            for(SearchCompanyDto searchCompanyDto:reservationCompanyServiceRest.getCompanies())
                lista.add(searchCompanyDto.getCompanyName());
            searchCompanyDtos.addAll(lista);

            ReviewSearchDto reviewSearchDto=new ReviewSearchDto();
            if(this.getTfCompanyName().getText()==null ||this.getTfCompanyName().getText().isEmpty())
                reviewSearchDto.setCompanyName("");
            if(this.getTfCity().getText()==null || this.getTfCity().getText().isEmpty())
                reviewSearchDto.setCity("");
            reviewDtos.clear();
            reviewDtos.addAll(reservationCompanyServiceRest.getReviews(reviewSearchDto));


            companyDtos.clear();
            companyDtos.addAll(reservationCompanyServiceRest.averageRating());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addListeners() {
        btnAddReview.setOnAction(e->{
            try {
                ReviewCreateDto reviewCreateDto=new ReviewCreateDto();
                reviewCreateDto.setDescription(this.getTfDescription().getText());
                reviewCreateDto.setRating(Long.parseLong(this.getCbRating().getSelectionModel().getSelectedItem().toString()));
                String companyName=this.cbCompany.getSelectionModel().getSelectedItem().toString();
                for(SearchCompanyDto searchCompanyDto:reservationCompanyServiceRest.getCompanies())
                    if(searchCompanyDto.getCompanyName().equalsIgnoreCase(companyName))
                    {
                        reviewCreateDto.setId_company(searchCompanyDto.getId());
                    }
                reviewCreateDto.setUser_id(this.getUser(ClientApp.getInstance().getToken()).getId());
                reservationCompanyServiceRest.addReview(reviewCreateDto);
                initValues();

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        });

        btnUpdateReview.setOnAction(e->{
            try {
                if(this.getTableReviews().getSelectionModel().getSelectedItem().getUser_id().equals(getUser(ClientApp.getInstance().getToken()).getId())) {
                    Scene sc = new Scene(new UpdateReviewView(this), 600, 200);
                    Main.secondStage.setScene(sc);
                    Main.secondStage.show();
                    Main.secondStage.setTitle("Change review");
                }
            } catch (JsonProcessingException jsonProcessingException) {
                jsonProcessingException.printStackTrace();
            }
        });
           btnDeleteReview.setOnAction(e->{
                try {
                    if(this.getTableReviews().getSelectionModel().getSelectedItem().getUser_id().equals(getUser(ClientApp.getInstance().getToken()).getId())) {
                        ReviewTableDto reviewTableDto = this.getTableReviews().getSelectionModel().getSelectedItem();
                        DeleteReviewDto deleteReviewDto = new DeleteReviewDto();
                        deleteReviewDto.setId(reviewTableDto.getId());
                        deleteReviewDto.setDescription(reviewTableDto.getDescription());
                        deleteReviewDto.setUser_id(reviewTableDto.getUser_id());
                        reservationCompanyServiceRest.deleteReview(deleteReviewDto);
                        initValues();
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });
        btnGoBack.setOnAction(e->{
            try {
                User user = getUser(ClientApp.getInstance().getToken());
                if(user.getRole().equalsIgnoreCase("ROLE_CLIENT"))
                {
                    Scene sc=new Scene(new ClientChangeDataView(user),500,500);
                    Main.mainStage.setScene(sc);
                    Main.mainStage.show();
                }
                if(user.getRole().equalsIgnoreCase("ROLE_MANAGER"))
                {
                    Scene sc=new Scene(new ManagerChangeDataView(user),700,700);
                    Main.mainStage.setScene(sc);
                    Main.mainStage.show();
                }
                if(user.getRole().equalsIgnoreCase("ROLE_ADMIN"))
                {
                    Scene sc=new Scene(new AdminChangeDataView(user),700,700);
                    Main.mainStage.setScene(sc);
                    Main.mainStage.show();
                }
            } catch (JsonProcessingException jsonProcessingException) {
                jsonProcessingException.printStackTrace();
            }
        });

    }


    private void addElements() {
        String token= ClientApp.getInstance().getToken();
        try {
            User user = getUser(token);
            this.hBoxFilers.getChildren().addAll(lblCompany,cbCompany,lblRating,cbRating,lblDescription,tfDescription,btnAddReview);
            this.getChildren().addAll(lblAddReview,hBoxFilers);
            this.hBoxFilers2.getChildren().addAll(lblCity,tfCity,lblCompanyName,tfCompanyName);
            this.hBox3.getChildren().addAll(btnUpdateReview, btnDeleteReview);
            this.getChildren().addAll(hBoxFilers2,tableReviews,hBox3);
            this.getChildren().add(tableCompanyList);
            this.getChildren().add(btnGoBack);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void initViewElements() {
        hBoxFilers = new HBox(10);
        lblAddReview=new Label("Add review:");
        lblCompany=new Label("Company");
        lblDescription=new Label("Description");
        lblRating=new Label("Rating");
        tfDescription=new TextField();
        searchCompanyDtos=FXCollections.observableArrayList();
        cbCompany=new ComboBox(searchCompanyDtos);
        ObservableList<Integer> list=FXCollections.observableArrayList(1,2,3,4,5);
        cbRating=new ComboBox(list);
        btnAddReview=new Button("Add review");


        hBoxFilers2=new HBox(10);
        lblCity=new Label("City");
        lblCompanyName=new Label("Company name");
        tfCity=new TextField();
        tfCompanyName=new TextField();

        reviewDtos = FXCollections.observableArrayList();
        tableReviews = new TableView<>(reviewDtos);

        TableColumn<ReviewTableDto, String> col1 = new TableColumn<>("Company");
        col1.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        TableColumn<ReviewTableDto, String> col2 = new TableColumn<>("Description");
        col2.setCellValueFactory(new PropertyValueFactory<>("description"));
        TableColumn<ReviewTableDto, Long> col3 = new TableColumn<>("Rating");
        col3.setCellValueFactory(new PropertyValueFactory<>("Rating"));
        TableColumn<ReviewTableDto, Long> col4 = new TableColumn<>("UserId");
        col4.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        tableReviews.getColumns().add(col1);
        tableReviews.getColumns().add(col2);
        tableReviews.getColumns().add(col3);
        tableReviews.getColumns().add(col4);

        hBox3=new HBox(10);
        btnDeleteReview =new Button("Delete review");
        btnUpdateReview =new Button("Update review");


        companyDtos = FXCollections.observableArrayList();
        tableCompanyList = new TableView<>(companyDtos);

        TableColumn<CompanyDto, String> col11 = new TableColumn<>("Company name");
        col11.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        TableColumn<CompanyDto, Long> col22 = new TableColumn<>("Rating");
        col22.setCellValueFactory(new PropertyValueFactory<>("rating"));

        tableCompanyList.getColumns().add(col11);
        tableCompanyList.getColumns().add(col22);

        btnGoBack=new Button("Go back");


    }

    public TextField getTfDescription() {
        return tfDescription;
    }

    public ComboBox getCbCompany() {
        return cbCompany;
    }

    public ComboBox getCbRating() {
        return cbRating;
    }

    public TextField getTfCity() {
        return tfCity;
    }

    public void setTfCity(TextField tfCity) {
        this.tfCity = tfCity;
    }

    public TextField getTfCompanyName() {
        return tfCompanyName;
    }

    public void setTfCompanyName(TextField tfCompanyName) {
        this.tfCompanyName = tfCompanyName;
    }

    public ObservableList<ReviewTableDto> getReviewDtos() {
        return reviewDtos;
    }

    public void setReviewDtos(ObservableList<ReviewTableDto> reviewDtos) {
        this.reviewDtos = reviewDtos;
    }

    public TableView<ReviewTableDto> getTableReviews() {
        return tableReviews;
    }

    public void setTableReviews(TableView<ReviewTableDto> tableReviews) {
        this.tableReviews = tableReviews;
    }
}
