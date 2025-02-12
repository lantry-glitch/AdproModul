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

    public void updateProduct(Product updatedProduct) {
        String updatedProductId = updatedProduct.getProductId();
        for (int i = 0; i < productsData.size(); i++) {
            String currentProductId = productsData.get(i).getProductId();
            if (currentProductId.equals(updatedProductId)) {
                productsData.set(i, updatedProduct);
                break;
            }
        }
    }

    public void delete(String deletedProductId) {
        productsData.removeIf(product -> product.getProductId().equals(deletedProductId));
    }

    public Iterator<Product> findAll() {
        return productsData.iterator();
    }
}
