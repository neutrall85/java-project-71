#Makefile
run-dist:
	./build/install/app/bin/app -h

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
