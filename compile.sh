#!/bin/bash
javac -cp .:./src:./lib/jfxrt.jar ./src/VoxspellPrototype/VoxspellPrototype.java

if [ $? -eq 0 ] ; then
    echo "Compilation Successful!";
else
    echo "Compilation Failed"
fi
