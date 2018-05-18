# java-stack-trace

java-stack-trace是一个用于调试的java instrument，指定方法前缀，它能在指定前缀的方法被调用时打印当前堆栈。

### 编译
```shell
mvn package
```

### 用法
在java命令后添加`-javaagent`参数
```shell
-javaagent:java-stack-trace-1.0-SNAPSHOT-jar-with-dependencies.jar=[m:method name|f:file name]
```
##### 方法前缀可以用m:method name指定
 - m: 代表Method方法
 - method name: 需要打印堆栈的方法的前缀
 
##### 方法前缀也可以用f:file name指定
 - f: 代表从文件读取方法前缀
 - file name: 指定读取方法前缀的文件
