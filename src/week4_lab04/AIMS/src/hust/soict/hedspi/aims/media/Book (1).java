package hust.soict.hedspi.aims.media;

import hust.soict.hedspi.aims.media.Media;

import java.util.ArrayList;
import java.util.List;

public class Book extends Media {
    private List<String> authors = new ArrayList<String>();

    public Book(String title){
        super(0, title, null, 0);
    }

    public Book(String title, String category, float cost){
        super(0, title, category, cost);
    }

    public Book(int id, String title, String category, float cost){
        super(id, title, category, cost);
    }

    public void addAuthor(String authorName){
        if(!authors.contains(authorName)){
            authors.add(authorName);
            System.out.println("Author " + authorName + " has been added to the list.");
        }
        else{
            System.out.println("Author " + authorName + " is already in the list.");
        }
    }

    public void removeAuthor(String authorName){
        if(authors.contains(authorName)){
            authors.remove(authorName);
            System.out.println("Author " + authorName + " has been removed from the list.");
        }
        else{
            System.out.println("Author " + authorName + " is not in the the list.");
        }
    }

    @Override
    public String toString() {
        return "Book - " + getTitle() + " - " + getCategory() + " - " + authors + ": " + getCost() + " $";
    }
}