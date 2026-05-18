package hust.soict.hedspi.aims.screen.manager;

import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;

import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class MediaStore extends JPanel {
    private Media media;

    public MediaStore(Media media) {
        this.media = media;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel(media != null && media.getTitle() != null ? media.getTitle() : "N/A");
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 15));
        title.setAlignmentX(CENTER_ALIGNMENT);

        JLabel cost = new JLabel(media != null ? media.getCost() + " $" : "N/A");
        cost.setAlignmentX(CENTER_ALIGNMENT);

        JPanel container = new JPanel();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));

        if (media instanceof Playable) {
            JButton playButton = new JButton("Play");
            playButton.addActionListener(e -> playMedia());
            container.add(playButton);
        }

        this.add(Box.createVerticalGlue());
        this.add(title);
        this.add(cost);
        this.add(Box.createVerticalGlue());
        this.add(container);

        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    private void playMedia() {
        if (media instanceof Playable) {
            JDialog playDialog = new JDialog();
            playDialog.setTitle("Playing Media: " + media.getTitle());
            playDialog.setSize(300, 150);
            playDialog.setLocationRelativeTo(this);
            playDialog.setLayout(new BorderLayout());

            JTextArea outputArea = new JTextArea(5, 20);
            outputArea.setEditable(false);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos);
            PrintStream old = System.out;
            System.setOut(ps);

            ((Playable) media).play();
            System.out.flush();
            System.setOut(old);

            outputArea.setText(baos.toString());
            playDialog.add(new JScrollPane(outputArea), BorderLayout.CENTER);

            JButton closeButton = new JButton("Close");
            closeButton.addActionListener(e -> playDialog.dispose());
            playDialog.add(closeButton, BorderLayout.SOUTH);

            playDialog.setVisible(true);
        }
    }
}