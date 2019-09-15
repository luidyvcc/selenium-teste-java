package br.fib.seleniumTesteSalario;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import br.fib.seleniumTesteSalario.models.Usuario;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class salarioTeste {

	private Usuario joao;

	private WebDriver driver;

	@Before
	public void criarAmbiente() {

		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");

		this.joao = new Usuario("Joao da Silva", "1", "40", "15.00");

		this.driver = new ChromeDriver();
		driver.get("http://localhost:9098/");

	}

	@Test
	public void aDeveAdicionarUmFuncionario() {

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

		salaryHour.submit();

		boolean achouName = driver.getPageSource().contains(joao.getNome());

		assertTrue(achouName);

	}

	@Test
	public void bVerificaCadastroDoFuncionario() {

		boolean achouNome = this.driver.getPageSource().contains(this.joao.getNome());

		assertTrue(achouNome);
	}

	@Test
	public void cVerificaCalculoSalarioLiquido() {

		List<WebElement> trs = driver.findElements(By.cssSelector("tbody tr"));

		boolean satarioLiquidoCorreto = false;

		for (WebElement tr : trs) {

			List<WebElement> td = tr.findElements(By.tagName("td"));

			if (td.get(0).getText().equals(this.joao.getNome()) && td.get(4).getText().equals("R$ 562,25")) {

				satarioLiquidoCorreto = true;
				break;

			}
		}

		assertTrue(satarioLiquidoCorreto);
	}

	@Test
	public void dVerificaMensagemDeFaltaDePreenchimntoDeCampo() {

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

		salaryHour.submit();

		boolean achouMensagem = driver.getPageSource().contains("não pode estar vazio");

		assertTrue(achouMensagem);

	}

	@Test
	public void eVerificaExclusaoDoFuncionario() {

		List<WebElement> trs = driver.findElements(By.cssSelector("tbody tr"));

		for (WebElement tr : trs) {

			List<WebElement> td = tr.findElements(By.tagName("td"));

			if (td.get(0).getText().equals(this.joao.getNome())) {

				tr.findElement(By.cssSelector("td:nth-child(7) a")).click();
				break;

			}
		}

	}

	@After
	public void finalizaAmbiente() {
		driver.close();
	}

}
