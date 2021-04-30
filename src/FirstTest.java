import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.Collection;

public class FirstTest extends CoreTestCase {

    private MainPageObject MainPageObject;

    protected void setUp() throws Exception {
        super.setUp();
        MainPageObject = new MainPageObject(driver);
    }




    @Test
    public void testTextIsPresent() {

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find text Search Wikipedia",
                5
        );

        MainPageObject.assertElementHasText(
                By.id("org.wikipedia:id/search_src_text"),
                "Search…",
                "Cannot find text",
                5
        );
    }
    @Test
    public void testSearchText(){
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find text Search Wikipedia",
                5
        );
        MainPageObject.waitForElementAndSendKeys(
                By.xpath(".//*[contains(@text,'Search…')]"),
                "Swift",
                "Cannot find search input",
                5
        );


        MainPageObject.assertElementHasText(
                By.xpath("//*[contains(@text, 'Swift') and contains(@text, 'Swift (programming language)')]"),
                "Swift (programming language)",
                "Cannot find text",
                15
        );
    }
    @Test
    public void testTextInResultOfSearch() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Can`t find search Wikipedia input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Can`t find search input",
                10
        );

        MainPageObject.waitForElementPresent(
                By.xpath("//android.widget.TextView[contains(@text, 'Java')]"),
                "Element Not Present",
                5
        );

        Collection<WebElement> collection = (Collection<WebElement>) driver.findElements(By.xpath("//android.widget.TextView[contains(@text, 'Java')]"));

        for (WebElement value : collection) {
            String element = value.getText();
            String text = "JAVA";
            if (element.toUpperCase().contains(text)) {
                System.out.println("Success");
            } else {
                assertEquals(
                        "Text is not contains 'Java'",
                        text,
                        element
                );
            }
        }

    }

    




    @Test
    public void testSaveTwoArticlesToFolder() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find text Search Wikipedia",
                5
        );
        String search_line = "Java";
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_line,
                "Cannot find search input",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='JavaScript']"),
                "Cannot find 'JavaScript' title topic searching by " + search_line,
                15
        );
        MainPageObject.waitForElementPresent(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot wait More options button",
                15
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button More optional",
                15
        );

        String reading_list = "//*[@text='Add to reading list']";
        MainPageObject.waitForElementPresent(
                By.xpath(reading_list),
                "Cannot wait button article to reading list",
                15
        );

        MainPageObject.waitForElementAndClick(
                By.xpath(reading_list),
                "Cannot find optional to add article to reading list",
                15
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find GOT IT button",
                5
        );

        MainPageObject.waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find input to set name of article folder",
                5
        );

        String name_of_folder = "Learning programing";
        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Cannot put text to article folder input",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot press OK button",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/menu_page_search"),
                "Cannot find search button",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_line,
                "Cannot find search input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot  find 'Java (programming language)' title topic searching by " + search_line,
                15
        );

        MainPageObject.waitForElementPresent(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot wait More options button",
                30
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button More optional",
                15
        );

        MainPageObject.waitForElementPresent(
                By.xpath(reading_list),
                "Cannot wait button add article to reading list",
                15
        );

        MainPageObject.waitForElementAndClick(
                By.xpath(reading_list),
                "Cannot find button add article to reading list",
                15
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Learning programing']"),
                "Cannot find folder 'Learning programing' to add article to reading list",
                15
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article, X link",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find navigation button to my list",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/item_container"),
                "Cannot find created folder",
                5
        );

        MainPageObject.swipeElementToLeft(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find save article"
        );

        MainPageObject.assertElementHasText(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title']"),
                "JavaScript",
                "Cannot find text",
                15
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/page_list_item_container"),
                "Cannot find Java Script article in folder",
                5
        );

        String title = MainPageObject.waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of article",
                15
        );

        assertEquals(
                "Article title cannot find",
                "JavaScript",
                title
        );
    }

    @Test
    public void testAssertElementPresent()
    {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Can`t find search Wikipedia input",
                10
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Can`t find search input",
                10
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot  find 'Java (programming language)' title topic searching",
                15
        );

        String title_article = MainPageObject.waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Can`t find article title",
                5
        );

        assertTrue(
                "title not contain text 'Java (programming language)'",
                title_article.contains("Java (programming language)")
        );
    }
}
