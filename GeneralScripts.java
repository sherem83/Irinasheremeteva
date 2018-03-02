package Irina;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class GeneralScripts {

	/**
	 * Question 1:
	 * 
	 * Given a string, reverse each word in it and reverse the entire string.
	 * For example, if the given string is “hope you are doing well”, then the
	 * output should be “llew gniod era uoy epoh”. Assume that all characters in
	 * the string are lower case. Create a method called
	 * reverseEverything(String s) where ‘s’ is the input from the user in order
	 * to solve this question.
	 * 
	 * 
	 */
	public static void reverseEverything(String s) {

		System.out.println(String.valueOf(new StringBuffer(s).reverse()));
	}

	/**
	 * Use your knowledge of WebDriver to fetch the following: (a) Display the
	 * third and fifth item from the above list. Your output should be:
	 * Applesauce, unsweetened Juice, Cranberry-apple drink (b) Fetch each food
	 * name and its servings and store them in a Map. Iterate through all the
	 * entries in the Map and print them.
	 */


	public static void htmlTesting() {
		
		System.setProperty("webdriver.chrome.driver",
				"./src/test/java/Irina/chromedriver.exe");
        

		Path sampleFile = Paths.get("./src/test/java/Irina/index.html");
		WebDriver driver = new ChromeDriver();
		IndexPageModel home = new IndexPageModel(driver);
		driver.get(sampleFile.toUri().toString());
		String thirtElementText = home.thirdItem.getText();
		String fifthElementText = home.fifthItem.getText();

		System.out.println("Text of Third Element is: - " + thirtElementText);
		System.out.println("Text of Fifth Element is: - " + fifthElementText);

		List<WebElement> qtyOfFoods = driver.findElements(By.xpath("//*[@class='title ng-binding']"));
		Map<String, String> foodDescription = new HashMap<>();

		for (int i = 0; i < qtyOfFoods.size(); i++) {
			String key = qtyOfFoods.get(i).getText();
			String value = driver
					.findElement(By.xpath("//*[@class='title ng-binding'][" + (i + 1) + "]/following-sibling::*[1]"))
					.getText();
			foodDescription.put(key, value);
		}

		for (Map.Entry<String, String> entry : foodDescription.entrySet())
			System.out.println(entry.getKey() + " - " + entry.getValue());
		
		driver.quit();
	}

	/*
	 * Create a method called doesFileExist(String path) which takes the path of
	 * the file and tells the user if the file exists at that path or not.
	 * Assume all paths are relative to your project structure. If the file does
	 * not exist, catch the requisite exception.
	 */

	public static boolean doesFileExist(String path) {
		boolean exist = false;
		File file = new File(path);
		try {
			if (file.exists()) {
				exist = true;
			}
		} catch (Exception e) {
			e.fillInStackTrace();
		}
		return exist;
	}

	/*
	 * Read each word and its possible meanings and print them out. Your output
	 * should look like this:
	 */

	public static void readFile() {
		Path sampleFile = Paths.get("./src/test/java/Irina/words.txt");
		String path = sampleFile.toString();
		doesFileExist(path);
		
		try {
			File file = new File(path);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				
				stringBuffer.append(line.replace("–", "\n").replace(",", "\n").replaceFirst(" ", "").replaceFirst(" ", "").replaceFirst(" ", "").replaceFirst(" ", ""));
				stringBuffer.append("\n");
			}
			fileReader.close();
			System.out.println("Contents of file:");
			System.out.println(stringBuffer.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		System.out.println("************************* Start Testing *********************");
		
		System.out.println("\n \n ############ Reverse Method ##################");
		reverseEverything("hope you are doing well");
		
		
		System.out.println("\n \n ############ File Read ##################");
		readFile();
		
		System.out.println("\n \n ############ HTML READ ##################");
		htmlTesting();
	}
}
