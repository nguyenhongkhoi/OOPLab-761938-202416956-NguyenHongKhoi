package hust.soict.hedspi.aims.media;

import java.util.Objects;

public abstract class Media implements Comparable<Media> {
    public static final MediaComparatorByTitleCost COMPARE_BY_TITLE_COST = new MediaComparatorByTitleCost();
    public static final MediaComparatorByCostTitle COMPARE_BY_COST_TITLE = new MediaComparatorByCostTitle();

    protected int id;
    protected String title;
    protected String category;
    protected float cost;

    public Media(int id, String title, String category, float cost) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.cost = cost;
    }

    public Media(String title) {
        this.title = title;
    }

    public Media(String title, String category, float cost) {
        this.title = title;
        this.category = category;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Media other)) {
            return false;
        }
        return Objects.equals(this.title, other.title)
                && Float.compare(this.cost, other.cost) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, cost);
    }

    @Override
    public int compareTo(Media other) {
        if (other == null) {
            throw new NullPointerException("Cannot compare Media with a null object.");
        }

        int titleComparison = this.title.compareTo(other.title);
        if (titleComparison != 0) {
            return titleComparison;
        }
        return Float.compare(this.cost, other.cost);
    }
}