package javapackage1;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;		
	
public class packagejava {
	 @Test
		public void test() throws IOException, InterruptedException {
			 System.setProperty("webdriver.chrome.driver" ,"C:\\Users\\gamanesonali.sukha\\Desktop\\chromedriver.exe");
			 WebDriver wd=new ChromeDriver();
			 wd.get(" https://www.myhcl.com/bprhome/Home/Index");
		     wd.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			 System.out.println("Browser Launched");
			wd.findElement(By.xpath("//input[@type='email']")).sendKeys("gamanesonali.sukha@hcl.com");
			wd.findElement(By.id("idSIButton9")).click();
			System.out.println("Username Entered");
					
			wd.findElement(By.cssSelector("input[type='password']")).sendKeys("Shree@123");
			wd.findElement(By.xpath(
						"/html/body/div/form[1]/div/div/div[2]/div[1]/div/div/div/div/div/div[3]/div/div[2]/div/div[3]/div[2]/div/div/div/div/input"))
						.click();
			System.out.println("Password Entered ");
			Thread.sleep(9000);
			wd.findElement(By.xpath(
						"/html/body/div/form/div/div/div[2]/div[1]/div/div/div/div/div/div/div[1]/div[2]/div/div[2]/div/div[3]/div[2]/div/div/div[2]/input"))
						.click();
			System.out.println("Clicked on Sign In");
		File file1=new File("C:\\Users\\gamanesonali.sukha\\Desktop\\Log4j_report\\cookies.data");
		try {
		file1.createNewFile();
		FileWriter fw=new FileWriter(file1);
		BufferedWriter bw=new BufferedWriter(fw);
		for(Cookie ck:wd.manage().getCookies()) {
		bw.write(("1]getName() \n"+ck.getName()+"2]getDomain() \n"+ck.getDomain()+"3]getPath() \n"+ck.getPath()+"4]getExpiry() \n"+ck.getExpiry()+"5]isSecure()  \n"+ck.isSecure()+"6]getValue() \n"+ck.getValue()));
		bw.newLine();
		 
		}
		bw.close();
		fw.close();
		}
		catch(Exception e) {
		System.out.println(e);
		}}}