#Makefile
run:
	./build/install/app/bin/app

run-h:
	./build/install/app/bin/app -h

run-json:
	./build/install/app/bin/app ~/java-project-71/app/src/test/resources/file1.json ~/java-project-71/app/src/test/resources/file2.json

run-yml:
	./build/install/app/bin/app ~/java-project-71/app/src/test/resources/file1.yml ~/java-project-71/app/src/test/resources/file2.yml

lint:
	./gradlew checkstyleMain
	./gradlew checkstyleTest

build:
	./gradlew clean
	./gradlew installDist

test:
	./gradlew test

report:
	./gradlew jacocoTestReport

.PHONY: build
