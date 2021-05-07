package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Collection;

public class SearchPageObject extends MainPageObject {

    private static final String
            SEARCH_INIT_ELEMENT = "//*[contains(@text,'Search Wikipedia')]",
            SEARCH_INPUT = "//*[contains(@text,'Search…')]",
            SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']",
            SEARCH_RESULT_ELEMENT = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']",
            SEARCH_EMPTY_RESULT_ELEMENT = "//*[@text='No results found']",
            FIND_TEXT_TPL = "//*[contains(@text, '{SUBSTRING}')]",
            TEXT_TEXTVIEW_BY_RESULT_TPL = "//android.widget.TextView[contains(@text, '{TEXT}')]";


    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    /* TEMPLATES  METHODS */
    private static String getResultSearchElement(String substring) {
       return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getSearchElementText(String searched_text) {
        return FIND_TEXT_TPL.replace("{SUBSTRING}", searched_text);
    }

    private static String getTextFromTextView(String searched_text) {
        return TEXT_TEXTVIEW_BY_RESULT_TPL.replace("{TEXT}", searched_text);
    }
     /* TEMPLATES  METHODS*/

    public void initSearchInput() {
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find and click search init element", 5);
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find search input after clicking init element");
    }

    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), search_line, "Cannot find and type into search input", 5);
    }

    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(By.id(SEARCH_CANCEL_BUTTON), "Cannot find cancel button", 5);
    }

    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(By.id(SEARCH_CANCEL_BUTTON), "Cancel button is still present", 5);
    }

    public void clickCancelSearch() {
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON), "Cancel find and click cancel button", 5);
    }

    public void waitForSearchResult(String substring) {

        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath), "Cannot find search result wits substring" + substring);
    }

    public void clickByArticleWithSubsrting(String substring) {

        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath), "Cannot find and click search result wits substring" + substring, 10);
    }

    public int getAmountOfFoundArticles() {
        this.waitForElementPresent(
                By.xpath(SEARCH_RESULT_ELEMENT),
                "Cannot find the request",
                30
        );
        return this.getAmountOfElements(By.xpath(SEARCH_RESULT_ELEMENT));
    }

    public void waitForEmptyResultsLabel() {
        this.waitForElementPresent(By.xpath(SEARCH_EMPTY_RESULT_ELEMENT), "Cannot find empty result element", 15);
    }

    public void assertThereIsNoResultOfSearch() {
        this.assertElementNotPresent(By.xpath(SEARCH_RESULT_ELEMENT), "We supposed not to find any results");
    }

    public void assertElementHasText(String searched_text, String assert_text) {
        this.assertElementHasText(
                By.xpath(getSearchElementText(searched_text)),
                assert_text,
                "We see unexpected text",
                15
        );
    }

    public void checkTextInSearchOfResult(String text) {

        Collection<WebElement> collection = (Collection<WebElement>) driver.findElements(By.xpath(getTextFromTextView(text)));
        for (WebElement value : collection) {
            String element = value.getText();
            if (element.toUpperCase().contains(text)) {
                System.out.println("Success");
            } else {
                Assert.assertEquals(
                        "Text is not contains " + text,
                        text,
                        element
                );
            }
        }
    }



}
