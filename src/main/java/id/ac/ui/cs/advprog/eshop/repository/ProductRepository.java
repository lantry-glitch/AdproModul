package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productsData = new ArrayList<>();

    public Product create(Product product) {
        productsData.add(product);
        return product;
    }

    public Product findById(String productId) {
        return productsData.stream()
                .filter(product -> product.getProductId().equals(productId))
                .findFirst()
                .orElse(null);
    }

    public void edit(Product editedProduct) {
        for (int i = 0; i < productsData.size(); i++) {
            Product product = productsData.get(i);
            if (product.getProductId().equals(editedProduct.getProductId())) {
                productsData.set(i, editedProduct);
                break;
            }
        }
    }

    public Iterator<Product> findAll() {
        return productsData.iterator();
    }
}
