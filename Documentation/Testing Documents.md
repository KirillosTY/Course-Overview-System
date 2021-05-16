# Testing document

This application has been tested with automatic and manual tests on a unit and integrationlevel.

## unit and Integration tests

 # Controls
 Most automated tests are applied in the cos.control package with tests ranging from the class itself
 to multiple class integration to simulate accurately the use of the application.
 
 ## Informationhandling
This class is tested with automated unit tests. It will create temporary files config.properties, settTest.bin and chTest.bin that will be 
removed after tests end. Note that if location contains a config.properties it will be restored to original after tests end instead of being removed.
 
 ## Test Coverage
 
 Current coverage is approximately 70%, mostly because simpler getters and setters have not been tested.
 
 ![TETS](admhe)
 
 The MainController class is not tested through automation.
 
 ## System Testing 
 
This application has been tested manually to accurately get the user experience. First by downloading the release, then using the user manual on how to operate, in both linux and windows. it has also been tested by trying to cause errors or feed unwanted values.


## Quality issues

Configurations have to be edited manually through the config.properties file and created by yourself, if there is a need for it.
