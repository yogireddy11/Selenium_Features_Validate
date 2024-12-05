package Features;

import BasePack.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

public class Interactions_Dragable extends BaseClass {

   By drag = By.xpath("//a[@href=\"https://jqueryui.com/draggable/\"][1]");
   By dragBox =  By.xpath("//div[@id=\"draggable\"]");


    @Test(priority = 1)
    public void draggable() throws InterruptedException {
        WebElement dragElement = driver.findElement(drag);
        driverWait.until(ExpectedConditions.elementToBeClickable(dragElement)).click();

        // Switch to the iframe
        WebElement iframeElement = driver.findElement(By.cssSelector("iframe.demo-frame"));
        driver.switchTo().frame(iframeElement);

        WebElement dragBoxElement = driverWait.until(ExpectedConditions.visibilityOfElementLocated(dragBox));
        actions.dragAndDropBy(dragBoxElement,9,246).perform();
        Thread.sleep(3000);
        driver.switchTo().defaultContent();
    }
    @Test(priority = 2)
    public void droppable(){
        WebElement droppableLink = driver.findElement(By.xpath("//a[@href=\"https://jqueryui.com/droppable/\"]"));
        driverWait.until(ExpectedConditions.elementToBeClickable(droppableLink)).click();
        WebElement iframe = driver.findElement(By.className("demo-frame"));
        driver.switchTo().frame(iframe);
        WebElement dragMe =  driver.findElement(By.id("draggable"));
        WebElement dropMe = driver.findElement(By.id("droppable"));
        actions.clickAndHold().dragAndDrop(dragMe,dropMe).release().perform();
        driver.switchTo().defaultContent();
    }

    @Test(priority = 3)
    public void resizable() throws InterruptedException {
        WebElement resizable = driver.findElement(By.xpath("//a[@href=\"https://jqueryui.com/resizable/\"]"));
        resizable.click();
        WebElement iframe = driver.findElement(By.className("demo-frame"));
        driver.switchTo().frame(iframe);
        WebElement resize = driver.findElement(By.xpath("//*[@id=\"resizable\"]/div[3]"));
        WebElement boxSize = driver.findElement(By.id("resizable"));
        Dimension initialdimension = boxSize.getSize();
        System.out.println("Before resize height - "+initialdimension.getHeight()+", width - "+initialdimension.getWidth());
        actions.clickAndHold(resize).moveByOffset(100,80).release().perform();
        Dimension finalDimension = boxSize.getSize();
        System.out.println("After resize height - "+finalDimension.getHeight()+", width - "+finalDimension.getWidth());
        driver.switchTo().defaultContent();

    }
    @Test(priority = 4)
    public void selectable() throws InterruptedException, AWTException {
        WebElement selectablePage = driver.findElement(By.xpath("//a[@href=\"https://jqueryui.com/selectable/\"]"));
        selectablePage.click();
        WebElement iframe = driver.findElement(By.className("demo-frame"));
        driver.switchTo().frame(iframe);

        List<WebElement> listItems = driver.findElements(By.xpath("//li[@class=\"ui-widget-content ui-selectee\"]"));
        for (WebElement str : listItems){
            String strTxt = str.getText();
            System.out.println(strTxt);
        }
        actions.clickAndHold(listItems.getFirst()).moveToElement(listItems.get(listItems.size()-1)).release().perform();
        Thread.sleep(3000);
    }
    @Test
    public void sortable(){
        WebElement sortableLink = driver.findElement(By.xpath("//a[@href=\"https://jqueryui.com/sortable/\"]"));
        sortableLink.click();
        WebElement iframe = driver.findElement(By.className("demo-frame"));
        driver.switchTo().frame(iframe);

        List<WebElement> listItems = driver.findElements(By.xpath("//li[@class=\"ui-state-default ui-sortable-handle\"]"));
       for(int i=0;i<listItems.size()-1;i++){
           WebElement item1 = listItems.get(i);
           WebElement item2 = listItems.get(i+1);
           actions.clickAndHold(item1).moveToElement(item2).release().perform();
           System.out.println("Moved item " + (i + 1) + " to position " + (i + 2));

       }
    }
}
