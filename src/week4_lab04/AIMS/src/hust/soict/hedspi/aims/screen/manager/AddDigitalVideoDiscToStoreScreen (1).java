package hust.soict.hedspi.aims.screen.manager;

import hust.soict.hedspi.aims.store.Store;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;

import javax.swing.*;
import java.awt.*;

public class AddDigitalVideoDiscToStoreScreen extends AddItemToStoreScreen {
    public AddDigitalVideoDiscToStoreScreen(Store store, StoreManagerScreen parentScreen) {
        super(store, parentScreen);
        setTitle("Add DVD to Store");
    }

    @Override
    JPanel createCenter() {
        JPanel center = new JPanel(new GridLayout(6, 2, 10, 10));

        JLabel titleLabel = new JLabel("Title:");
        JTextField titleField = new JTextField(20);
        JLabel categoryLabel = new JLabel("Category:");
        JTextField categoryField = new JTextField(20);
        JLabel costLabel = new JLabel("Cost:");
        JTextField costField = new JTextField(10);
        JLabel directorLabel = new JLabel("Director:");
        JTextField directorField = new JTextField(20);
        JLabel lengthLabel = new JLabel("Length:");
        JTextField lengthField = new JTextField(10);

        JButton addButton = new JButton("Add DVD");
        addButton.addActionListener(e -> {
            try {
                String title = titleField.getText();
                String category = categoryField.getText();
                float cost = Float.parseFloat(costField.getText());
                String director = directorField.getText();
                int length = Integer.parseInt(lengthField.getText());

                DigitalVideoDisc dvd = new DigitalVideoDisc(title, category, director, length, cost);
                store.addMedia(dvd);
                JOptionPane.showMessageDialog(this, "DVD added successfully!");
                setVisible(false);
                parentScreen.setVisible(true);
                parentScreen.repaint();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid cost or length. Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        center.add(titleLabel);
        center.add(titleField);
        center.add(categoryLabel);
        center.add(categoryField);
        center.add(costLabel);
        center.add(costField);
        center.add(directorLabel);
        center.add(directorField);
        center.add(lengthLabel);
        center.add(lengthField);
        center.add(new JLabel()); // Ô trống
        center.add(addButton);

        return center;
    }
}