# java-stack-trace

java-stack-trace是一个用于调试的java instrument，指定方法前缀，它能在指定前缀的方法被调用时打印当前堆栈。

### 编译
使用`maven clean`然后`package`即可编译成`java-stack-trace-1.0-SNAPSHOT-jar-with-dependencies.jar`

### 用法
```shell
-javaagent:java-stack-trace-1.0-SNAPSHOT-jar-with-dependencies.jar=m:com.xxxx.xxxx
```
 - m: 代表Method方法
 - com.xxxx.xxxx: 需要打印堆栈的方法的前缀
