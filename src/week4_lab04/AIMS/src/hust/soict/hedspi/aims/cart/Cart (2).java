package hust.soict.hedspi.aims.cart;

import hust.soict.hedspi.aims.media.*;
import java.util.ArrayList;
import java.util.Collections;

public class Cart {
    public static final int MAX_NUMBER_ORDERED = 20;
    private ArrayList<Media> itemsOrdered = new ArrayList<>();

    public void addMedia(Media... mediaItems) {
        if (itemsOrdered.size() + mediaItems.length <= MAX_NUMBER_ORDERED) {
            for (int i = 0; i < mediaItems.length; i++) {
                itemsOrdered.add(mediaItems[i]);
            }
            System.out.println(mediaItems.length + " media item(s) have been added.");
        }
        else {
            System.out.println("The cart is full.");
        }
    }

    public void removeMedia(Media media) {
        if (itemsOrdered.remove(media)) {
            System.out.println("The media has been removed.");
        }
        else {
            System.out.println("The media was not found in the cart.");
        }
    }

    public float totalCost() {
        float sum = 0;
        for (Media media : itemsOrdered) {
            sum += media.getCost();
        }
        return sum;
    }

    public void display() {
        for (int i = 0; i < itemsOrdered.size(); i++) {
            Media media = itemsOrdered.get(i);
            String type = media instanceof DigitalVideoDisc ? "DVD" :
                    (media instanceof CompactDisc ? "CD" : "Book");
            System.out.printf("%-5d%-5s%-20s%-20s%-20s%10d%10.2f\n",
                    i + 1,
                    type,
                    media.getTitle(),
                    media.getCategory() != null ? media.getCategory() : "N/A",
                    media instanceof CompactDisc ? ((CompactDisc) media).getArtist() != null ? ((CompactDisc) media).getArtist() : "N/A" : "N/A",
                    media instanceof DigitalVideoDisc ? ((DigitalVideoDisc) media).getLength() :
                            media instanceof CompactDisc ? ((CompactDisc) media).getLength() : 0,
                    media.getCost()
            );
        }
        System.out.printf("%-5s%-5s%-20s%-20s%-20s%10s%10.2f\n", "", "", "Total cost:", "", "", "", this.totalCost());
    }

    public void print() {
        System.out.println("***********************CART***********************\nOrdered Items:");
        for (int i = 0; i < itemsOrdered.size(); i++) {
            Media media = itemsOrdered.get(i);
            String type = media instanceof DigitalVideoDisc ? "DVD" :
                    (media instanceof CompactDisc ? "CD" : "Book");
            System.out.println((i + 1) + ". " + type + " - " + media.getTitle() + " - " +
                    (media.getCategory() != null ? media.getCategory() : "N/A") + " - " +
                    (media instanceof DigitalVideoDisc ? (((DigitalVideoDisc) media).getDirector() != null ? ((DigitalVideoDisc) media).getDirector() : "N/A") :
                            media instanceof CompactDisc ? (((CompactDisc) media).getArtist() != null ? ((CompactDisc) media).getArtist() : "N/A") : "N/A") + " - " +
                    (media instanceof DigitalVideoDisc ? ((DigitalVideoDisc) media).getLength() :
                            media instanceof CompactDisc ? ((CompactDisc) media).getLength() : 0) + ": " +
                    media.getCost() + " $");
        }
        System.out.println("Total cost: " + this.totalCost());
        System.out.println("***************************************************");
    }

    public Media searchById(int id) {
        for (Media media : itemsOrdered) {
            if (media.getId() == id) {
                return media;
            }
        }
        return null;
    }

    public Media searchByTitle(String title) {
        for (Media media : itemsOrdered) {
            if (media.getTitle().equalsIgnoreCase(title)) {
                return media;
            }
        }
        return null;
    }

    public void sortByTitleCost() {
        Collections.sort(itemsOrdered, Media.COMPARE_BY_TITLE_COST);
    }

    public void sortByCostTitle() {
        Collections.sort(itemsOrdered, Media.COMPARE_BY_COST_TITLE);
    }

    public ArrayList<Media> getItemsOrdered() {
        return itemsOrdered;
    }
}