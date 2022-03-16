package util;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotReport {

    public static String screenshot(WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        String dateFormat = new SimpleDateFormat("_yyyyMMdd_hhmmss").format(new Date());
        String img = "image"+dateFormat+".png";
        String path = "./screenshots/"+img;
        FileUtils.copyFile(src, new File("Report" + File.separator + "screenshots" + File.separator + img));
        return path;
    }

}
