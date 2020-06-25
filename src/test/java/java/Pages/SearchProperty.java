package java.Pages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Base.Base1;
import Utils.TestUtils;

public class SearchProperty extends Base1{
	
	String ele;
	String price;
	 ArrayList<String> arlist= new ArrayList<>();

	@FindBy(xpath = "//button[@type='submit']//following-sibling::button")
	WebElement submitcookie;
	
	
	
	@FindBy(xpath = "//input[@name='q']")
	WebElement searchtextbox;
	
	
	@FindBy(id ="search-submit")
	WebElement searchbutton;
	
	@FindBy(xpath ="(//div[@class='ui-agent__text']/h4)[1]")
	WebElement PropertyAgentname;
	
	@FindBy(xpath ="(//*[@class='agent_logo']//following-sibling::a)")
	List<WebElement> list;
	
	@FindBy(xpath ="((//h1[@class='bottom-half']/b)[1])")
	public static WebElement AgentName;
	
	
	
	
	public SearchProperty()
	{
		
		PageFactory.initElements(driver, this);
		
	}
	
	public void search() 
	{
		
		submitcookie.click();
		searchtextbox.sendKeys(prop.getProperty("Country"));
		//driver.manage().timeouts().implicitlyWait(TestUtils.IMPLICIT_WAIT, TimeUnit.SECONDS);
		//searchtextbox.click();
		searchbutton.click();
		
		
		
	}
	
	public ArrayList<String> propertyPrice()
	{
		
		//List<WebElement> list =driver.findElements(By.xpath("(//*[@class='agent_logo']//following-sibling::a)"));
		for(int i=1; i<list.size();i++)
		{
			ele = driver.findElement(By.xpath("(//*[@class='agent_logo']//following-sibling::a)["+i+"]")).getText();

			 
			if(ele.contains(" "))
			{
				 price = ele.substring(0, ele.indexOf(" "));
			}
			else
			{
				price=ele;
			}
			price=price.replaceAll("£", "").replaceAll(",", "");
			System.out.print(price +",");
			arlist.add(price);		}
		     
		    return arlist;
		
	

}
	
	public String fifthProperty()
	{
		//List<WebElement> list =driver.findElements(By.xpath("(//*[@class='agent_logo']//following-sibling::a)"));
		for(int i=1; i<list.size();i++)
		{
			if(i==5)
			{
				driver.findElement(By.xpath("(//*[@class='agent_logo']//following-sibling::a)["+i+"]")).click();
				break;
			}
			
			
			
		}
		String actual=PropertyAgentname.getText();
		System.out.println(actual);
		
		PropertyAgentname.click();
		return actual;
		
		
		
		
	}
	
	

}
