package main.java;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumMain {

	public static void main(String[] args) {
		//드라이버 위치 설정
		System.setProperty("webdriver.chrome.driver",
				"/Users/soo/Downloads/chromedriver");

		/*
		//브라우저를 출력하지 않고 가져오기
		ChromeOptions options = new ChromeOptions();
		options.addArguments("headless");
		
		//드라이버 로드
		WebDriver driver = new ChromeDriver(options);
		*/
		
		WebDriver driver = new ChromeDriver();
		
		//사이트 접속
		driver.get("https://nid.naver.com/nidlogin.login");
		
		//자바스크립트 실행
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript(
				"document.getElementsByName('id')[0].value=\'" 
				+ "anythdus" +"\'");
		js.executeScript(
				"document.getElementsByName('pw')[0].value=\'" 
				+ "bbni.4130!" +"\'");
		
		//로그인 버튼 클릭
		driver.findElement(
				By.xpath("//*[@id=\"log.login\"]")).click();
		driver.get("https://cafe.naver.com/joonggonara");
		
		//소스 코드 가져오기
		System.out.println(driver.getPageSource());

	}

}
