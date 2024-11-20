APP_PATH = ./build/install/app/bin/app

setup:
	./gradlew wrapper --gradle-version 8.5

clean:
	./gradlew clean

run-dist:
		$(APP_PATH)

clean:
		./gradlew clean

install:
		./gradlew clean install

run: build
		$(APP_PATH) $(ARGS)

app:
		make run ARGS="$(ARGS)"

lint:
		./gradlew checkstyleMain

test:
		./gradlew test

report:
	./gradlew jacocoTestReport

.PHONY: build

build:
		./gradlew clean build
