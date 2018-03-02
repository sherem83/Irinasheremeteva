package Irina;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IndexPageModel {
	
	public IndexPageModel(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
	@FindBy (className = "title ng-binding")
	public WebElement allFood;
	
	@FindBy (xpath = "//*[@class='title ng-binding'][3]")
	public WebElement thirdItem;
	
	@FindBy (xpath = "//*[@class='title ng-binding'][5]")
	public WebElement fifthItem;

}
