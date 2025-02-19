package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Test
    public void testFindAll() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Sabun");
        product1.setProductQuantity(100);

        Product product2 = new Product();
        product2.setProductId("keb558e9f-1c39-460e-8860-71af6af63bd6");
        product2.setProductName("Sabun Cap Sampo");
        product2.setProductQuantity(10);

        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2).iterator());

        List<Product> productList = productService.findAll();

        assertEquals(2, productList.size());
        assertEquals(product1, productList.get(0));
        assertEquals(product2, productList.get(1));
        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void testCreateProduct() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Sabun");
        product.setProductQuantity(100);

        when(productRepository.create(product)).thenReturn(product);

        Product createdProduct = productService.create(product);

        assertEquals(product, createdProduct);
        verify(productRepository, times(1)).create(product);
    }

    @Test
    public void testEditProduct() {
        Product editedProduct = new Product();
        editedProduct.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        editedProduct.setProductName("Sampo Cap Ek");
        editedProduct.setProductQuantity(10);

        when(productRepository.findById(editedProduct.getProductId())).thenReturn(editedProduct);

        productService.edit(editedProduct);

        Product updatedProduct = productService.findById(editedProduct.getProductId());

        assertEquals(editedProduct.getProductName(), updatedProduct.getProductName());
        assertEquals(editedProduct.getProductQuantity(), updatedProduct.getProductQuantity());

        verify(productRepository, times(1)).findById(editedProduct.getProductId());
    }


    @Test
    public void testDeleteProduct() {
        String productId = "eb558e9f-1c39-460e-8860-71af6af63bd6";
        when(productRepository.delete(productId)).thenReturn(true);

        assertTrue(productService.delete(productId));
        verify(productRepository, times(1)).delete(productId);
    }

    @Test
    public void testDeleteProductNotFound() {
        String invalidId = "invalid-id";
        when(productRepository.delete(invalidId)).thenReturn(false);

        assertFalse(productService.delete(invalidId));
        verify(productRepository, times(1)).delete(invalidId);
    }

    @Test
    public void testGetFound() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Sabun");
        product.setProductQuantity(100);

        when(productRepository.findById(product.getProductId())).thenReturn(product);

        Product foundProduct = productService.findById(product.getProductId());

        assertEquals(product, foundProduct);
        verify(productRepository, times(1)).findById(product.getProductId());
    }

    @Test
    public void testGetNotFound() {
        String invalidId = "invalid-id";
        when(productRepository.findById(invalidId)).thenReturn(null);

        Product foundProduct = productService.findById(invalidId);

        assertNull(foundProduct);
        verify(productRepository, times(1)).findById(invalidId);
    }
}