#!/bin/bash
rm -rf heapdump.hprof
# rm -rf *.log
# rm -rf *.current
#   -XX:+UnlockDiagnosticVMOptions -XX:+LogVMOutput -XX:LogFile=vm.log \

java -jar -Xmx20G \
-Duser.timezone=Asia/Shanghai \
-XX:+HeapDumpOnOutOfMemoryError \
-XX:HeapDumpPath=heapdump.hprof \
-XX:ErrorFile=hs_err_pid_%p.log \
-XX:+PrintGCDetails \
-XX:+PrintGCDateStamps \
-Xloggc:gc.log \
-XX:+UnlockDiagnosticVMOptions \
-XX:+UseGCLogFileRotation \
-XX:GCLogFileSize=1m \
-XX:NumberOfGCLogFiles=2 \
-XX:-OmitStackTraceInFastThrow \
-XX:+PrintGCApplicationStoppedTime \
-XX:+PrintGCApplicationConcurrentTime \
target/intelliq-console.jar
