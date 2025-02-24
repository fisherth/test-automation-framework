# test-automation-framework
Test Automation Framework written in Java using Cucumber for BDD and Selenium for tests that need to run in a browser.

## Features

### Behavior Driven Development (BDD), the Gherkin language and Cucumber
Behavior Driven Development (BDD) is an Agile software development technique that uses plain readable descriptions of software user requirements as the base of the software tests.  BDD allows all stakeholders involved in the development of the software user requirement to understand the requirement workflow, entities, events, acceptance criteria and outputs of a story.  In BDD, a story is written in a certain format and plain language (Gherkin - Given/When/Then Format) allowing all stakeholders to understand the user requirement. A tester would then use the Gherkin workflow as the main test scenario to test the story (happy path) and then determine other tests needed (functional, negative, integration, etc.) based on the story and it's acceptance criteria.  Then to automate the tests the Gherkin language can be easily ingested into the test automation framework using the Cucumber software to create a Feature File which contains the test(s) in plain English and is linked to a Steps File which contains the code to execute the test in the Feature File. Then Selenium and Java would be used to code the steps to create the automated test.  

### Page Objects
Page Object Model (POM) is a design pattern/framework that is used when using Selenium as part of a test automation framework.  It's a way organize test automation code creating an object repository of the different web elements across the Application Under Test (AUT). In the Page Object Model framework, a class file for each web page is created. This class file will consist of different web elements and methods present on the web page. The test scripts then use the web elements and methods to perform different actions on the AUT. With each page’s web elements and methods in a separate class file the code becomes easier to maintain and reduces code duplication. com.notsruht.taf.ui.pages.BasePageObject is an abstract class that is meant to be extended. It contains helper methods to select web elements and upload files so the Selenium API does not need to be called directly.

### Properties
The Test Automation Framework is highly configurable through the use of properties. There are many properties the framework checks for by default and testers can also supply their own. All properties can be defined in any number of properties files on the classpath, specified as a comma separated list in the testautomationframework.propertiesFiles system property. If that is not provided, then then framework defaults to reading properties from the testautomationframework.properties file on the classpath.  Properties can also be read from the System Properties, which take precedence over any properties defined in files. Configuration properties can be provided for the following:
- Browser used to run tests 
- Passing start up parameters to browsers (headless, window size, etc)
- Environment to run tests in
- Database connection pool configuration
- Application login information

### WebDriver Management
By default, The Test Automation Framework uses Selenium Manager keep the WebDriver versions in sync with the browser versions installed on your machine by automatically downloading them.
This behavior can be overridden by providing testautomationframework.webdriver.path.<browser> property values in testautomationframework.properties or system properties.

### Dependency Injection
Dependency Injection is a technique where an object's dependencies are provided by an "injector" that is responsible for creation, lifecycle management, and distribution of objects. In the Test Automation Framework, this functionality is provided by the Spring Framework (https://spring.io/projects/spring-framework). Its configuration is contained in the classes in the gov.nih.era.taf.config package.
In the Test Automation Framework, dependency injection is used to safely share state between the steps in a scenario. For example, the framework controls the lifecycle of the WebDriver in each scenario. Testers will not need to create new instances of WebDriver or quit the WebDriver after each scenario. The framework will do this. Testers can also create their own objects, like Page Objects, to be managed by the framework to ensure there are no issues with object state.

### Database
The com.notsruht.taf.db.TAFDatasource class represents a relational database that can be used in the framework as a Spring bean. Configuration properties are defined in testautomationframework.properties.

### Screenshots
The com.notsruht.taf.ui.ScreenshotUtil class has a takeScreenshot method can be used to capture a screen shot and embed it in the Cucumber report as needed. 
Any scenario with an "@ui" tag will place at least one screenshot in the report (see com.notsruht.taf.hooks.UIScenarioHooks). Through a Test Automation Framework property, testers can choose to either generate a screenshot for each step in the scenario or only for a failed step.
- To generate a screenshot after a failed step, set testautomationframework.screenshot.eachstep = false
- To generate a screenshot after each step, set testautomationframework.screenshot.eachstep = true
