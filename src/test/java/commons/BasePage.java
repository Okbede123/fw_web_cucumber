package commons;


import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class BasePage {

   protected WebDriver driver;

    public BasePage(WebDriver driver){
        this.driver = driver;
    }

    protected String castToParameter(String locator,String...values){
        return String.format(locator,(Object[])values);
    }


    protected By getByLocator(String locator) {

        By locatorcut = null;

        if(locator.startsWith("ID=") || locator.startsWith("Id=") || locator.startsWith("iD=") || locator.startsWith("id=")){
            //return By.id(locator.substring(3));
            locatorcut = By.id(locator.substring(3));
        }
        else if(locator.startsWith("name=") || locator.startsWith("Name=") || locator.startsWith("NAME=")){
            locatorcut = By.name(locator.substring(5));
        }
        else if(locator.startsWith("xpath=") || locator.startsWith("XPATH=") || locator.startsWith("Xpath=")){
            locatorcut = By.xpath(locator.substring(6));
        }
        else if(locator.startsWith("CSS=") || locator.startsWith("Css=") || locator.startsWith("css=")){
            locatorcut = By.cssSelector(locator.substring(4));
        }
        else if(locator.startsWith("class=") || locator.startsWith("CLASS=") || locator.startsWith("Class=")){
            locatorcut = By.className(locator.substring(6));
        }
        else {
            throw new RuntimeException("Locator is not valild");
        }
        return locatorcut;

    }

    protected WebDriver.Navigation driverNavigate(){
      return  driver.navigate();
    }

    protected void backPageandForward(){
        driverNavigate().back();
        driverNavigate().forward();
    }

    protected void backPage(){
        driverNavigate().back();
    }
    protected void forwardPage(){
        driverNavigate().forward();
    }

    protected WebElement searchToElement(String locator, String...values){
      return  driver.findElement(getByLocator(castToParameter(locator,values)));
    }

    protected void clickToElements(String locator,String...values){
        waitElementClick(locator,values);
        searchToElement(locator,values).click();
    }

    protected void sendKeysToElement(String locator,CharSequence valueToSend,String...values){
        waitElementVisibility(locator,values);
        searchToElement(locator,values).clear();
        searchToElement(locator,values).sendKeys(valueToSend);
    }


    protected void moveToElement(String locator,String...values){
        waitElementVisibility(locator,values);
        new Actions(driver).moveToElement(searchToElement(locator,values)).perform();
    }

    protected void selectItem(String locator,String valueText,String...values){
        Select select;
      select = new Select(searchToElement(castToParameter(locator,values)));
      select.selectByVisibleText(valueText);
    }

    protected String getText(String locator,String...values){
        return searchToElement(locator,values).getText();
    }

    protected String getNameUrl(){
       return driver.getCurrentUrl();
    }

    protected void refeshPage(){
        driver.navigate().refresh();
    }

    protected void goToURl(String url){
        driver.get(url);
        driver.manage().window().maximize();
    }

    protected void sendKey(String locator,String value){
        waitElementVisibility(locator);
        searchToElement(locator).clear();
        searchToElement(locator).sendKeys(value);
    }

    protected void waitElementVisibility(String locator, String...values){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(castToParameter(locator,values))));
    }

    protected Set<Cookie> setCookie(){
        return driver.manage().getCookies();
    }

    protected void addCookie(Set<Cookie> cookies){
        for (Cookie c: cookies) {
            driver.manage().addCookie(c);
        }
        refeshPage();
    }

    protected void waitElementClick(String locator,String...values){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(getByLocator(castToParameter(locator,values))));
    }

    protected void waitAlertPresentAndAccept(){
        new WebDriverWait(driver,Duration.ofSeconds(5)).until(ExpectedConditions.alertIsPresent()).accept();
    }

    protected void waitAlertPresentAndDismiss(){
        new WebDriverWait(driver,Duration.ofSeconds(5)).until(ExpectedConditions.alertIsPresent()).dismiss();
    }

    protected String waitAlertPresentAndGetText(){
       return new WebDriverWait(driver,Duration.ofSeconds(5)).until(ExpectedConditions.alertIsPresent()).getText();
    }


    protected void waitElementInvisible(String locator, String...values){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(castToParameter(locator,values))));
    }

    protected boolean isElementDisplayed(String locator,String... values){
        waitElementClick(locator,values);
      return searchToElement(locator,values).isDisplayed();
    }

    protected boolean ElementNotDisplay(String locator,String...values){
        if(getListElement(locator,values).size() == 0){
            System.out.println("element not in dom");
            return true;
        } else if (getListElement(locator,values).size() > 0 && !getListElement(locator,values).get(0).isDisplayed()) {
            System.out.println("element in dom and not display");
            return true;
        } else if (getListElement(locator,values).size() > 0) {
            System.out.println("element in dom and display");
            return false;
        }
        else {
            return false;
        }
    }

    protected boolean isSelectedElement(String locator,String... values){
        waitElementClick(locator,values);
       return searchToElement(locator,values).isSelected();
    }

    protected List<WebElement> getListElement(String locator,String... values){
        return driver.findElements(getByLocator(castToParameter(locator,values)));
    }

    protected void switchToIframe(String locator,String...values){
        driver.switchTo().frame(castToParameter(locator,values));
    }

    protected String getWindowHandle(){
        return driver.getWindowHandle();
    }

    protected Set<String> getWindowHandles(){
        return driver.getWindowHandles();
    }

    protected String getTitles(){
        return driver.getTitle();
    }

    protected void clickByJs(String locator,String...values){
//        waitElementClick(locator,values);
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();",searchToElement(locator,values));
    }

    protected String getAttribute(String locator,String nameAttribute,String...values){
      return searchToElement(locator,values).getAttribute(nameAttribute);
    }

    protected void removeAttribute(String locator,String attribute,String...values){
        ((JavascriptExecutor)driver).executeScript("arguments[0].removeAttribute('" +attribute+ "')",searchToElement(locator,values));
    }

    protected WebDriver switchToId(String id){
        return driver.switchTo().window(id);
    }

    protected void switchToWindowTabs(String titleExpected){
        sleepInTime(1);
        Set<String> allTabs = getWindowHandles();
        if(allTabs.size() > 1){
            for (String tab:allTabs) {
                switchToId(tab);
                if(getTitles().equals(titleExpected)){
                    break;
                }
            }
        }
    }

    protected void switchToWindowById(){
        String idTabPresent = getWindowHandle();
        Set<String> allTabid = getWindowHandles();
        for (String tab:allTabid) {
            if(!tab.equals(idTabPresent)){
                switchToId(tab);
            }
        }
    }

    protected void sleepInTime(int time){
        try {
            Thread.sleep(time*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }



    protected int randomNum(int num){
        Random rand = new Random();
        int random = rand.nextInt(1000);
        return random;
    }
}
