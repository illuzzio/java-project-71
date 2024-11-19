APP_PATH = ./build/install/app/bin/app

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

.PHONY: build

build:
		./gradlew clean build
