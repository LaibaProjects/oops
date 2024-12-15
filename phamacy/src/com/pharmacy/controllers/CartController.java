
package com.pharmacy.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller for managing cart-related actions.
 */
public class CartController {

    private List<String> cartItems = new ArrayList<>();

    @FXML
    private ListView<String> medicinesListView;

    @FXML
    private ListView<String> cartListView;

    @FXML
    private Button addToCartButton;

    @FXML
    private Button viewCartButton;

    @FXML
    private Button checkoutButton;

    @FXML
    private Label totalItemsLabel;

    /**
     * Initializes the list of medicines available for selection.
     */
    @FXML
    public void initialize() {
        // Sample data for medicines
        medicinesListView.getItems().addAll(
            "Paracetamol - $10", 
            "Aspirin - $15", 
            "Ibuprofen - $12", 
            "Antibiotic - $25", 
            "Cough Syrup - $8"
        );
    }

    /**
     * Adds the selected item from the medicines list to the cart.
     */
    @FXML
    public void addToCart() {
        String selectedMedicine = medicinesListView.getSelectionModel().getSelectedItem();
        if (selectedMedicine != null) {
            cartItems.add(selectedMedicine);
            cartListView.getItems().add(selectedMedicine);
            updateTotalItemsLabel();
            showPopup("Added to cart", "You have added " + selectedMedicine + " to the cart.");
            System.out.println("Added to cart: " + selectedMedicine);
        } else {
            showPopup("No selection", "Please select a medicine to add to the cart.");
        }
    }

    /**
     * Displays the current items in the cart.
     */
    @FXML
    public void viewCart() {
        if (cartItems.isEmpty()) {
            showPopup("Cart is empty", "No items in the cart.");
        } else {
            showPopup("Items in Cart", "Items in your cart: \n" + String.join("\n", cartItems));
        }
    }

    /**
     * Proceeds to the checkout process.
     */
    @FXML
    public void checkout() {
        if (!cartItems.isEmpty()) {
            String receipt = "You have purchased the following items:\n" + String.join("\n", cartItems);
            showPopup("Checkout Complete", receipt);
            cartItems.clear();
            cartListView.getItems().clear();
            updateTotalItemsLabel();
        } else {
            showPopup("Cart is empty", "No items in the cart to checkout.");
        }
    }

    /**
     * Updates the label displaying the total number of items in the cart.
     */
    private void updateTotalItemsLabel() {
        totalItemsLabel.setText("Total Items: " + cartItems.size());
    }

    /**
     * Displays a pop-up alert with a custom message.
     *
     * @param title   The title of the alert.
     * @param message The message to display in the alert.
     */
    private void showPopup(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

