package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static constants.constant.TimeoutVar.EXPLICIT_WAIT_10_SEC;

@SuppressWarnings("unused")
public class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Navigate to page with URL
     *
     * @param url defines url to navigate to
     */
    @Step("Navigating to the page")
    public void open(String url) {
        driver.get(url);
    }

    /**
     * Check url is opened
     *
     * @param urlExpected defines expected url
     */
    public boolean isOpened(String urlExpected) {
        return Objects.equals(driver.getCurrentUrl(), urlExpected);
    }


//____________________________________________________Sending Keys______________________________________________________

    /**
     * Send keys to element from list by id of the element
     *
     * @param locator is a 'By' locator of element on web-page
     * @param id      is an id of element from all elements from list
     */
    public void sendKeysById(By locator, int id, String text) {
        driver.findElements(locator).get(id).sendKeys(text);
    }

    /**
     * Send text to web-element
     *
     * @param locator is a 'By' locator of element on web-page
     * @param text    is a text that will be sent to web-element
     */
    @Step("Sending keys to element")
    public void sendKeys(By locator, String text) {
        driver.findElement(locator).sendKeys(text);
    }

    /**
     * Clear keys from defined input
     *
     * @param locator defines element to be cleared
     */
    public void clearKeys(By locator) {
        WebElement element = driver.findElement(locator);
        element.clear();
    }

    /**
     * Get text of element from list by id of the element
     *
     * @param locator is a 'By' locator of element on web-page
     * @param id      is an id of element from all elements from list
     */
    public String getTextById(By locator, int id) {
        @SuppressWarnings("UnnecessaryLocalVariable")
        String text = driver.findElements(locator).get(id).getText();
        return text;
    }

//_______________________________________________________Clicks_________________________________________________________

    /**
     * Click web-element on page
     *
     * @param locator is a 'By' locator of element on web-page
     */
    @Step("Clicking element")
    public void clickElement(By locator) {
        driver.findElement(locator).click();
    }

    /**
     * Click element from list by id of the element
     *
     * @param locator is a 'By' locator of element on web-page
     * @param id      is an id of element from all elements from list
     */
    public void clickElementById(By locator, int id) {
        driver.findElements(locator).get(id).click();
    }

    /**
     * Click element using java-script
     * Can be used when it is not possible/necessary to emulate human-like behavior
     * Will click through other elements without interception
     *
     * @param locator is a 'By' locator of element on web-page
     */
    public void jsClick(By locator) {
        WebElement element = driver.findElement(locator);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click();", element);
    }

    /**
     * Click all element that will be collected with locator
     * Can be used to click all clickable elements on page.
     *
     * @param locator is a 'By' locator of element on web-page
     */
    @SuppressWarnings("unused")
    public void clickMultipleElements(By locator) {
        List<WebElement> addresses = driver.findElements(locator);
        int numberOfElements = addresses.size();
        int iteration = 0;
        while (iteration != numberOfElements) {
            driver.findElements(locator).get(iteration).click();
            iteration++;
        }
    }

    /**
     * Click all element that will be collected with locator with java-script.
     * Can be used to click all clickable elements on page.
     *
     * @param locator is a 'By' locator of element on web-page
     */
    public void jsClickMultipleElements(By locator) {
        List<WebElement> addresses = driver.findElements(locator);
        int numberOfElements = addresses.size();
        int iteration = 0;
        while (iteration != numberOfElements) {
            WebElement toClick = driver.findElements(locator).get(iteration);
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("arguments[0].click();", toClick);
            iteration++;
        }
    }

//_______________________________________________________Actions________________________________________________________

    /**
     * Scrolls to element on page
     *
     * @param element defines element to be scrolled to
     */
    public void scrollToElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.scrollToElement(element).perform();
    }

    /**
     * scrolling page by X and Y axes
     *
     * @param deltaX defines X-axes
     * @param deltaY defines Y-axes
     */
    public void scrollDownPage(int deltaX, int deltaY) {
        Actions actions = new Actions(driver);
        actions.scrollByAmount(deltaX, deltaY).perform();
    }

    /**
     * Double click element on page
     *
     * @param locator defines element to be double-clicked
     */
    public void doubleClick(By locator) {
        Actions actions = new Actions(driver);
        actions.doubleClick(driver.findElement(locator));
    }

    /**
     * Right click element on page
     *
     * @param locator defines element to be right-clicked
     */
    public void contextClick(By locator) {
        Actions actions = new Actions(driver);
        actions.contextClick(driver.findElement(locator));
    }

    /**
     * Click and hold element on page
     *
     * @param locator defines element to be clicked and held
     */
    public void clickAndHold(By locator) {
        Actions actions = new Actions(driver);
        actions.clickAndHold(driver.findElement(locator));
    }

    /**
     * Move cursor to the element
     *
     * @param locator defines the element to focus on
     */
    public void focusOnElement(By locator) {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(locator)).perform();
    }
//_______________________________________________________Alerts_________________________________________________________

    /**
     * Dismiss alert
     */
    public void dismissAlert() {
        driver.switchTo().alert().dismiss();
    }

    /**
     * Accept alert
     */
    @Step("Accepting alert")
    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    /**
     * Get text from alert
     */
    public void getAlertText() {
        driver.switchTo().alert().getText();
    }

    /**
     * Send text to alert
     *
     * @param text defines text to be sent to alert
     */
    public void sendKeysToAlert(String text) {
        driver.switchTo().alert().sendKeys(text);
    }

//______________________________________________________Switches________________________________________________________

    /**
     * Switch driver to work with new window
     */
    public void switchToNewWindow() {
        String newTab = driver.getWindowHandle();
        driver.switchTo().window(newTab);
    }

    /**
     * Return to original window
     *
     * @param origin handle for original window
     */
    public void switchToOriginalWindow(String origin) {
        driver.switchTo().window(origin);
    }

//______________________________________________________Selects_________________________________________________________

    /**
     * Select option by its value
     *
     * @param locator defines locator of options list
     * @param value   defines value that should be presented
     */
    public void selectByValue(By locator, String value) {
        Select select = new Select(driver.findElement(locator));
        select.selectByValue(value);
    }

    /**
     * Select option by its index
     *
     * @param locator defines locator of options list
     * @param index   defines index of element that should be presented
     */
    public void selectByIndex(By locator, Integer index) {
        Select select = new Select(driver.findElement(locator));
        select.selectByIndex(index);
    }

    /**
     * * Select option by its visible text
     *
     * @param locator defines locator of options list
     * @param text    defines text that should be presented
     */
    public void selectByVisibleText(By locator, String text) {
        Select select = new Select(driver.findElement(locator));
        select.selectByVisibleText(text);
    }

    /**
     * Deselect option by its value
     *
     * @param locator defines locator of options list
     * @param value   defines value that should be presented
     */
    public void deselectByValue(By locator, String value) {
        Select select = new Select(driver.findElement(locator));
        select.deselectByValue(value);
    }

    /**
     * Deselect option by its index
     *
     * @param locator defines locator of options list
     * @param index   defines index of element that should be presented
     */
    public void deselectByIndex(By locator, Integer index) {
        Select select = new Select(driver.findElement(locator));
        select.deselectByIndex(index);
    }

    /**
     * * Deselect option by its visible text
     *
     * @param locator defines locator of options list
     * @param text    defines text that should be presented
     */
    public void deselectByVisibleText(By locator, String text) {
        Select select = new Select(driver.findElement(locator));
        select.deselectByVisibleText(text);
    }

    /**
     * Deselect option by its visible text
     *
     * @param locator defines locator of options list
     */
    public void deselectAll(By locator) {
        Select select = new Select(driver.findElement(locator));
        select.deselectAll();
    }

//_______________________________________________________Waits__________________________________________________________

    /**
     * Wait until alert is visible on page
     */
    @SuppressWarnings("UnusedReturnValue")
    public void waitAlertIsVisible() {
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT_10_SEC))
                .until(ExpectedConditions.alertIsPresent());
    }

    @Step("Waiting for element to be visible")
    /**
     * Wait until web-element is visible on page
     *
     * @param locator is a 'By' locator of element on web-page
     */
    @SuppressWarnings("UnusedReturnValue")
    public WebElement waitElementIsVisible(By locator) {
        WebElement element = driver.findElement(locator);
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT_10_SEC))
                .until(ExpectedConditions.visibilityOf(element));
        return element;
    }

    /**
     * Wait until web-element is clickable on page
     *
     * @param locator is a 'By' locator of element on web-page
     */
    @SuppressWarnings("UnusedReturnValue")
    public WebElement waitElementIsClickable(By locator) {
        WebElement element = driver.findElement(locator);
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT_10_SEC))
                .until(ExpectedConditions.elementToBeClickable(element));
        return element;
    }

//________________________________________________Working with elements_________________________________________________

    /**
     * Check element is selected
     *
     * @param locator defines element to check
     */
    public boolean checkElementSelected(By locator) {
        WebElement element = driver.findElement(locator);
        return element.isSelected();
    }

    /**
     * Check element is displayed
     *
     * @param locator defines element to check
     */
    public boolean checkElementIsDisplayed(By locator) {
        WebElement element = driver.findElement(locator);
        return element.isDisplayed();
    }

    /**
     * Check element is enabled
     *
     * @param locator defines element to check
     */
    public boolean checkElementIsEnabled(By locator) {
        WebElement element = driver.findElement(locator);
        return element.isEnabled();
    }

    /**
     * Find shadow element in shadow root
     *
     * @param shadowRootLocator    defines shadow root
     * @param shadowContentLocator defines element inside shadow root
     * @return element from shadow root
     */
    public WebElement findShadowElement(By shadowRootLocator, By shadowContentLocator) {
        SearchContext element = driver.findElement(shadowRootLocator).getShadowRoot();
        return element.findElement(shadowContentLocator);
    }

    /**
     * Check if:
     *
     * @param text is visible on page
     */
    @SuppressWarnings({"ResultOfMethodCallIgnored", "UnusedReturnValue"})
    public String checkTextPresentedOnPage(String text) {
        driver.getPageSource().contains(text);
        return text;
    }

    /**
     * Count elements on page
     */
    @SuppressWarnings("UnnecessaryLocalVariable")
    public Integer countElementsOnPage(By locator) {
        Integer numberOfElements = driver.findElements(locator).size();
        return numberOfElements;
    }

    /**
     * Choose element on page by its index
     *
     * @param locator defines elements to choose from
     * @param index   defines index of element that should be chosen
     */
    @SuppressWarnings("UnnecessaryLocalVariable")
    public WebElement chooseElementByIndex(By locator, Integer index) {
        WebElement element = driver.findElements(locator)
                .stream()
                .toList()
                .get(index);
        return element;
    }

    /**
     * Take text from element on page
     *
     * @param locator defines element to take text from
     * @return text of element
     */
    @Step("Getting element text")
    @SuppressWarnings("UnnecessaryLocalVariable")
    public String getElementText(By locator) {
        String text = driver.findElement(locator).getText();
        return text;
    }

    /**
     * Get attribute value of defined element on page
     *
     * @param locator defines locator of element that contains attribute needed
     * @param name    is a name of attribute that had to be got
     * @return value of attribute via String format
     */
    public String getElementAttribute(By locator, String name) {
        @SuppressWarnings("UnnecessaryLocalVariable")
        String attribute = driver.findElement(locator).getAttribute(name);
        return attribute;
    }

    /**
     * Count web-elements by locator
     *
     * @param locator is a 'By' locator of element on web-page
     * @return returns integer value of how many elements were found on page
     */
    public int countElements(By locator) {
        return driver.findElements(locator).size();
    }

    /**
     * Pick random item from list of elements
     * Usable for different dropdowns for better coverage
     *
     * @param locator is a 'By' locator of element on web-page
     */
    public void pickRandomItemFromTable(By locator) {
        List<WebElement> itemsInDropdown = driver.findElements(locator);
        int listSize = itemsInDropdown.size();
        int randNumber = ThreadLocalRandom.current().nextInt(0, listSize);
        WebElement toClick = itemsInDropdown.get(randNumber);
        toClick.click();
    }

    /**
     * Get all attributes available for defined element
     *
     * @param locator defines element that attributes will be taken from
     */
    @SuppressWarnings("UnnecessaryLocalVariable")
    public Object getElementAttributes(By locator) {
        WebElement element = driver.findElement(locator);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        Object attributes = jse.executeScript("var items = {}; for (index = 0; index < arguments[0].attributes.length; ++index) { items[arguments[0].attributes[index].name] = arguments[0].attributes[index].value }; return items;", element);
        return attributes;
    }

    @Step("Saving option number")
    public List<Integer> separateNumbersFromString(String string) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(string);
        List<Integer> list = new ArrayList<>();
        while (matcher.find()) {
            Integer validInteger = Integer.valueOf(matcher.group());
            list.add(validInteger);
        }
        return list;
    }
}
