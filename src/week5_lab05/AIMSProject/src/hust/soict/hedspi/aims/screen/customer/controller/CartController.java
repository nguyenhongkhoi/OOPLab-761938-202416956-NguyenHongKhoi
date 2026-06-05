package hust.soict.hedspi.aims.screen.customer.controller;

import java.io.IOException;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.exception.PlayerException;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;
import hust.soict.hedspi.aims.store.Store;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CartController {

    @FXML private TableView<Media> tblMedia;
    @FXML private TableColumn<Media, Integer> colMediaId;
    @FXML private TableColumn<Media, String> colMediaTitle;
    @FXML private TableColumn<Media, String> colMediaCategory;
    @FXML private TableColumn<Media, Float> colMediaCost;
    @FXML private Button btnPlay;
    @FXML private Button btnRemove;
    @FXML private Label costLabel;
    @FXML private TextField tfFilter;
    @FXML private RadioButton radioBtnFilterId;
    @FXML private RadioButton radioBtnFilterTitle;
    @FXML private Button btnViewStore;
    @FXML private Button btnPlaceOrder;

    private final Store store;
    private final Cart cart;
    private FilteredList<Media> filteredList;

    public CartController(Store store, Cart cart) {
        this.store = store;
        this.cart = cart;
    }

    @FXML
    public void initialize() {
        colMediaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colMediaTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colMediaCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colMediaCost.setCellValueFactory(new PropertyValueFactory<>("cost"));

        filteredList = new FilteredList<>(cart.getItemsOrdered(), p -> true);
        SortedList<Media> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(tblMedia.comparatorProperty());
        tblMedia.setItems(sortedList);

        btnPlay.setVisible(false);
        btnRemove.setVisible(false);
        btnPlay.setManaged(false);
        btnRemove.setManaged(false);

        tblMedia.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Media>() {
            @Override
            public void changed(ObservableValue<? extends Media> observable, Media oldValue, Media newValue) {
                updateButtonBar(newValue);
            }
        });
        updateTotalCost();

        ToggleGroup filterToggleGroup = new ToggleGroup();
        radioBtnFilterId.setToggleGroup(filterToggleGroup);
        radioBtnFilterTitle.setToggleGroup(filterToggleGroup);
        radioBtnFilterId.setSelected(true);

        tfFilter.textProperty().addListener((observable, oldValue, newValue) -> showFilteredMedia());
        radioBtnFilterId.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                showFilteredMedia();
            }
        });
        radioBtnFilterTitle.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                showFilteredMedia();
            }
        });
    }

    void updateButtonBar(Media media) {
        if (media != null) {
            btnRemove.setVisible(true);
            btnRemove.setManaged(true);

            if (media instanceof Playable) {
                btnPlay.setVisible(true);
                btnPlay.setManaged(true);
            } else {
                btnPlay.setVisible(false);
                btnPlay.setManaged(false);
            }
        } else {
            btnRemove.setVisible(false);
            btnRemove.setManaged(false);
            btnPlay.setVisible(false);
            btnPlay.setManaged(false);
        }
    }

    @FXML
    void btnPlayPressed(ActionEvent event) {
        Media selectedMedia = tblMedia.getSelectionModel().getSelectedItem();
        if (selectedMedia instanceof Playable playable) {
            try {
                playable.play();
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Playing Media");
                alert.setHeaderText(null);
                alert.setContentText("Playing: " + selectedMedia.getTitle());
                alert.showAndWait();
            } catch (PlayerException e) {
                System.err.println("PlayerException caught in CartController: " + e.getMessage());
                System.err.println("Exception toString(): " + e.toString());
                e.printStackTrace();

                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Media Playback Error");
                alert.setHeaderText("Cannot play media.");
                alert.setContentText(e.getMessage() + "\n\nDetails: " + e);
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Cannot Play");
            alert.setHeaderText(null);
            alert.setContentText("The selected item is not playable.");
            alert.showAndWait();
        }
    }

    @FXML
    void btnRemovePressed(ActionEvent event) {
        Media selectedMedia = tblMedia.getSelectionModel().getSelectedItem();
        if (selectedMedia != null) {
            cart.removeMedia(selectedMedia);
            updateTotalCost();
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please select an item to remove.");
            alert.showAndWait();
        }
    }

    private void updateTotalCost() {
        costLabel.setText(String.format("%.2f $", cart.totalCost()));
    }

    void showFilteredMedia() {
        String filterText = tfFilter.getText();
        String normalized = filterText == null ? "" : filterText.toLowerCase();

        filteredList.setPredicate(media -> {
            if (normalized.isEmpty()) {
                return true;
            }

            if (radioBtnFilterId.isSelected()) {
                try {
                    int filterId = Integer.parseInt(normalized);
                    return media.getId() == filterId;
                } catch (NumberFormatException e) {
                    return false;
                }
            }

            return media.getTitle().toLowerCase().contains(normalized);
        });
    }

    @FXML
    void btnViewStorePressed(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Store.fxml"));
            loader.setControllerFactory(c -> new ViewStoreController(store, cart));
            Parent root = loader.load();

            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("AIMS - Store Screen");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Screen Navigation Error");
            alert.setHeaderText("Cannot load the store screen.");
            alert.setContentText("Details: " + e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    void btnPlaceOrderPressed(ActionEvent event) {
        if (cart.getItemsOrdered().isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Empty Cart");
            alert.setHeaderText(null);
            alert.setContentText("There is no item in the cart to place an order.");
            alert.showAndWait();
            return;
        }

        cart.clear();
        updateTotalCost();

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Order Placed");
        alert.setHeaderText(null);
        alert.setContentText("Your order has been placed successfully.");
        alert.showAndWait();
    }
}
