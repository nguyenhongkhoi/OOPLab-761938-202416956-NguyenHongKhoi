package hust.soict.dsai.aims;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.*;
import hust.soict.dsai.aims.store.Store;
import java.util.Scanner;

public class Aims {
    private static Store store = new Store();
    private static Cart cart = new Cart();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Khởi tạo dữ liệu mẫu cho Store
        initData();

        int choice;
        do {
            showMenu();
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: // View Store
                    viewStore();
                    break;
                case 2: // Update Store
                    updateStore();
                    break;
                case 3: // View Cart
                    viewCart();
                    break;
                case 0:
                    System.out.println("Tạm biệt!");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (choice != 0);
    }

    public static void showMenu() {
        System.out.println("\n--- AIMS MENU ---");
        System.out.println("1. View store");
        System.out.println("2. Update store");
        System.out.println("3. See current cart");
        System.out.println("0. Exit");
        System.out.print("Chọn: ");
    }

    public static void viewStore() {
        store.print();
        System.out.println("\nOptions: 1. Add to cart | 2. Play | 0. Back");
        int subChoice = scanner.nextInt();
        scanner.nextLine();

        if (subChoice == 1) {
            System.out.print("Nhập tiêu đề sản phẩm muốn thêm: ");
            String title = scanner.nextLine();
            Media m = store.searchByTitle(title);
            if (m != null)
                cart.addMedia(m);
            else
                System.out.println("Không tìm thấy!");
        }
    }

    public static void updateStore() {
        System.out.println("1. Add Media | 2. Remove Media");
        int subChoice = scanner.nextInt();
        scanner.nextLine();
        if (subChoice == 1) {
            System.out.print("Nhập tên sách/DVD: ");
            String title = scanner.nextLine();
            store.addMedia(new Book(title, "General", 10.0f));
        }
    }

    public static void viewCart() {
        cart.print();
        System.out.println("\nOptions: 1. Remove | 2. Play | 0. Back");
        int subChoice = scanner.nextInt();
        if (subChoice == 1) {
        }
    }

    public static void initData() {
        store.addMedia(new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f));
        store.addMedia(new DigitalVideoDisc("Star Wars", "Sci-Fi", "George Lucas", 124, 24.95f));
        store.addMedia(new Book("Java Programming", "Tech", 15.0f));
    }
}