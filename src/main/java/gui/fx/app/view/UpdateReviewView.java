package gui.fx.app.view;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gui.fx.app.ClientApp;
import gui.fx.app.Main;
import gui.fx.app.model.User;
import gui.fx.app.restclient.ReservationCompanyServiceRest;
import gui.fx.app.restclient.dtoReservationService.ChangeReviewDto;
import gui.fx.app.restclient.dtoReservationService.ReviewDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Base64;

public class UpdateReviewView extends VBox {

    private HBox hBox;
    private Label lblDescription;
    private Label lblRating;

    private TextField tfDescription;
    private ComboBox comboBox;

    private Button btnChangeReview;

    private ReviewView reviewView;

    public UpdateReviewView(ReviewView reviewView){
        this.reviewView=reviewView;
        initViewElements();
        addElements();
        addListeners();
        this.reviewView=reviewView;
        this.setSpacing(15);
    }



    private void addListeners() {
        btnChangeReview.setOnAction(e->{
            try {
                ChangeReviewDto changeReviewDto=new ChangeReviewDto();
                changeReviewDto.setId(this.reviewView.getTableReviews().getSelectionModel().getSelectedItem().getId());

                changeReviewDto.setDescription(this.getTfDescription().getText());
                changeReviewDto.setRating(Long.parseLong(this.getComboBox().getSelectionModel().getSelectedItem().toString()));
                ReservationCompanyServiceRest reservationCompanyServiceRest=new ReservationCompanyServiceRest();
                ReviewDto reviewDto=reservationCompanyServiceRest.changeReview(changeReviewDto);

                Main.secondStage.close();
                System.out.println(reviewDto);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
    }


    private void addElements() {
        String token= ClientApp.getInstance().getToken();
        try {
            this.hBox.getChildren().addAll(lblRating,comboBox,lblDescription,tfDescription, btnChangeReview);
            this.getChildren().addAll(hBox);
            this.setSpacing(10);
            this.setAlignment(Pos.CENTER);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void initViewElements() {
        hBox = new HBox(10);

        lblDescription=new Label("Description");
        lblRating =new Label("Rating");
        tfDescription=new TextField();
        ObservableList<Integer> list=FXCollections.observableArrayList(1,2,3,4,5);
        comboBox=new ComboBox(list);
        btnChangeReview =new Button("Change review");

    }

    public TextField getTfDescription() {
        return tfDescription;
    }

    public void setTfDescription(TextField tfDescription) {
        this.tfDescription = tfDescription;
    }

    public ComboBox getComboBox() {
        return comboBox;
    }

    public void setComboBox(ComboBox comboBox) {
        this.comboBox = comboBox;
    }
}
