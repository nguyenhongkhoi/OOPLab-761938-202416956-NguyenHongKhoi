package hust.soict.dsai.aims.store;

import hust.soict.dsai.aims.media.Media;
import java.util.ArrayList;

public class Store {
    private ArrayList<Media> itemsInStore = new ArrayList<Media>();

    public void addMedia(Media media) {
        itemsInStore.add(media);
        System.out.println("Item added to store.");
    }

    public void removeMedia(Media media) {
        itemsInStore.remove(media);
    }

    public void print() {
        System.out.println("--- STORE ITEMS ---");
        for (Media m : itemsInStore) {
            System.out.println(m.toString());
        }
    }

    public Media searchByTitle(String title) {
        for (Media m : itemsInStore) {
            if (m.getTitle().equalsIgnoreCase(title))
                return m;
        }
        return null;
    }
}