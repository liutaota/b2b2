#!/bin/bash
#
# by lcs
# 2019-05-09


mvn clean compile exec:java  -Dexec.classpathScope=runtime -Dexec.mainClass="com.zsyc.code.generator.CodeGenerator"