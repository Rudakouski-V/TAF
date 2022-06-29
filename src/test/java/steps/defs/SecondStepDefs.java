package steps.defs;

import baseEntities.BaseCucumberTest;
import configuration.ReadProperties;
import io.cucumber.java.en.When;

public class SecondStepDefs extends BaseCucumberTest{
    private BaseCucumberTest baseCucumberTest;

    public SecondStepDefs(BaseCucumberTest baseCucumberTest) {
        this.baseCucumberTest = baseCucumberTest;
    }

    @When("loginpage is opened")
    public void loginpageIsOpened() {
        baseCucumberTest.driver.get(ReadProperties.getUrl());
    }
}
