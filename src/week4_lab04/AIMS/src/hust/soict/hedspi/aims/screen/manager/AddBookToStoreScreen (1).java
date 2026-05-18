package hust.soict.hedspi.aims.screen.manager;

import hust.soict.hedspi.aims.store.Store;
import hust.soict.hedspi.aims.media.Book;

import javax.swing.*;
import java.awt.*;

public class AddBookToStoreScreen extends AddItemToStoreScreen {
    public AddBookToStoreScreen(Store store, StoreManagerScreen parentScreen) {
        super(store, parentScreen);
        setTitle("Add Book to Store");
    }

    @Override
    JPanel createCenter() {
        JPanel center = new JPanel(new GridLayout(5, 2, 10, 10));

        JLabel titleLabel = new JLabel("Title:");
        JTextField titleField = new JTextField(20);
        JLabel categoryLabel = new JLabel("Category:");
        JTextField categoryField = new JTextField(20);
        JLabel costLabel = new JLabel("Cost:");
        JTextField costField = new JTextField(10);
        JLabel authorsLabel = new JLabel("Authors (comma-separated):");
        JTextField authorsField = new JTextField(20);

        JButton addButton = new JButton("Add Book");
        addButton.addActionListener(e -> {
            try {
                String title = titleField.getText();
                String category = categoryField.getText();
                float cost = Float.parseFloat(costField.getText());
                String[] authors = authorsField.getText().split(",");

                Book book = new Book(title, category, cost);
                for (String author : authors) {
                    book.addAuthor(author.trim());
                }
                store.addMedia(book);
                JOptionPane.showMessageDialog(this, "Book added successfully!");
                setVisible(false);
                parentScreen.setVisible(true);
                parentScreen.repaint();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid cost. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        center.add(titleLabel);
        center.add(titleField);
        center.add(categoryLabel);
        center.add(categoryField);
        center.add(costLabel);
        center.add(costField);
        center.add(authorsLabel);
        center.add(authorsField);
        center.add(new JLabel()); // Ô trống
        center.add(addButton);

        return center;
    }
}