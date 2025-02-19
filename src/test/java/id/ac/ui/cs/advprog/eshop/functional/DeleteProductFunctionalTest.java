package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
public class DeleteProductFunctionalTest {
    @LocalServerPort
    private int serverPort;

    @Value("${app.baseURL:http://localhost}")
    private String testBaseUrl;
    private String baseUrl;

    @BeforeEach
    void setUp() {
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }

    @Test
    void createAndDeleteProduct_isCorrect(ChromeDriver driver) {
        driver.get(baseUrl + "/product/create");

        WebElement nameInput = driver.findElement(By.name("productName"));
        WebElement quantityInput = driver.findElement(By.name("productQuantity"));
        WebElement submitButton = driver.findElement(By.tagName("button"));

        nameInput.sendKeys("Sabun Cap Ybara");
        quantityInput.sendKeys("82");
        submitButton.click();

        driver.get(baseUrl + "/product/list");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        WebElement deleteButton = driver.findElement(By.xpath("//button[contains(text(),'Delete')]"));
        deleteButton.click();

        Alert deleteConfirmationAlert = driver.switchTo().alert();
        deleteConfirmationAlert.accept();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        WebElement productTable = driver.findElement(By.tagName("table"));
        String pageSource = productTable.getText();

        assertFalse(pageSource.contains("Sabun Cap Ybara"));
        assertFalse(pageSource.contains("82"));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }
}