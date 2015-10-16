package org.trader.crawler;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;


public class App  {
    public static void main(String[] args) throws ParseException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("phantomjs.binary.path", "C:\\phantomjs-2.0.0-windows\\bin\\phantomjs.exe");
        WebDriver driver = new PhantomJSDriver(caps);
        // 交易資訊 > 三大法人 > 查詢 > 區分各期貨契約 > 依日期
        driver.get("http://www.taifex.com.tw/chinese/3/7_12_3.asp");
        
        List<WebElement> rows = driver.findElements(By.xpath("//table[@class='table_f']//tr[td//text()[contains(., '外資')]]"));
        WebElement row = rows.get(0); //台指期貨
        List<WebElement> cells = row.findElements(By.tagName("td"));
        //多方交易口數
        final Number LTradeVol = NumberFormat.getInstance().parse(cells.get(1).getText());
        //多方交易金額
        final Number LTradeAmt = NumberFormat.getInstance().parse(cells.get(2).getText());
        //空方交易口數
        final Number STradeVol = NumberFormat.getInstance().parse(cells.get(3).getText());
        //空方交易金額
        final Number STradeAmt = NumberFormat.getInstance().parse(cells.get(4).getText());
        //多空淨額口數
        final Number DifTradeVol = NumberFormat.getInstance().parse(cells.get(5).getText());
        //多空淨額金額
        final Number DifTradeAmt = NumberFormat.getInstance().parse(cells.get(6).getText());
        
        //多方未平倉口數
        final Number LOIVol = NumberFormat.getInstance().parse(cells.get(7).getText());
        //多方未平倉金額
        final Number LOIAmt = NumberFormat.getInstance().parse(cells.get(8).getText());
        //空方未平倉口數
        final Number SOIVol = NumberFormat.getInstance().parse(cells.get(9).getText());
        //空方未平倉金額
        final Number SOIAmt = NumberFormat.getInstance().parse(cells.get(10).getText());
        //多空淨額口數
        final Number OIVol = NumberFormat.getInstance().parse(cells.get(11).getText());
        //多空淨額金額
        final Number OIAmt = NumberFormat.getInstance().parse(cells.get(12).getText());
        
        System.out.println("OIVol:" + OIVol + " OIAmt:" + OIAmt);
        driver.quit();
    }
}
