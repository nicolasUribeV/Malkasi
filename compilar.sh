cd malkasi-ejb
mvn clean install
cd ..
cd malkasi-web
mvn install -DskipTests
cd ..
cd malkasi-ear
mvn clean install
cd ..
