APP_PATH = ./build/install/app/bin/app

setup:
	./gradlew wrapper --gradle-version 8.5

run-dist:
		$(APP_PATH)

clean:
		./gradlew clean

install:
		./gradlew clean installDist

run: build
		$(APP_PATH) $(ARGS)

app:
		make run ARGS="$(ARGS)"

lint:
		./gradlew checkstyleMain

test:
		./gradlew checkstyleMain checkstyleTest

report:
	./gradlew jacocoTestReport --warning-mode all

.PHONY: build

build:
		./gradlew clean build
