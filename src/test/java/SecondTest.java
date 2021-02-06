import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Point;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class SecondTest {
    @Test
    void dragAndDropTest() {
        open("https://the-internet.herokuapp.com/drag_and_drop");
        //arrange
        SelenideElement cubeA = $("div#column-a").shouldHave(text("A"));
        SelenideElement cubeB = $("div#column-b").shouldHave(text("B"));

        //act
        cubeA.dragAndDropTo(cubeB);

        //assert
        cubeA.shouldHave(text("B"));
        cubeB.shouldHave(text("A"));
    }

    @Test
    void actionsMoveToElement() {
        //arrange
        open("https://the-internet.herokuapp.com/drag_and_drop");
        $("#column-a").shouldHave(text("A"));
        $("#column-b").shouldHave(text("B"));

        SelenideElement cubeA = $$("header").find(text("A"));
        SelenideElement cubeB = $$("header").find(text("B"));

        //act
        actions().moveToElement(cubeA).clickAndHold().moveToElement(cubeB).release().perform();

        //assert
        $("#column-a").shouldHave(text("B"));
        $("#column-b").shouldHave(text("A"));
    }

    @Test
    void actionsMoveByOffset() {
        //arrange
        open("https://the-internet.herokuapp.com/drag_and_drop");
        $("#column-a").shouldHave(text("A"));
        $("#column-b").shouldHave(text("B"));

        //act
        Point pointA = $$("header").find(text("A")).getWrappedElement().getLocation();
        Point pointB = $$("header").find(text("B")).getWrappedElement().getLocation();
        int xOffset = pointB.x - pointA.x;
        int yOffset = pointB.y - pointA.y;

        $$("header").find(text("A")).hover();
        actions()
                .clickAndHold()
                .moveByOffset(xOffset,yOffset)
                .perform();

        //assert
        $("#column-a").shouldHave(text("B"));
        $("#column-b").shouldHave(text("A"));
    }
}