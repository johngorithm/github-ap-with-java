#!/bin/sh

echo "Running static analysis and tests ..."

# Inspect code (find-bug, lint, pmd, checkstyle, test)
./gradlew check test --daemon

status=$?

if [ "$status" = 0 ] ; then
    echo "Static analysis found no problems and tests passed."
    exit 0
else
    echo 1>&2 "Static analysis found violations it could not fix."
    exit 1
fi