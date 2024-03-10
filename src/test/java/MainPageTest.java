import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static constants.constant.Urls.MAIN_PAGE;

@DisplayName("Test Page Test Suite")
@Nested
public class MainPageTest extends BaseTest {

    @Test
    @DisplayName("Title Test")
    public void checkTitle(){
        String title = "Main page";
        Assertions.assertEquals(title, mainPage.checkTitle());
    }

    @Test
    @DisplayName("All Elements Displayed Test")
    public void checkAllElementsDisplayed() {
        Assertions.assertTrue(mainPage.findAllElements());
    }

    @Test
    @DisplayName("Number Of Links Test")
    public void checkNumberOfLinks() {
        Assertions.assertEquals(4, mainPage.checkNumberOfLinks());
    }

    @ParameterizedTest
    @ValueSource(strings = {"link1", "LINK1", "link2", "LINK2"})
    @DisplayName("Links By Link Text")
    public void checkLinkByLinkText(String linkHref) {
        Assertions.assertTrue(mainPage.checkLinkPresented(linkHref));
    }

    @ParameterizedTest
    @CsvSource({
            "1, radio1",
            "3, radio3"
    })
    @DisplayName("Radio Button Chosen Test")
    public void checkChosenRadioButton(String expectedNumber, String buttonNumber) {
        List<Integer> expectedChosenButtons = new ArrayList<>();
        expectedChosenButtons.add(Integer.valueOf(expectedNumber));
        Assertions.assertEquals(expectedChosenButtons, mainPage.checkRadioOption(buttonNumber));
    }

    @Test
    @DisplayName("Chosen Checkboxes Test")
    public void checkChosenCheckboxes() {
        List<Integer> expectedActiveCheckboxes = Arrays.asList(1, 2, 3);
        Assertions.assertEquals( mainPage.checkCheckboxOption(), expectedActiveCheckboxes);
    }

    @Test
    @DisplayName("Chosen Dropdown Option Test")
    public void checkChosenDropdownValue() {
        List<Integer> expectedActiveCheckboxes = List.of(2);
        Assertions.assertEquals(expectedActiveCheckboxes, mainPage.checkDropdownOption("2"));

    }

    @Test
    @DisplayName("Input Fields Test")
    public void checkInputFields() {
        String name = "User";
        String email = "test@test.mail";
        String phone = "+99112345678";
        mainPage.inputUserDetails(name, email, phone);
        mainPage.handleAlert();
        Assertions.assertTrue(mainPage.checkUserDataInTable(name, email, phone), "User data is not equal to expected");
    }

    @Test
    @DisplayName("URL Opened And Data Saved Test")
    public void checkAllDataSavedAndUrlOpened(){
        String name = "User";
        String email = "test@test.mail";
        String phone = "+99112345678";
        mainPage.checkCurrentUrl(MAIN_PAGE);
        checkChosenDropdownValue();
        Assertions.assertTrue(mainPage.checkAllDataSaved(name, email, phone));

    }
}
