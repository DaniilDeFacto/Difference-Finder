run-dist:
	make -C app run-dist

build:
	make -C app build

test:
	make -C app test

report:
	make -C app report

.PHONY: build