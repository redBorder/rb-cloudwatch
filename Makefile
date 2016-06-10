
package:
	mvn clean package

rpm: 
	$(MAKE) -C packaging/rpm
