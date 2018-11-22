#!/usr/bin/python

# Self-usage autotester script.
# Takes as inputs:
# - the relative path to the folder containing the test inputs and outputs
# - the relative path to a Java file, containing a main() method which reads from stdin and writes to stdout
# Runs all the tests (by diverting stdin to the input file and running the main()), compares the output to the
# accepted output (by diverting stdout to a tmpfile), and outputs which testcases passed and which failed.
#
# Currently can only compare outputs directly (checks for equality);
# doesn't function when more than one solution is acceptable.

import sys
import subprocess
from pathlib import Path

def compileJava(javaFile):
    cmd = 'javac ' + javaFile
    proc = subprocess.call(cmd)

def isInputFile(fileName):
    if ".in" == (fileName.name)[-3:] :
        return True
    else:
        return False

testDataFolder = sys.argv[1]

def cutInExtension(fileName):
    return testDataFolder + '/' + fileName.name[:-3]

testDataFolder = sys.argv[1]
testFilePathName = sys.argv[2] + ".java"
javaClassName = sys.argv[2][4:].replace("/", ".")

path = Path('./' + testDataFolder)

testCases = list(map(cutInExtension,
                     filter(isInputFile,
                            path.iterdir())
                     )
                 ) # list of input files to be used.

# compiles the autotester and the class to be tested
compileJava(testFilePathName)
compileJava("src/AutoTester.java")

# execute autotester
subprocess.call(["java", "-cp", "./src",  "AutoTester", javaClassName] + testCases)