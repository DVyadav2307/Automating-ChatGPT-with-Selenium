import java.io.File;
import java.util.Date;
import java.util.Scanner;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class App {
    public static void main(String[] args) throws Exception {

        // String URL = "https://bard.google.com";

        System.setProperty("webdriver.edge.driver", "lib\\msedgedriver.exe");
        
        WebDriver driver = new ChromeDriver();

        
        gptLogin(driver);
        beginPrompting(driver);
        
        
    }


    static void gptLogin(WebDriver driver) throws Exception{

        // driver.manage().window().minimize();
        driver.get("https://chat.openai.com/");
        Thread.sleep(10000);

        driver.manage().deleteAllCookies();
        // System.out.println("---------\n" + driver.manage().getCookies());
        
        System.out.println("login button clicked....");
        driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div[1]/div/div/button[1]")).click();
        Thread.sleep(10000);
        
        System.out.println("Continue using Google Account.");
        driver.findElement(By.xpath("/html/body/div/main/section/div/div/div/div[4]/form[2]/button")).click();
        // Thread.sleep(5000);

        Scanner input  = new Scanner(System.in);
        System.out.print("Enter the gmail account: ");
        String email = input.nextLine();
        
        WebElement emailfeild = driver.findElement(By.id("identifierId"));
        emailfeild.sendKeys(email);
        emailfeild.sendKeys(Keys.RETURN);
        // Thread.sleep(10000);

        System.out.print("Enter Password:   ---hidden for security\n");        
        String password = "#DSEU23072004";
        input = null;

	    Thread.sleep(10000);
        WebElement passfeild = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div/c-wiz/div/div[2]/div/div[1]/div/form/span/section[2]/div/div/div[1]/div[1]/div/div/div/div/div[1]/div/div[1]/input"));
        passfeild.sendKeys(password);
        passfeild.sendKeys(Keys.RETURN);

        System.out.println("Authenticating....");
        Thread.sleep(15000);
        
        driver.switchTo().activeElement().sendKeys(Keys.RETURN);

        System.out.println("Login Success!!\nYou can start prompting now.");
        Thread.sleep(5000);

        

    }

    /* under maintenece --> */static void bardLogin(WebDriver driver) throws Exception{
        driver.get("https://bard.google.com");
        Thread.sleep(10000);

        driver.findElement(By.xpath("/html/body/chat-app/side-navigation/mat-sidenav-container/mat-sidenav-content/main/welcome-window/div/landing-page-variant-a/div/div/div/button/span[2]")).click();

    }

    static void beginPrompting(WebDriver driver) throws Exception{

        

        for (int i = 2; i < 21;i+=2) {
            System.out.println("\n\n");

            System.out.print("Enter your prompt: ");
            Scanner input = new Scanner(System.in);
            String prompt = input.nextLine();
            input = null;

            driver.findElement(By.id("prompt-textarea")).sendKeys(prompt);
            // input.
            Thread.sleep(5000);
            driver.findElement(By.id("__next")).click();
            System.out.println("generating answer.......      ");
            Thread.sleep(5000);
            System.out.println("Almost There.......       (Estimated wait 5 sec)\n");
            Thread.sleep(5000);
            System.out.println("-----------------------------------");
            System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/main/div[1]/div[1]/div/div/div/div["+ i +"]/div/div/div[2]/div/div[1]/div/div")).getText());
        }

        System.out.println("\n***Prompmt Limit Reached***");
        driver.quit();
    }

    static void instaLogin(WebDriver driver) throws Exception{
        driver.get("https://www.instagram.com");
        Thread.sleep(5000);
        driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div/div/div/div[1]/section/main/article/div[2]/div[1]/div[2]/form/div/div[1]/div/label/input")).sendKeys("dvyadav_2307");
        driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div/div/div/div[1]/section/main/article/div[2]/div[1]/div[2]/form/div/div[2]/div/label/input")).sendKeys("7112003");
        driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div/div/div/div[1]/section/main/article/div[2]/div[1]/div[2]/form/div/div[3]/button")).click();

        Thread.sleep(10000);
        driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div/div/div/div[1]/div[1]/div[2]/section/main/div/div/div/div/div")).click();
    }

    static void exportCookie(WebDriver driver)throws Exception{
        Thread.sleep(5000);
        Set<Cookie> cokkieSet =  driver.manage().getCookies();
        
        for (Cookie cookie : cokkieSet) {
            // System.out.println(cookie);

            System.out.println(cookie.getName());
            System.out.println(cookie.getValue());
            System.out.println(cookie.getDomain());
            System.out.println(cookie.getPath());
           if(cookie.getExpiry() != null)
                System.out.println(cookie.getExpiry().getTime());
            else
                System.out.println(0);
            System.out.println(cookie.isSecure());
            System.out.println(cookie.getSameSite());
        } 

    }

    static void setCookie(WebDriver driver, String path)throws Exception{

        
        
        Thread.sleep(10000);
        driver.manage().deleteAllCookies();
        System.out.println("---------\n" + driver.manage().getCookies());
        
        Scanner input = new Scanner(new File(path));
        int i=0;
        while (input.hasNextLine()) {
            System.out.println(i++);
            driver.manage().addCookie(new Cookie(input.nextLine(), input.nextLine(), input.nextLine(), input.nextLine(), new Date(Long.parseLong(input.nextLine())), Boolean.parseBoolean(input.nextLine()), false, input.nextLine()));
            
        }

        System.out.println("---------\n" + driver.manage().getCookies());
    }

}
