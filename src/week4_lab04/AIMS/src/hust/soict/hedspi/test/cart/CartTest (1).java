package hust.soict.hedspi.aims.cart;

import hust.soict.hedspi.aims.disc.DigitalVideoDisc;

public class CartTest {
    public static void main(String[] args) {
        // Tạo giỏ hàng mới
        Cart cart = new Cart();

        // Tạo các đối tượng DVD và thêm vào giỏ hàng (theo trang 12)
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King",
                "Animation", "Roger Allers", 87, 19.95f);
        cart.addDigitalVideoDisc(dvd1);

        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars",
                "Science Fiction", "George Lucas", 124, 24.95f);
        cart.addDigitalVideoDisc(dvd2);

        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladin",
                "Animation", 18.99f);
        cart.addDigitalVideoDisc(dvd3);

        // Test phương thức print() (theo trang 11)
        System.out.println("=== TEST PRINT CART ===");
        cart.print();
        System.out.println("=======================");

        // Test tìm kiếm theo ID (giả định DVD có ID)
        System.out.println("\n=== TEST SEARCH BY ID ===");
        cart.searchById(1); // Tìm DVD đầu tiên
        cart.searchById(999); // Test không tìm thấy
        System.out.println("=======================");

        // Test tìm kiếm theo tiêu đề (sử dụng isMatch())
        System.out.println("\n=== TEST SEARCH BY TITLE ===");
        cart.searchByTitle("lion king"); // Khớp chính xác
        cart.searchByTitle("star"); // Khớp một phần
        cart.searchByTitle("Harry Potter"); // Không tìm thấy
        System.out.println("===========================");

        // Test tính tổng chi phí
        System.out.printf("\nTotal cost: %.2f $\n", cart.totalCost());
    }
}