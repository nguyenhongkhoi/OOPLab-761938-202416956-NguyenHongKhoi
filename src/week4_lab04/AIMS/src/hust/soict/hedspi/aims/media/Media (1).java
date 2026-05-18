package hust.soict.hedspi.aims.media;

import java.util.Comparator;

public abstract class Media{
    private int id;
    private String title;
    private String category;
    private float cost;

    public static final Comparator<Media> COMPARE_BY_TITLE_COST = new MediaComparatorByTitleCost();
    public static final Comparator<Media> COMPARE_BY_COST_TITLE = new MediaComparatorByCostTitle();

    public Media() {
        super();
    }

    public Media(int id, String title, String category, float cost) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.cost = cost;
    }

    public int getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public String getCategory(){
        return category;
    }

    public float getCost(){
        return cost;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Media)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Media other = (Media) obj;
        if (this.title == null) {
            return other.title == null;
        }
        return this.title.equals(other.title);
    }

    @Override
    public int hashCode() {
        return title == null ? 0 : title.hashCode();
    }

    @Override
    public String toString() {
        return "Media - " + title + " - " + category + ": " + cost + " $";
    }
}