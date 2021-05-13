# Testing document

This application has been tested with automatic and manual tests on a unit and integrationlevel.

## unit and Integration tests

 #Controls
 Most automated tests are applied in the cos.control package with tests ranging from the class itself
 ranging to multiple class integration to simulate accurately the use of the application.
 
 # Informationhandling
 This class is tested with automated unit tests. It will create temporary files config.properties, settTest.bin and chTest.bin that will be 
removed after tests end. Note that if location contains a config.properties it will be restored to original after tests end instead of being removed.
 
