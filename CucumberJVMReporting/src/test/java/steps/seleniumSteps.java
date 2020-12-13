package steps;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.utils.FileUtil;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

public class seleniumSteps {

	public static WebDriver driver;

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\SunithaS\\Desktop\\SeleniumCucumberSunitha\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
	}

	@After
	public void tearDown(Scenario scenario) throws IOException {
		// Screen shot on failure
		if (scenario.isFailed()) {
			//To embed screenshot in extent report with cucumber
			String scenarioName = scenario.getName();
			byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			/*
			 *Embeds data into the report(s). Some reporters (such as the progress one) don't embed data, but others do (html and json). 
			 *Refer the website and go to EMBED method section --> //https://www.javadoc.io/doc/info.cukes/cucumber-core/1.2.5/cucumber/api/Scenario.html#embed-byte:A-java.lang.String-
			*/
			scenario.embed(screenshotBytes, "image/png");
			scenario.write(scenarioName);
			
			//To put screenshot in a folder with scenario name and date format
			Date d = new Date();
			String date = d.toString().replace(":", "_").replace("", "_");
			File fileTemp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File filePerm = new File("C:\\Users\\SunithaS\\Desktop\\SeleniumCucumberSunitha\\FailedScreenshot"+scenarioName+"_"+date+".png");
			FileUtils.copyFile(fileTemp, filePerm);
			
			
			
		}

		if (driver != null) {
			//driver.quit();
		}

	}

	@Given("^I go to Sign up Registraion Form$")
	public void Sign_up_Registraion_Form() {
	    driver.get("http://elearningm1.upskills.in/main/auth/inscription.php");
	}

	@When("^I type for first name \"([^\"]*)\"$")
	public void firstName(String firstName) {
		driver.findElement(By.name("firstname")).sendKeys(firstName);
	}
	
	@And("^I type for last name \"([^\"]*)\"$")  
    public void lastName(String lastName) throws Throwable {  
	driver.findElement(By.name("lastname")).sendKeys(lastName);
    }
	
	@And("^I type for email \"([^\"]*)\"$")  
    public void email(String email) throws Throwable {  
	driver.findElement(By.name("email")).sendKeys(email);
    }
	@And("^I type for user name \"([^\"]*)\"$")  
    public void userName(String userName) throws Throwable {  
	driver.findElement(By.name("username")).sendKeys(userName);
    }
	@And("^I type for Pass \"([^\"]*)\"$")  
    public void password(String password) throws Throwable {  
	driver.findElement(By.name("pass1")).sendKeys(password);
    }
	@And("^I type for confirm Password \"([^\"]*)\"$")  
    public void confirmPassword(String confirmPass) throws Throwable {  
	driver.findElement(By.name("pass2")).sendKeys(confirmPass);
    }
	@When("^I click on Register search$")
	public void register() throws Throwable 
	{
	   driver.findElement(By.id("registration_submit")).click();   
	}
	
/*	@Then("Confirmation message should become \"([^\"]*)\"$")  
    public void ConfirmationMess(String confirmMessage) throws Throwable {  
		Thread.sleep(10000);
	String text=driver.findElement(By.xpath("//*[@id=\"cm-content\"]/div/div[2]/div/p[1]")).getText();
	System.out.println(text);
	Assert.assertEquals(confirmMessage,text);
    } */
	@And("^Click on menu drop down$")
	public void menuDrop() throws Throwable 
	{
		Thread.sleep(10000);
	   driver.findElement(By.xpath("//li[@id='count_message_li']/following::li[1]")).click();   
	}
	@Then("Email id should verified as \"([^\"]*)\"$")  
    public void emailVerifyDrop(String email) throws Throwable {  
	String text=driver.findElement(By.xpath("//div[@class=\"text-center\"]/p")).getText();
	System.out.println(text);
	Assert.assertEquals(email,text);
    }
	@And("^Click on compose option.$")
	public void compose() throws Throwable 
	{
	   driver.findElement(By.xpath("//a[@title='Inbox']")).click();   
	   driver.findElement(By.xpath("//img[@alt=\"Compose message\"]")).click();
	}
	@And("^I type for send to \"([^\"]*)\"$")  
    public void sendTo(String email) throws Throwable {  
	driver.findElement(By.className("select2-search__field")).sendKeys(email);
	driver.findElement(By.xpath("//li[text()='sunitha sreeramulu (sunithssree10)']")).click();
    }
	
	@And("^I type for Subject \"([^\"]*)\"$")
	public void subject(String subject){
	    driver.findElement(By.id("compose_message_title")).sendKeys(subject);
	}
	@And("^I type for Message \"([^\"]*)\"$")
	public void message(String subject){
	    driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='Rich Text Editor, content']")));
	    System.out.println("Entered into frame");
	    driver.findElement(By.xpath("//html/body[@contenteditable='true']")).click();
	    driver.findElement(By.xpath("//html/body[@contenteditable='true']")).sendKeys(subject);
	    driver.switchTo().defaultContent();
	}
	@And("^click on send message$")
	public void send(){
	    driver.findElement(By.id("compose_message_compose")).click();
	}
	@Then("^acknowledgement message should \"([^\"]*)\"$")
	public void acknowMessage(String ackMessage){
	    String text=driver.findElement(By.xpath("//div[@class=\"alert alert-success\"]")).getText();
	    System.out.println(text);
	    Assert.assertEquals(ackMessage, text);
	}
	
}
