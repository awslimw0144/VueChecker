# VueChecker
This is solely for the purpose of demo-ing the potential of using Serenity.bdd to perform automation testing. http://todomvc.com/examples/vue/#/all is used as test site to perform the automated testing.

# Excel Sheet used as test data repository
I have attached the test data sheet in the resource folder, underneath test folder <br />
![Alt text](/src/main/resources/BS_DATA_Location.PNG?raw=true)

# Multiple test executions
If there are many test cases to perform, and that having to click on each one of the test run may be tedious and <br />
time-consuming, you may wish to create test suite as shown on the screenshot below.
![Alt text](/src/main/resources/RegressionSuit2e.PNG?raw=true)

# Generating Report
For best practices, do clear off the current test results by performing the following
  <br />[1] Go to terminal
  <br />[2] mvn -U clean package<br />
After testing is done, do generate the result by performing the following
  <br />[1] Go to terminal
  <br />[2] mvn verify serenity::aggregate<br />

# Viewing Report
After successfully executing 'mvn verify serenity::aggregate', the 'target' folder will be displayed.<br />
![Alt text](/src/main/resources/PostRegressionSuite.PNG?raw=true)

From 'target' folder, do navigate to the following folder<br />
Traverse : target/site/serenity/index.html<br />
![Alt text](/src/main/resources/PostRegressionSuite_2.PNG?raw=true)
![Alt text](/src/main/resources/PostRegressionSuite_3.PNG?raw=true)<br />
As navigating to this location may be tedious, I will usually create a shortcut.<br />

If there is anything questions, lets have a chat !!!<br />
My Email : awslimw0144@gmail.com
