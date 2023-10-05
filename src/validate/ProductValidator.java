package validate;

import input.Input;

public class ProductValidator {
    public static int inputId() {
        String regex = "^[0-9]{3}$";
        String id;
        do {
            System.out.println("Nhap id 3 chu so: ");
            id = Input.inputString();
        } while (!id.matches(regex));
        return Integer.parseInt(id);
    }

    public static String inputName() {
        String regex = "^[a-zA-Z1-9\\s]{6,8}$";
        String name;
        do {
            System.out.println("Nhap ten 6-8 ky tu: ");
            name = Input.inputString();
        } while (!name.matches(regex));
        return name;
    }

    public static int inputPrice() {
        String regex = "^(0|[1-9]\\d{0,2})$";
        String price;
        do {
            System.out.println("Nhap gia tien nho hon 1000: ");
            price = Input.inputString();
        } while (!price.matches(regex));
        return Integer.parseInt(price);
    }

    public static int inputQuantity() {
        String regex = "^(0?[1-9]|[1-9]\\d)$";
        String quantity;
        do {
            System.out.println("Nhap so luong nho hon 100: ");
            quantity = Input.inputString();
        } while (!quantity.matches(regex));
        return Integer.parseInt(quantity);
    }
}
