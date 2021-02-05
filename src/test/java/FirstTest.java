import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class FirstTest {

    @Test
    void checkSelenideSoftAssertionsHasExampleJUnit5() {
        //act
        open("https://github.com/selenide/selenide");
        $(byText("Wiki")).click();

        $$(".Box .Box-row a").find(text("SoftAssertions")).click();

        SelenideElement exampleJUnit = $(byText("Using JUnit5 extend test class:")).closest("ol").sibling(0);

        //assert
        exampleJUnit.shouldHave(
                text("@ExtendWith({SoftAssertsExtension.class})"),
                text("class Tests {"),
                text("@Test"),
                text("void test() {"),
                text("Configuration.assertionMode = SOFT;"),
                text("open(\"page.html\");"),
                text("$(\"#first\").should(visible).click();"),
                text("$(\"#second\").should(visible).click();"),
                text("}\n}")
        );
        sleep(5000);
    }
}
