package menu;

import input.Input;
import manager.ProductManager;
import model.Product;
import validate.ProductValidator;

import java.util.List;

public class ProductMenu {
    private static final ProductManager productManager = new ProductManager();

    public static void run() {
        int choice;
        do {
            System.out.println("========= Product Manager ====== \n" +
                    "1. Thêm mới sản phẩm\n" +
                    "2. Sửa sản phẩm\n" +
                    "3. Xóa sản phẩm\n" +
                    "4. Hiển thị tất cả\n" +
                    "5. Tìm theo Id\n" +
                    "6. Tìm theo tên\n" +
                    "7. Tìm theo loại hàng\n" +
                    "0.Thoát");
            System.out.println("Nhập lựa chọn:");
            choice = Input.inputNumber();
            switch (choice) {
                case 1:
                    addMenu();
                    break;
                case 2:
                    editMenu();
                    break;
                case 3:
                    deleteMenu();
                    break;
                case 4:
                    showAll();
                    break;
                case 5:
                    findByIdMenu();
                    break;
                case 6:
                    findByNameMenu();
                    break;
                case 7:
                    findByCategoryMenu();
                    break;
            }
        } while (choice != 0);
    }

    private static void findByCategoryMenu() {
        System.out.println("==========Tìm kiếm bằng loại hàng==========");
        System.out.println("Nhập loại hàng:");
        String category = Input.inputString();
        List<Product> searchList = productManager.findByCategory(category);
        for (Product product : searchList) System.out.println(product);
    }

    private static void findByNameMenu() {
        System.out.println("==========Tìm kiếm bằng tên==========");
        System.out.println("Nhập tên:");
        String name = Input.inputString();
        List<Product> searchList = productManager.findByName(name);
        for (Product product : searchList) System.out.println(product);
    }

    private static void findByIdMenu() {
        System.out.println("==========Tìm kiếm bằng Id==========");
        System.out.println("Nhập Id:");
        int id = ProductValidator.inputId();
        int index = productManager.findById(id);
        if (index == -1) {
            System.out.println("Id không tồn tại");
            return;
        }
        System.out.println(productManager.findAll().get(index));
    }

    private static void deleteMenu() {
        System.out.println("---=Xoá sản phẩm=---");
        System.out.println("Nhập Id sản phẩm:");
        int id = ProductValidator.inputId();
        int productIndex = productManager.findById(id);
        if (productIndex == -1) {
            System.out.println("====Id sản phẩm không tồn tại====\n " +
                    "1. Nhập lại Id\n " +
                    "0. Quay về menu\n " +
                    "Nhập lựa chọn:");
            int choice = Input.inputNumber();
            switch (choice) {
                case 1:
                    deleteMenu();
                    break;
                case 0:
                    return;
            }
        }
        productManager.delete(id);
        System.out.println("===Xoá thành công===");
    }

    private static void editMenu() {
        System.out.println("---=Sửa đổi thông tin sản phẩm=---");
        System.out.println("Nhập Id sản phẩm cần sửa đổi:");
        int id = ProductValidator.inputId();
        int productIndex = productManager.findById(id);
        while (productIndex == -1) {
            System.out.println("Id sản phẩm không tồn tại, vui lòng nhập lại;");
            id = ProductValidator.inputId();
            productIndex = productManager.findById(id);
        }
        Product product = productManager.findAll().get(productIndex);
        System.out.println(product);
        String name = ProductValidator.inputName();
        int price = ProductValidator.inputPrice();
        int quantity = ProductValidator.inputQuantity();
        System.out.println("Nhập loại mặt hàng:");
        String category = Input.inputString();
        Product newProduct = new Product(id, name, price, quantity, category);
        productManager.edit(id, newProduct);
        System.out.println("Sửa đổi thành công.");
    }

    public static void addMenu() {
        System.out.println("---=Thêm sản phẩm mới=---");
        int id = ProductValidator.inputId();
        while (productManager.findById(id) != -1) {
            System.out.println("Id đã tồn tại, mời nhập lại:");
            id = ProductValidator.inputId();
        }
        String name = ProductValidator.inputName();
        int price = ProductValidator.inputPrice();
        int quantity = ProductValidator.inputQuantity();
        System.out.println("Nhập loại mặt hàng:");
        String category = Input.inputString();
        productManager.add(new Product(id, name, price, quantity, category));
        System.out.println("===Thêm thành công===");
    }

    public static void showAll() {
        System.out.println("=======Danh sách sản phẩm=======");
        List<Product> list = productManager.findAll();
        for (Product product : list) {
            System.out.println(product);
        }
    }
}
