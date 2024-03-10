package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MainPage extends BasePage {
    public final By TITLE_LOCATOR = By.id("title");
    public final By RADIO_TITLE_LOCATOR = By.id("radio");
    public final By RADIO_OPTION_ONE_LOCATOR = By.id("radio1");
    public final By RADIO_OPTION_TWO_LOCATOR = By.id("radio2");
    public final By RADIO_OPTION_THREE_LOCATOR = By.id("radio3");
    public final By CHECKBOXES_TITLE_LOCATOR = By.id("checkbox");
    public final By CHECKBOX_OPTION_ONE_LOCATOR = By.id("checkbox1");
    public final By CHECKBOX_OPTION_TWO_LOCATOR = By.id("checkbox2");
    public final By CHECKBOX_OPTION_THREE_LOCATOR = By.id("checkbox3");
    public final By INPUT_TITLE_LOCATOR = By.id("input");
    public final By NAME_INPUT_LOCATOR = By.id("text1");
    public final By EMAIL_INPUT_LOCATOR = By.id("text2");
    public final By PHONE_NUMBER_INPUT_LOCATOR = By.id("text3");
    public final By SUBMIT_BUTTON_LOCATOR = By.id("submit");
    public final By DROPDOWN_TITLE_LOCATOR = By.id("dropdown");
    public final By DROPDOWN_LOCATOR = By.xpath("//select[@id='select-dropdown']");
    public final By LINKS_TITLE_LOCATOR = By.id("links");
    public final By LINKS_LOCATOR = By.xpath("//a[contains(@href,'')]");
    public final By FIRST_LINK_LOCATOR = By.xpath("//a[@href='link1.html']");
    public final By SECOND_LINK_LOCATOR = By.xpath("//a[@href='LINK1.html']");
    public final By THIRD_LINK_LOCATOR = By.xpath("//a[@href='link2.html']");
    public final By FOURTH_LINK_LOCATOR = By.xpath("//a[@href='LINK2.html']");
    public final By RADIO_OPTION_SELECTED_LOCATOR = By.id("radio-option-selected");
    public final By CHECKBOX_SELECTED_LOCATOR = By.id("options-selected");
    public final By DROPDOWN_SELECTED_LOCATOR = By.id("dropdown-option-selected");
    public final By USER_NAME_TABLE_LOCATOR = By.xpath("//tbody/tr[1]/td[2]");
    public final By USER_EMAIL_TABLE_LOCATOR = By.xpath("//tbody/tr[2]/td[2]");
    public final By USER_PHONE_TABLE_LOCATOR = By.xpath("//tbody/tr[3]/td[2]");



    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Check title displayed")
    public String checkTitle() {
        return getElementText(TITLE_LOCATOR);
    }


    public void checkTittleDisplayed(){
        checkElementIsDisplayed(TITLE_LOCATOR);
    }

    @Step("Check radio button section displayed")
    public void checkRadioDisplayed(){
        checkElementIsDisplayed(RADIO_TITLE_LOCATOR);
        checkElementIsDisplayed(RADIO_OPTION_ONE_LOCATOR);
        checkElementIsDisplayed(RADIO_OPTION_TWO_LOCATOR);
        checkElementIsDisplayed(RADIO_OPTION_THREE_LOCATOR);
    }

    @Step("Check checkbox section displayed")
    public void checkCheckboxDisplayed(){
        checkElementIsDisplayed(CHECKBOXES_TITLE_LOCATOR);
        checkElementIsDisplayed(CHECKBOX_OPTION_ONE_LOCATOR);
        checkElementIsDisplayed(CHECKBOX_OPTION_TWO_LOCATOR);
        checkElementIsDisplayed(CHECKBOX_OPTION_THREE_LOCATOR);
    }

    @Step("Check input fields section displayed")
    public void checkInputDisplayed(){
        checkElementIsDisplayed(INPUT_TITLE_LOCATOR);
        checkElementIsDisplayed(NAME_INPUT_LOCATOR);
        checkElementIsDisplayed(EMAIL_INPUT_LOCATOR);
        checkElementIsDisplayed(PHONE_NUMBER_INPUT_LOCATOR);
        checkElementIsDisplayed(SUBMIT_BUTTON_LOCATOR);
    }

    @Step("Check dropdown section displayed")
    public void checkDropdownDisplayed(){
        checkElementIsDisplayed(DROPDOWN_TITLE_LOCATOR);
        checkElementIsDisplayed(DROPDOWN_LOCATOR);
    }

    @Step("Check links section displayed")
    public void checkLinksDisplayed(){
        checkElementIsDisplayed(LINKS_TITLE_LOCATOR);
        checkElementIsDisplayed(FIRST_LINK_LOCATOR);
        checkElementIsDisplayed(SECOND_LINK_LOCATOR);
        checkElementIsDisplayed(THIRD_LINK_LOCATOR);
        checkElementIsDisplayed(FOURTH_LINK_LOCATOR);
    }

    @Step("Finding all elements on the page")
    public boolean findAllElements(){
        try {
            checkTittleDisplayed();
            checkRadioDisplayed();
            checkCheckboxDisplayed();
            checkInputDisplayed();
            checkDropdownDisplayed();
            checkLinksDisplayed();
            return true;
        }
        catch (NoSuchElementException exception) {
            exception.printStackTrace();
            return false;
        }

    }

    @Step("Email input")
    public void inputEmail(String email) {
        sendKeys(EMAIL_INPUT_LOCATOR, email);
    }

    @Step("Name input")
    public void inputName(String name) {
        sendKeys(NAME_INPUT_LOCATOR, name);
    }

    @Step("Phone number input")
    public void inputPhoneNumber(String phone) {
        sendKeys(PHONE_NUMBER_INPUT_LOCATOR, phone);
    }

    @Step("Clicking submit button")
    public void clickSubmit() {
        clickElement(SUBMIT_BUTTON_LOCATOR);
    }

    @Step("User details input")
    public void inputUserDetails(String name, String email, String phone) {
        inputName(name);
        inputEmail(email);
        inputPhoneNumber(phone);
        clickSubmit();
    }

    @Step("Handling alert")
    public void handleAlert() {
        acceptAlert();
    }

    @Step("Checking user data saved in table")
    public boolean checkUserDataInTable(String expectedName, String expectedEmail, String expectedPhone) {
        if (expectedName.equals(getElementText(USER_NAME_TABLE_LOCATOR)) &&
            expectedEmail.equals(getElementText(USER_EMAIL_TABLE_LOCATOR)) &&
                expectedPhone.equals(getElementText(USER_PHONE_TABLE_LOCATOR))) {
            return true;
        }
        else {
            String failedResult = expectedName.equals(getElementText(USER_NAME_TABLE_LOCATOR)) ?
                                (expectedEmail.equals(getElementText(USER_EMAIL_TABLE_LOCATOR)) ?
                                expectedPhone : expectedEmail) : expectedName;
            System.out.println("String: '" + failedResult + "' is not matching expected string ");
            return false;}
    }

    @Step("Check number of links on the page")
    public int checkNumberOfLinks(){
        List<WebElement> listOfLinks = driver.findElements(LINKS_LOCATOR);
        return listOfLinks.size();
    }

    @Step("Check link is presented on page")
    public boolean checkLinkPresented(String linkHref){
        return checkElementIsDisplayed(By.xpath("//a[contains(@href,'" + linkHref + "')]"));
    }

    @Step("Check chosen radio button")
    public List<Integer> checkRadioOption(String btnNumber) {
        clickElement(By.id(btnNumber));
        return separateNumbersFromString(getElementText(RADIO_OPTION_SELECTED_LOCATOR));
    }

    @Step("Check chosen checkboxes")
    public List<Integer> checkCheckboxOption() {
        clickElement(CHECKBOX_OPTION_ONE_LOCATOR);
        clickElement(CHECKBOX_OPTION_TWO_LOCATOR);
        clickElement(CHECKBOX_OPTION_THREE_LOCATOR);
        return separateNumbersFromString(getElementText(CHECKBOX_SELECTED_LOCATOR));
    }

    @Step("Check chosen dropdown")
    public List<Integer> checkDropdownOption(String dropdownOption) {
        clickElement(DROPDOWN_LOCATOR);
        clickElement(By.xpath("//select[@id='select-dropdown']/*[" + dropdownOption + "]"));
        return separateNumbersFromString(getElementText(DROPDOWN_SELECTED_LOCATOR));
    }

    @Step("Check current url")
    public boolean checkCurrentUrl(String expected) {
        return driver.getCurrentUrl().equals(expected);
    }

    @Step("Check all data was saved")
    public boolean checkAllDataSaved(String name, String email, String phone) {
        checkUserDataInTable(name, email, phone);
        if (separateNumbersFromString(getElementText(RADIO_OPTION_SELECTED_LOCATOR)).size() == 1 &&
        separateNumbersFromString(getElementText(CHECKBOX_SELECTED_LOCATOR)).size() == 3) {
            return true;
        }
        else return false;
    }
}
