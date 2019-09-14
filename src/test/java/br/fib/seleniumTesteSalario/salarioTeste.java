package br.fib.seleniumTesteSalario;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import br.fib.seleniumTesteSalario.models.Usuario;

public class salarioTeste {

	private Usuario joao;

	private WebDriver driver;

	@Before
	public void criarAmbiente() {

		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");

		this.joao = new Usuario("Joao da Silva", "1", "40", "15.00");

		this.driver = new ChromeDriver();
		driver.get("http://192.168.5.115:9098");

	}

	@Test
	public void deveAdicionarUmFuncionario() {

		WebElement buttonNovo = driver.findElement(By.cssSelector("div.navbar-menu div.navbar-start a"));
		buttonNovo.click();

		WebElement name = driver.findElement(By.name("name"));
		WebElement dependents = driver.findElement(By.name("dependents"));
		WebElement timeToWork = driver.findElement(By.name("timeToWork"));
		WebElement salaryHour = driver.findElement(By.name("salaryHour"));

		name.clear();
		name.sendKeys(this.joao.getNome());

		dependents.clear();
		dependents.sendKeys(this.joao.getDependentes());

		timeToWork.clear();
		timeToWork.sendKeys(this.joao.getHoraTrabalhada());

		salaryHour.clear();
		salaryHour.sendKeys(this.joao.getSalarioHora());

		WebElement botaoSalvar = driver.findElement(By.id("salvar"));
		botaoSalvar.click();

		boolean achouName = driver.getPageSource().contains(joao.getNome());
		boolean achouDependents = driver.getPageSource().contains(this.joao.getDependentes());
		boolean achouTimeToWork = driver.getPageSource().contains(this.joao.getHoraTrabalhada());
		boolean achouSalaryHour = driver.getPageSource().contains(this.joao.getSalarioHora());

		assertTrue(achouName);
		assertTrue(achouDependents);
		assertTrue(achouTimeToWork);
		assertTrue(achouSalaryHour);

	}

	@Test
	public void verificaCadastroDoFuncionario() {
		boolean achouNome = this.driver.getPageSource().contains(this.joao.getNome());

		assertTrue(achouNome);
	}

	@Test
	public void verificaMensagemDeFaltaDePreenchimntoDeCampo() {

		WebElement buttonNovo = driver.findElement(By.cssSelector("div.navbar-menu div.navbar-start a"));
		buttonNovo.click();

		WebElement name = driver.findElement(By.name("name"));
		WebElement dependents = driver.findElement(By.name("dependents"));
		WebElement timeToWork = driver.findElement(By.name("timeToWork"));
		WebElement salaryHour = driver.findElement(By.name("salaryHour"));

		name.clear();

		dependents.clear();
		dependents.sendKeys(this.joao.getDependentes());

		timeToWork.clear();
		timeToWork.sendKeys(this.joao.getHoraTrabalhada());

		salaryHour.clear();
		salaryHour.sendKeys(this.joao.getSalarioHora());

		WebElement botaoSalvar = driver.findElement(By.id("salvar"));
		botaoSalvar.click();

		boolean achouMensagem = driver.getPageSource().contains("não pode estar vazio");

		assertTrue(achouMensagem);

	}

	@After
	public void finalizaAmbiente() {
		driver.close();
	}

}
