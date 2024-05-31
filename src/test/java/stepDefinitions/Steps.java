package stepDefinitions;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Steps {

	WebDriver driver;
	String url = "file:///C:/Users/Ramak1501278/OneDrive%20-%20Mastek%20Limited/Desktop/UK_MBE_09302006.pdf";
	PDDocument pdDocument;
	@Given("launch pdf")
	public void launch_pdf() {
		//ChromeOptions co = new ChromeOptions();
		//co.setHeadless(true);
		//co.addArguments("--incognito");
		//driver = new ChromeDriver(co);
		driver = new ChromeDriver();
		driver.get(url);
	}

	@When("pdf filed get opened")
	public void pdf_filed_get_opened() throws Exception {
		URL pdfURL = new URL(url);
		InputStream openStream = pdfURL.openStream();
		BufferedInputStream bf = new BufferedInputStream(openStream);
		pdDocument = PDDocument.load(bf);
		int numberOfPages = pdDocument.getNumberOfPages();
		System.out.println("There are "+numberOfPages+" number of pages in the PDF");
		Assert.assertEquals(numberOfPages,2);
	}

	@Then("verify it contains correct informations")
	public void verify_it_contains_correct_informations() throws Exception {

		PDFTextStripper textStripper = new PDFTextStripper();
		String text = textStripper.getText(pdDocument);
		System.out.println(text);
		Assert.assertTrue(text.contains("FORTENAY HOUSE, 14A ONGAR ROAD"));
		Assert.assertTrue(text.contains("0117 987 4217"));
		Assert.assertTrue(text.contains("CV1 1ED"));
		Assert.assertTrue(text.contains("YORK HOUSE, 20 SHOPLATCH"));
		Assert.assertTrue(text.contains("WOLVERHAMPTON"));
		
		textStripper.setStartPage(1);
		String text1 = textStripper.getText(pdDocument);
		System.out.println(text1);
	}
}
