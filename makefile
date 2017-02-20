NAME = "Main"
JFLAGS = -cp .:$(JARS) -g -d .
JARS = jars/com.fasterxml.jackson.databind.jar:jars/com.fasterxml.jackson.core.jar:jars/com.fasterxml.jackson.annotations.jar

all:
	@echo "Compiling..."
	javac $(JFLAGS) *.java view/*.java model/*.java

run: all
	@echo "Running..."
	java -cp $(JARS):. $(NAME)

clean:
	rm -rf *.class
