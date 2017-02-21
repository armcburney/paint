NAME = "Main"
JFLAGS = -cp .:$(JARS) -g -d .
JARS = jars/com.fasterxml.jackson.databind.jar:jars/com.fasterxml.jackson.core.jar:jars/com.fasterxml.jackson.annotations.jar

all:
	@echo "Compiling..."
	javac $(JFLAGS) src/main/java/*.java src/main/java/model/*.java src/main/java/view/*.java

run: all
	@echo "Running..."
	java -cp $(JARS):. ca.andrewmcburney.cs349.a2.Main

clean:
	rm -rf *.class
