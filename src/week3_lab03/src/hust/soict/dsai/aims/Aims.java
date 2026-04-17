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

    private static int readInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.print("Vui lòng nhập số hợp lệ: ");
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    private static float readFloat(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextFloat()) {
            scanner.next();
            System.out.print("Vui lòng nhập số hợp lệ: ");
        }
        float value = scanner.nextFloat();
        scanner.nextLine();
        return value;
    }

    public static void viewStore() {
        store.print();
        System.out.println("\nOptions: 1. Add to cart | 2. Play media | 0. Back");
        int subChoice = readInt("Lựa chọn: ");

        switch (subChoice) {
            case 1:
                System.out.print("Nhập tiêu đề sản phẩm muốn thêm: ");
                String addTitle = scanner.nextLine();
                Media mediaToAdd = store.searchByTitle(addTitle);
                if (mediaToAdd != null) {
                    cart.addMedia(mediaToAdd);
                } else {
                    System.out.println("Không tìm thấy sản phẩm trong store.");
                }
                break;
            case 2:
                System.out.print("Nhập tiêu đề sản phẩm muốn phát: ");
                String playTitle = scanner.nextLine();
                Media mediaToPlay = store.searchByTitle(playTitle);
                if (mediaToPlay instanceof Playable) {
                    ((Playable) mediaToPlay).play();
                } else if (mediaToPlay != null) {
                    System.out.println("Sản phẩm này không thể phát.");
                } else {
                    System.out.println("Không tìm thấy sản phẩm trong store.");
                }
                break;
            default:
                break;
        }
    }

    public static void updateStore() {
        System.out.println("1. Add Media | 2. Remove Media | 0. Back");
        int subChoice = readInt("Lựa chọn: ");

        switch (subChoice) {
            case 1:
                System.out.println("1. Book | 2. DVD");
                int type = readInt("Chọn loại media: ");
                System.out.print("Nhập tiêu đề: ");
                String title = scanner.nextLine();
                System.out.print("Nhập thể loại: ");
                String category = scanner.nextLine();
                float cost = readFloat("Nhập giá: ");
                if (type == 1) {
                    store.addMedia(new Book(title, category, cost));
                } else if (type == 2) {
                    System.out.print("Nhập đạo diễn: ");
                    String director = scanner.nextLine();
                    int length = readInt("Nhập thời lượng (phút): ");
                    store.addMedia(new DigitalVideoDisc(title, category, director, length, cost));
                } else {
                    System.out.println("Loại media không hợp lệ.");
                }
                break;
            case 2:
                System.out.print("Nhập tiêu đề media muốn xoá: ");
                String removeTitle = scanner.nextLine();
                Media mediaToRemove = store.searchByTitle(removeTitle);
                if (mediaToRemove != null) {
                    store.removeMedia(mediaToRemove);
                    System.out.println("Đã xóa sản phẩm khỏi store.");
                } else {
                    System.out.println("Không tìm thấy sản phẩm trong store.");
                }
                break;
            default:
                break;
        }
    }

    public static void viewCart() {
        cart.print();
        System.out.println("\nOptions: 1. Remove | 2. Play media | 0. Back");
        int subChoice = readInt("Lựa chọn: ");

        switch (subChoice) {
            case 1:
                System.out.print("Nhập tiêu đề sản phẩm muốn xoá khỏi giỏ: ");
                String removeTitle = scanner.nextLine();
                Media mediaToRemove = cart.searchByTitle(removeTitle);
                if (mediaToRemove != null) {
                    cart.removeMedia(mediaToRemove);
                } else {
                    System.out.println("Không tìm thấy sản phẩm trong giỏ.");
                }
                break;
            case 2:
                System.out.print("Nhập tiêu đề sản phẩm muốn phát: ");
                String playTitle = scanner.nextLine();
                Media mediaToPlay = cart.searchByTitle(playTitle);
                if (mediaToPlay instanceof Playable) {
                    ((Playable) mediaToPlay).play();
                } else if (mediaToPlay != null) {
                    System.out.println("Sản phẩm này không thể phát.");
                } else {
                    System.out.println("Không tìm thấy sản phẩm trong giỏ.");
                }
                break;
            default:
                break;
        }
    }

    public static void initData() {
        store.addMedia(new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f));
        store.addMedia(new DigitalVideoDisc("Star Wars", "Sci-Fi", "George Lucas", 124, 24.95f));
        store.addMedia(new Book("Java Programming", "Tech", 15.0f));
    }
}
