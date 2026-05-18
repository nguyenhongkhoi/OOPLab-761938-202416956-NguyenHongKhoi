package hust.soict.hedspi.aims.store;

public class StoreTest {
    public static void main(String[] args) {
        // Tạo cửa hàng
        Store store = new Store();

        // Tạo các DVD
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King",
                "Animation", "Roger Allers", 87, 19.95f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars",
                "Science Fiction", "George Lucas", 124, 24.95f);
        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladin",
                "Animation", 18.99f);

        // Thêm DVD vào cửa hàng
        store.addDVD(dvd1);
        store.addDVD(dvd2);
        store.addDVD(dvd3);

        // Hiển thị cửa hàng
        System.out.println("\n=== After adding DVDs ===");
        store.displayStore();

        // Xóa một DVD
        store.removeDVD(dvd2);

        // Hiển thị lại cửa hàng
        System.out.println("\n=== After removing a DVD ===");
        store.displayStore();

        // Thử xóa DVD không tồn tại
        DigitalVideoDisc dvd4 = new DigitalVideoDisc("Nonexistent");
        store.removeDVD(dvd4);
    }
}