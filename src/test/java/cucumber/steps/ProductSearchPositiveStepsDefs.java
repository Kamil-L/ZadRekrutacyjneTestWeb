package cucumber.steps;

import cucumber.DriverFactory;
import cucumber.config.ConfigReader;
import cucumber.models.Product;
import cucumber.pages.basket.BasketMainPage;
import cucumber.pages.basket.BasketSummaryPage;
import cucumber.pages.grid.ProductGridPage;
import cucumber.pages.home.HomePage;
import cucumber.pages.home.TopMenuPage;
import cucumber.pages.products.ProductSideBarPage;
import cucumber.pages.products.ProductViewPage;
import cucumber.utils.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public class ProductSearchPositiveStepsDefs {

    private static final Logger log = LoggerFactory.getLogger(ProductSearchPositiveStepsDefs.class);

    private WebDriver driver = DriverFactory.getDriver();
    private HomePage homePage;
    private TopMenuPage topMenuPage;
    private ProductGridPage productGridPage;
    private ProductViewPage productViewPage;
    private ProductSideBarPage productSideBarPage;
    private BasketMainPage basketMainPage;
    private BasketSummaryPage basketSummaryPage;
    private TestContext testContext;

    public ProductSearchPositiveStepsDefs(TestContext testContext) {
        this.testContext = testContext;
    }

    @Given("Home page is opened")
    public void homePageIsOpened() {
        homePage = new HomePage(driver)
                .goToHomePage()
                .acceptAllCookies();
    }

    @When("I click Urządzenia dropdown button in top menu")
    public void iClickUrządzeniaDropdownButtonInTopMenu() {
        topMenuPage = new TopMenuPage(driver);
        topMenuPage.clickProductsBtn();
    }

    @Then("I see submenu dropdown list")
    public void iSeeSubmenuDropdownList() {
        Assertions.assertThat(topMenuPage.isSubMenuDropdownDisplayed()).isTrue();
    }

    @And("I click on Smartfony under Bez abonamentu")
    public void iClickOnSmartfonyUnderBezAbonamentu() {
        topMenuPage.clickWithoutAboSmartphonesBtn();
    }

    @Then("I see product grid with smartphones")
    public void iSeeProductGridWithSmartphones() throws InterruptedException {
        productGridPage = new ProductGridPage(driver);
        boolean isProductGridDisplayed = productGridPage.isProductsGridDisplayed();

        Assertions.assertThat(isProductGridDisplayed).isTrue();
    }

    @And("I click on the first product")
    public void iClickOnTheFirstProduct() throws InterruptedException {
        productGridPage = new ProductGridPage(driver);
        productGridPage.clickProductFirstCard();
    }

    @And("I add product to the basket")
    public void iAddProductToTheBasket() {
        productViewPage = new ProductViewPage(driver);
        String productViewName = productViewPage.getProductName().getText();

        productSideBarPage = new ProductSideBarPage(driver);

        String initialPaymentProductViewBuild = productSideBarPage.getInitialPaymentPrice().getText()
                .stripTrailing().replaceAll("[^\\d]", "");
        BigDecimal initialPaymentProductView = BigDecimal.valueOf(Long.parseLong(initialPaymentProductViewBuild));

        String monthlyWithDiscountPaymentProductViewBuild = productSideBarPage.getMonthlyPaymentPrice().getText()
                .stripTrailing().replaceAll("[^\\d]", "");
        BigDecimal monthlyWithDiscountPaymentProductView = BigDecimal.valueOf(Long.parseLong(monthlyWithDiscountPaymentProductViewBuild));

        Product productView = new Product(productViewName, initialPaymentProductView, monthlyWithDiscountPaymentProductView);

        testContext.setProduct(productView);

        productSideBarPage.clickAddToBasketBtn();
    }

    @Then("I see basket main page")
    public void iSeeBasketMainPage() {
        basketMainPage = new BasketMainPage(driver);

        boolean isBasketMainPageDisplayed = basketMainPage.isBasketMainPageDisplayed();

        basketSummaryPage = new BasketSummaryPage(driver);
        boolean isBasketSummaryPageDisplayed = basketSummaryPage.isBasketSummaryPageDisplayed();

        Assertions.assertThat(isBasketMainPageDisplayed).isTrue();
        Assertions.assertThat(isBasketSummaryPageDisplayed).isTrue();
    }

    @And("I see correct prices of chosen product in the basket")
    public void iSeeCorrectPricesOfChosenProductInTheBasket() {
        basketMainPage = new BasketMainPage(driver);
        String productBasketName = basketMainPage.getProductName().getText();

        basketSummaryPage = new BasketSummaryPage(driver);
        String initialPaymentBasketBuild = basketSummaryPage.getInitialPaymentTotalValue().getText();
        BigDecimal initialPaymentBasketSummary = BigDecimal.valueOf(Long.parseLong(initialPaymentBasketBuild));


        String monthlyWithDiscountPaymentBasketSummaryBuild = basketSummaryPage.getMonthlyPaymentTotalValue().getText();
        BigDecimal monthlyWithDiscountPaymentBasketSummary = BigDecimal.valueOf(Long.parseLong(monthlyWithDiscountPaymentBasketSummaryBuild));

        Product productBasketPage = new Product(productBasketName, initialPaymentBasketSummary, monthlyWithDiscountPaymentBasketSummary);

        Product productView = testContext.getProduct();

        Assertions.assertThat(productBasketPage).isEqualToComparingFieldByFieldRecursively(productView);
    }

    @And("I go back to the home page")
    public void iGoBackToTheHomePage() {
        driver.navigate().to(ConfigReader.getProperty("appUrl"));
    }

    @Then("I see in the basket icon one product added")
    public void iSeeInTheBasketIconOneProductAdded() {
        topMenuPage = new TopMenuPage(driver);
        boolean isMenuBasketIconDisplayed = topMenuPage.isMenuBasketIconDisplayed();

        Assertions.assertThat(isMenuBasketIconDisplayed).isTrue();
        Assertions.assertThat(topMenuPage.getMenuBasketIconCounter().getText()).isEqualTo("1");
    }

    @Then("I see product view page")
    public void iSeeProductViewPage() {
        productViewPage = new ProductViewPage(driver);
        boolean isProductViewDisplayed = productViewPage.isProductsViewDisplayed();

        Assertions.assertThat(isProductViewDisplayed).isTrue();
    }
}