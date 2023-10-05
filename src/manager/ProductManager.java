package manager;

import dto.RWFile;
import model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductManager implements Manager<Product> {
    private final List<Product> productList;

    private final RWFile rwFile = new RWFile();

    public ProductManager() {
        productList = rwFile.readFile();
    }

    @Override
    public void add(Product product) {
        productList.add(product);
        rwFile.writeFile(productList);
    }

    @Override
    public boolean edit(int id, String name, int quantity, int price, String category) {
        int index = findById(id);
        if (index == -1) return false;
        Product product = productList.get(index);
        product.setName(name);
        product.setQuantity(quantity);
        product.setPrice(price);
        product.setCategory(category);
        rwFile.writeFile(productList);
        return true;
    }

    @Override
    public boolean delete(int id) {
        int index = findById(id);
        if (index == -1) return false;
        productList.remove(index);
        rwFile.writeFile(productList);
        return true;
    }

    @Override
    public List<Product> findAll() {
        return productList;
    }

    @Override
    public int findById(int id) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId() == id) return i;
        }
        return -1;
    }

    @Override
    public List<Product> findByName(String searchStr) {
        List<Product> resultList = new ArrayList<>();
        for (Product product : productList) {
            if (product.getName().contains(searchStr)) {
                resultList.add(product);
            }
        }
        return resultList;
    }

    @Override
    public List<Product> findByCategory(String category) {
        List<Product> resultList = new ArrayList<>();
        for (Product product : productList) {
            if (product.getCategory().equals(category)) {
                resultList.add(product);
            }
        }
        return resultList;
    }
}
