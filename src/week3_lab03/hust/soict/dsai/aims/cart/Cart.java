package hust.soict.dsai.aims.cart;

import hust.soict.dsai.aims.media.Media;
import java.util.ArrayList;

public class Cart {
    private ArrayList<Media> itemsOrdered = new ArrayList<Media>();

    public void addMedia(Media media) {
        itemsOrdered.add(media);
        System.out.println("Added: " + media.getTitle());
    }

    public void removeMedia(Media media) {
        if (itemsOrdered.remove(media)) {
            System.out.println("Removed: " + media.getTitle());
        } else {
            System.out.println("Media not found!");
        }
    }

    public float totalCost() {
        float total = 0;
        for (Media m : itemsOrdered)
            total += m.getCost();
        return total;
    }

    public void print() {
        System.out.println("***********************CART***********************");
        System.out.println("Ordered Items:");
        for (Media m : itemsOrdered) {
            System.out.println(m.getId() + ". DVD - " + m.getTitle() + " - " + m.getCost() + "$");
        }
        System.out.println("Total cost: " + totalCost() + "$");
        System.out.println("***************************************************");
    }
}