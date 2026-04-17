package hust.soict.dsai.aims.media;

public abstract class Media {
    private int id;
    private String title;
    private String category;
    private float cost;
    private static int nbMedia = 0;

    public Media(String title, String category, float cost) {
        this.title = title;
        this.category = category;
        this.cost = cost;
        nbMedia++;
        this.id = nbMedia;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public float getCost() {
        return cost;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Media) {
            Media other = (Media) obj;
            return this.title.equals(other.title);
        }
        return false;
    }
}