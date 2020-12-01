import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoginAdminTest {
    private WebDriver driver;
    private String path = "http://localhost:8080/Controller";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\bryan\\Desktop\\School\\Schooljaar 2019-2020\\Trimester2\\WEB2\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(path+"?command=Index");
    }

    @After
    public void clean() {
        driver.quit();
    }

    @Test
    public void test_Login_as_Admin_Success() {
        submitForm("u0000001", "Admin123!");

        WebElement welcomeMessage=driver.findElement(By.id("welcomeMessage"));

        assertEquals(welcomeMessage.getText(), "Welkom, admin!");

    }

    @Test
    public void test_Login_as_Admin_See_Delete_Page() {
        submitForm("u0000001", "Admin123!");

        driver.navigate().to("http://localhost:8080/verwijder.jsp");
        String title = driver.getTitle();


        assertEquals("Delete",title);
    }

    private void fillOutField(String name,String value) {
        WebElement field=driver.findElement(By.id(name));
        field.clear();
        field.sendKeys(value);
    }

    private void submitForm(String userId, String password) {
        fillOutField("userId", userId);
        fillOutField("password", password);

        WebElement button=driver.findElement(By.id("signIn"));
        button.click();
    }
}
