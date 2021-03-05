#!/usr/bin/env bash
# by lcs
# 2020-11-17

 /opt/apache-jmeter-5.3/bin/jmeter -n -t zt-b2b/zt-b2b-api/src/main/jmeter/token.jmx -l /data/logs/jmeter/token.txt -e -o /data/logs/jmeter/report