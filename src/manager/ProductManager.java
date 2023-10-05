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
    public void edit(int id, Product product) {
        int index = findById(id);
        productList.set(index, product);
        rwFile.writeFile(productList);
    }

    @Override
    public void delete(int id) {
        int index = findById(id);
        productList.remove(index);
        rwFile.writeFile(productList);
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
            if (product.getName().toLowerCase().contains(searchStr.toLowerCase())) {
                resultList.add(product);
            }
        }
        return resultList;
    }

    @Override
    public List<Product> findByCategory(String category) {
        List<Product> resultList = new ArrayList<>();
        for (Product product : productList) {
            if (product.getCategory().equalsIgnoreCase(category)) {
                resultList.add(product);
            }
        }
        return resultList;
    }
}
