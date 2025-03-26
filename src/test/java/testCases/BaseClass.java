package testCases;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager; // log4j2 
import org.apache.logging.log4j.Logger; // log4j2 
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;



public class BaseClass {
	
	
public static WebDriver driver;
public Logger logger = LogManager.getLogger(this.getClass());// log4j2 
public Properties p;
	
	@BeforeClass(groups= {"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public void setup(String os , String br) throws IOException {
		
//		Loading config.properties file
		FileReader file = new FileReader("src//test//resources//config.properties");
		p = new Properties();
		p.load(file);
		
		
//		Here we are passing browser name 
		switch(br.toLowerCase()) {			
		case "chrome" : driver = new ChromeDriver();break;
		case "firefox" : driver = new FirefoxDriver();break;
		case "edge" : driver = new EdgeDriver();break;
		default: System.out.print("Invalid browser name provided");return;
		}
		
//		Here we are opening the browser
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(p.getProperty("appURL")); // reading file from config properties
		driver.manage().window().maximize();
	}
	
//	Here we are generating random string and number for registration page
	public String randomString() {
		String generatedString  = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	public String randomNumber() {
		String generatedString  = RandomStringUtils.randomNumeric(10);
		return generatedString;
	}
	public String randomPassword() {
		String generatedString  = RandomStringUtils.randomAlphabetic(3);
		String generatednumber  = RandomStringUtils.randomNumeric(3);
		return (generatedString+generatednumber);
	}
	
	@AfterClass(groups= {"Sanity","Regression","Master"})
	public void TearDown() {
		driver.quit();
	}
	
	public String captureScreen(String tname) throws IOException {
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format (new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		return targetFilePath;
	}
}