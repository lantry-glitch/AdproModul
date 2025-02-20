package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.apache.hc.core5.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureJsonTesters
@AutoConfigureMockMvc
@SpringBootTest
public class ProductControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private ProductService service;

    @Autowired
    private JacksonTester<Product> jsonProduct;

    @Test
    void testHomePage() throws Exception {
        mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("HomePage"));
    }

    @Test
    void testProductListPage() throws Exception{
        MockHttpServletResponse response = mvc.perform(get("/product/list")).andReturn().getResponse();
        assertEquals(HttpStatus.SC_OK, response.getStatus());
    }

    @Test
    void testCreateProductPage() throws Exception {
        MockHttpServletResponse response = mvc.perform(get("/product/create")).andReturn().getResponse();
        assertEquals(HttpStatus.SC_OK, response.getStatus());
    }

    @Test
    void testEditProductPage() throws Exception {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Sabun");
        product.setProductQuantity(10);

        Mockito.when(service.findById("eb558e9f-1c39-460e-8860-71af6af63bd6")).thenReturn(product);

        MockHttpServletResponse response = mvc.perform(get("/product/edit/eb558e9f-1c39-460e-8860-71af6af63bd6")).andReturn().getResponse();
        assertEquals(HttpStatus.SC_OK, response.getStatus());
    }

    @Test
    public void testDeleteProductPostNotFound() throws Exception {
        mvc.perform(delete("/product/delete/{id}", "eb558e9f-1c39-460e-8860-71af6af63bd6"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));
    }

    @Test
    public void testEditProductPostValid() throws Exception {
        Product product = new Product();
        product.setProductName("Sampo Cap Sabun");
        product.setProductQuantity(10);

        mvc.perform(post("/product/edit")
                        .contentType("application/x-www-form-urlencoded")
                        .param("productName", "Sampo Cap Sabun")
                        .param("productQuantity", "10"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));
    }

    @Test
    public void testEditProductPostInvalid() throws Exception {
        mvc.perform(post("/product/edit")
                        .contentType("application/x-www-form-urlencoded")
                        .param("productName", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("EditProduct"));
    }

    @Test
    public void testCreateProductPostValid() throws Exception {
        mvc.perform(post("/product/create")
                        .contentType("application/x-www-form-urlencoded")
                        .param("productName", "Sampo Cap Sabun")
                        .param("productQuantity", "10"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("list"));
    }

    @Test
    public void testCreateProductPostInvalid() throws Exception {
        mvc.perform(post("/product/create")
                        .contentType("application/x-www-form-urlencoded")
                        .param("productName", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("CreateProduct"));
    }
}