package steps.defs;

import baseEntities.BaseCucumberTest;
import io.cucumber.java.en.Given;

public class MainStepDefs extends BaseCucumberTest {
    private BaseCucumberTest baseCucumberTest;

    public MainStepDefs(BaseCucumberTest baseCucumberTest) {
        this.baseCucumberTest = baseCucumberTest;
    }

    @Given("chrome is started")
    public void chromeIsStarted() {
        baseCucumberTest.driver.get("https://onliner.by");
    }
}
