package hust.soict.hedspi.aims.store;


import hust.soict.hedspi.aims.media.Media;
import java.util.ArrayList;

public class Store{
    public static final int MAX_NUMBER_IN_STORE = 100;
    private ArrayList<Media> itemsInStore = new ArrayList<>();

    public void addMedia(Media media){
        if(itemsInStore.size() < MAX_NUMBER_IN_STORE){
            itemsInStore.add(media);
            System.out.println("Media " + media.getTitle() + " has been added to the store.");
        }
        else{
            System.out.println("The store is full, cannot add more.");
        }
    }

    public void removeMedia(Media media){
        if (itemsInStore.remove(media)) {
            System.out.println("Media " + media.getTitle() + " has been removed from the store.");
        } else {
            System.out.println("Media " + media.getTitle() + " is not available in store.");
        }
    }

    public void print() {
        if (itemsInStore.isEmpty()) {
            System.out.println("Store is empty.");
            return;
        }
        System.out.println("***********************STORE***********************");
        System.out.println("Items in store:");
        for (int i = 0; i < itemsInStore.size(); i++) {
            System.out.println((i + 1) + ". " + itemsInStore.get(i).toString());
        }
        System.out.println("*************************************************");
    }

    public Media searchByTitle(String title) {
        for (Media media : itemsInStore) {
            if (media.getTitle().equalsIgnoreCase(title)) {
                return media;
            }
        }
        return null;
    }

    public ArrayList<Media> getItemsInStore() {
        return itemsInStore;
    }

}