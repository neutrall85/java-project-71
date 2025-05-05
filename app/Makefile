#Makefile
run-dist:
	./build/install/app/bin/app

test:
	./gradlew checkstyleMain
	./gradlew checkstyleTest

build:
	./gradlew clean
	./gradlew installDist

.PHONY: build
