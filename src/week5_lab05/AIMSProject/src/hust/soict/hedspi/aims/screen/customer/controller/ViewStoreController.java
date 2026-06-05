package hust.soict.hedspi.aims.screen.customer.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.store.Store;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ViewStoreController {

    private final Store store;
    private final Cart cart;

    public ViewStoreController(Store store, Cart cart) {
        this.store = store;
        this.cart = cart;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private GridPane gridPane;

    @FXML
    void btnViewCartPressed(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Cart.fxml"));
            loader.setControllerFactory(c -> new CartController(store, cart));
            Parent root = loader.load();

            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Cart Screen");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load Cart.fxml: " + e.getMessage());
        }
    }

    @FXML
    public void initialize() {
        final String itemFxmlFilePath = "/hust/soict/hedspi/aims/screen/customer/view/Item.fxml";
        int column = 0;
        int row = 1;

        if (store == null || store.getItemsInStore() == null) {
            System.err.println("Store or items in store are not initialized. Cannot display items.");
            return;
        }

        for (int i = 0; i < store.getItemsInStore().size(); i++) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(itemFxmlFilePath));
                ItemController itemController = new ItemController(cart);
                fxmlLoader.setController(itemController);

                Parent anchorPane = fxmlLoader.load();
                itemController.setData(store.getItemsInStore().get(i));

                if (column == 3) {
                    column = 0;
                    row++;
                }

                gridPane.add(anchorPane, column++, row);
                GridPane.setMargin(anchorPane, new Insets(20, 10, 10, 10));
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Error loading item FXML or setting data: " + e.getMessage());
            }
        }
    }
}
