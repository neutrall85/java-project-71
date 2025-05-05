#Makefile
run-dist:
	./build/install/app/bin/app

lint:
	./gradlew checkstyleMain
	./gradlew checkstyleTest

build:
	./gradlew clean
	./gradlew installDist

test:
	./gradlew test

.PHONY: build
