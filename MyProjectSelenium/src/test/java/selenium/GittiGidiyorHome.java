package selenium;

public class GittiGidiyorHome {
    WebDriver driver;

    public GittiGidiyorHome(WebDriver driver){
        this.driver = driver;
    }

    public void gotoN11(){
        driver.get("https://www.gittigidiyor.com/");
    }
}
