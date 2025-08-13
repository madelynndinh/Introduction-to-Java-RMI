# Makefile for Java RMI Calculator Project

# Java settings
JAVA_SRC = src/main/java
JAVA_CLASSES = target/classes
PACKAGE = org/example
MAIN_PACKAGE = org.example

# Compilation target
compile:
	@echo "Compiling Java sources..."
	@mkdir -p $(JAVA_CLASSES)
	javac -cp $(JAVA_SRC) -d $(JAVA_CLASSES) $(JAVA_SRC)/$(PACKAGE)/*.java
	@echo "Compilation complete."

# Run the RMI server
run-server: compile
	@echo "Starting RMI Calculator Server..."
	java -cp $(JAVA_CLASSES) $(MAIN_PACKAGE).CalculatorServer

# Run the client (server must be running first)
run-client: compile
	@echo "Running Calculator Client..."
	java -cp $(JAVA_CLASSES) $(MAIN_PACKAGE).CalculatorClient

# Clean compiled classes
clean:
	@echo "Cleaning compiled classes..."
	rm -rf $(JAVA_CLASSES)
	@echo "Clean complete."

# Run tests (compile and run client)
test: compile run-client

# Help target
help:
	@echo "Available targets:"
	@echo "  compile     - Compile Java source files"
	@echo "  run-server  - Start the RMI Calculator Server"
	@echo "  run-client  - Run the Calculator Client (requires server running)"
	@echo "  test        - Compile and run client tests"
	@echo "  clean       - Remove compiled class files"
	@echo "  help        - Show this help message"

.PHONY: compile run-server run-client clean test help