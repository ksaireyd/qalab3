package tests;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.w3c.dom.Document;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import pages.HomePage;
import pages.LoginPage;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;

public class BaseTest {


    private WebDriver driver;

    public BaseTest() throws IOException, SAXException, ParserConfigurationException {
    }


    @BeforeTest
    public void profileSetUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");

    }

    @BeforeMethod
    public void testSetUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://accounts.google.com/signin/v2/identifier?continue=https%3A%2F%2Fmail.google.com");
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }

    public WebDriver getDriver(){return driver;}
    public HomePage getHomePage() throws IOException {
        return new HomePage(getDriver());
    }
    public LoginPage getLoginPage() throws IOException {return  new LoginPage(getDriver()) ;}


    private String filepath = "src/main/resources/data.xml";

    private Element getList() throws ParserConfigurationException, IOException, SAXException {
        File xmlFile = new File(filepath);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(xmlFile);
        doc.getDocumentElement().normalize();
        return  doc.getDocumentElement();

    }
    private Element nodeList =  getList();
    public String getNode(String nodeName){
        return nodeList.getElementsByTagName(nodeName).item(0).getTextContent();
    }


}
