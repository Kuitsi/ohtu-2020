package ohtu;

import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tester {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:4567");

        sleep(2);

        // login
        //WebElement element = driver.findElement(By.linkText("login"));
        // new user
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();

        sleep(2);

        // login with correct username and password
        /*element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));*/

        // login with correct username, incorrect password
        /*element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("incorrect");
        element = driver.findElement(By.name("login"));*/

        // new user
        Random r = new Random();
        element = driver.findElement(By.id("username"));
        element.sendKeys("pekka"+r.nextInt(100000));
        element = driver.findElement(By.id("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.id("passwordConfirmation"));
        element.sendKeys("akkep");

        sleep(2);
        element.submit();

        sleep(3);

        // logout after new user
        clickLinkWithText("continue to application mainpage", driver);
        sleep(2);
        clickLinkWithText("logout", driver);
        sleep(3);

        driver.quit();
    }

    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }

    private static void clickLinkWithText(String text, WebDriver driver) {
        int trials = 0;
        while( trials++<5 ) {
            try{
                WebElement element = driver.findElement(By.linkText(text));
                element.click();
                break;
            } catch(Exception e) {
                System.out.println(e.getStackTrace());
            }
        }
    }
}
