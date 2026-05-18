package hust.soict.hedspi.aims.screen.manager;

import hust.soict.hedspi.aims.store.Store;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class AddItemToStoreScreen extends JFrame {
    protected Store store;
    protected StoreManagerScreen parentScreen;

    public AddItemToStoreScreen(Store store, StoreManagerScreen parentScreen) {
        this.store = store;
        this.parentScreen = parentScreen;

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(createNorth(), BorderLayout.NORTH);
        cp.add(createCenter(), BorderLayout.CENTER);

        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    JPanel createNorth() {
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
        north.add(createMenuBar());
        north.add(createHeader());
        return north;
    }

    JMenuBar createMenuBar() {
        JMenu menu = new JMenu("Options");

        JMenuItem viewStoreItem = new JMenuItem("View store");
        viewStoreItem.addActionListener(e -> {
            setVisible(false); // Ẩn màn hình hiện tại
            parentScreen.setVisible(true); // Hiển thị lại màn hình chính
            parentScreen.repaint();
        });
        menu.add(viewStoreItem);

        JMenu smUpdateStore = new JMenu("Update Store");

        JMenuItem addBookItem = new JMenuItem("Add Book");
        addBookItem.addActionListener(e -> {
            setVisible(false);
            new AddBookToStoreScreen(store, parentScreen);
        });
        smUpdateStore.add(addBookItem);

        JMenuItem addCDItem = new JMenuItem("Add CD");
        addCDItem.addActionListener(e -> {
            setVisible(false);
            new AddCompactDiscToStoreScreen(store, parentScreen);
        });
        smUpdateStore.add(addCDItem);

        JMenuItem addDVDItem = new JMenuItem("Add DVD");
        addDVDItem.addActionListener(e -> {
            setVisible(false);
            new AddDigitalVideoDiscToStoreScreen(store, parentScreen);
        });
        smUpdateStore.add(addDVDItem);

        menu.add(smUpdateStore);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuBar.add(menu);

        return menuBar;
    }

    JPanel createHeader() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));

        JLabel title = new JLabel("AIMS");
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));
        title.setForeground(Color.CYAN);

        header.add(Box.createRigidArea(new Dimension(10, 10)));
        header.add(title);
        header.add(Box.createHorizontalGlue());
        header.add(Box.createRigidArea(new Dimension(10, 10)));

        return header;
    }

    abstract JPanel createCenter();
}