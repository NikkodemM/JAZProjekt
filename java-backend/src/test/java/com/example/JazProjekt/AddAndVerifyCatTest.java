package com.example.JazProjekt;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AddAndVerifyCatTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testCatsAreDisplayed() throws InterruptedException {
        driver.get("http://localhost:3000");

        Thread.sleep(2000);

        WebElement table = driver.findElement(By.tagName("table"));

        List<WebElement> rows = table.findElements(By.xpath(".//tbody/tr"));

        assertFalse(rows.isEmpty(), "Tabela z kotami jest pusta!");

        System.out.println("Liczba wyświetlonych kotów: " + rows.size());
    }
}
