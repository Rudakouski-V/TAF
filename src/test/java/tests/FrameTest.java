package tests;

import baseEntities.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FrameTest extends BaseTest {

    @Test
    public void frameTest1() {
        driver.get("https://the-internet.herokuapp.com/iframe");


        // ������������� � �������� �����:

        // 1. ���� ��� ���� �� ������� (� �� ������ �������), ���� �� ������������; ��������� - � ����
        driver.switchTo().frame(0);

        // 2. �� ����� ��� ID`�����
        //driver.switchTo().frame("mce_0_ifr");

        // 3. �� WebElement`y (��� iFrame)
        //driver.switchTo().frame(driver.findElement(By.cssSelector(".tox-edit-area__iframe")));


        Assert.assertTrue(driver.findElement(By.id("tinymce")).isDisplayed());


        // ������������� ������� � ������������ �����; ������������ - ���� �� ���� ������� ����
        driver.switchTo().parentFrame();

        // ������������ � ����� ������� ��������
        //driver.switchTo().defaultContent();


        Assert.assertTrue(driver.findElement(By.className("tox-editor-container")).isDisplayed());
    }
}
