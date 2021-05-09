package test;

import com.test.selenium.BaskedOperation;
import com.test.selenium.LoginPage;
import com.test.selenium.ProductOperations;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import selenium.GittiGidiyorHome;
import selenium.LoginPage;
import selenium.WebDriver;

public class TestGittiGidiyorProject {
    WebDriver driver;
    private Object Assert;

    @Before
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "C:\\chrome-driver\\chromedriver_win32\\chromedriver.exe");
        this.driver = new ChromeDriver();
    }

    @Test
    public void start() {
     GittiGidiyorHome GittiGidiyorApp = new GittiGidiyorHome(driver);
        /* www.gittigidiyor.com sitesi açılır. */
        GittiGidiyorApp.gotoGittiGidiyor();

        Assert.assertEquals(driver.getCurrentUrl(),"https://www.gittigidiyor.com/");
        /* Ana sayfanın açıldığı kontrol edilir. Siteye login olunur. */
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setEmail("burak@gmail.com");
        loginPage.setPass("xxxxxxxx");
        loginPage.loginGittiGidiyor();
        /* kullanıcı ismi ile Login işlemi kontrol edilir. */
        Assert.assertEquals(loginPage.loginControl(),"burak");
        /* Arama kutucuğuna 'samsung' kelimesi girilir. */
        ProductOperations productOperations = new ProductOperations(driver);
        productOperations.setSearchWord("samsung");
        productOperations.sendAndClickSearch();
        /* Arama sonuçları sayfasından 2.sayfa açılır. */
        productOperations.secondPage();
        /* 2.sayfanın açıldığı kontrol edilir. */
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.gittigidiyor.com/arama?q=samsung&pg=2");
        /*Favorilerim linkine tıklanılır.*/
        productOperations.setSearchWord("favorilerim");
        productOperations.sendAndClickSearch();

        /* Sonuca göre sergilenen ürünlerden rastgele bir ürün seçilir. */
        productOperations.productSelect();

        BaskedOperation baskedOperation = new BaskedOperation(driver);
        /* Rastgele Seçilen ürün sepete eklenir.*/
        baskedOperation.addBasked();
        /* Sepet görüntülenir */
        baskedOperation.goToBasked();


        /* Ürün sepetten silinerek sepetin boş olduğu kontrol edilir. */
        baskedOperation.baskedProductRemove();
        Assert.assertEquals(baskedOperation.productRemoveControl(),"Sepetiniz Boş");

        System.out.println("Successful");
    }


}
