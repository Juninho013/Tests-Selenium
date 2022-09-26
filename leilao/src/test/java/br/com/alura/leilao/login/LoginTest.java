package br.com.alura.leilao.login;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {

	private WebDriver browser;

	@BeforeAll
	public static void beforeAll() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

	}

	@BeforeEach
	public void beforeEach() {
		this.browser = new ChromeDriver();

	}

	@AfterEach
	public void afterEach() {
		this.browser.quit();
	}

	@Test
	public void deveriaEfeturarLoginComDadosValidos() {
		browser.navigate().to("http://localhost:8080/login");
		browser.findElement(By.id("username")).sendKeys("fulano");
		browser.findElement(By.id("password")).sendKeys("pass");
		browser.findElement(By.id("login-form")).submit();

		Assertions.assertFalse(browser.getCurrentUrl().equals("http://localhost:8080/login"));
		Assertions.assertEquals("fulano", browser.findElement(By.id("usuario-logado")).getText());
		browser.quit();

	}

	@Test
	public void naoDeveriaLogarComDadosInvalidos() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver browser = new ChromeDriver();
		browser.navigate().to("http://localhost:8080/login");
		browser.findElement(By.id("username")).sendKeys("cachoro");
		browser.findElement(By.id("password")).sendKeys("passto");
		browser.findElement(By.id("login-form")).submit();

		Assertions.assertTrue(browser.getCurrentUrl().equals("http://localhost:8080/login?error"));
		Assertions.assertTrue(browser.getPageSource().contains("Usuário e senha inválidos"));
		Assertions.assertThrows(NoSuchElementException.class,
				() -> browser.findElement(By.id("usuario-logado")).getText());
		browser.quit();

	}
}
