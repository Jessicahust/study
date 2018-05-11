## 目录结构
解压后生成以下两个子目录

* study-service，包含各中间件的使用示例代码，代码在src/main/java目录下的com.cainiao.work包中。
* study-start，包含启动类`com.cainiao.work.Application`。中间件使用示例的单元测试代码在`src/test/java`目录下的`com.cainiao.work`包中。日志配置文件为`src/main/resources`目录下的logback-spring.xml。

## 使用方式
### 在开发工具中执行
将工程导入eclipse或者idea后，直接执行包含main方法的类`com.cainiao.work.Application`。

### 使用fat jar的方式
这也是pandora boot应用发布的方式。首先执行下列命令打包
   
```sh
mvn package
```

如果选择了auto-config，可在命令后加

```sh 
-Dautoconfig.userProperties=study-start/antx.properties
```

通过-D参数指定antx.properties的位置，否则会进入autoconfig的交互模式

然后进入`study-start/target`目录，执行fat jar

```sh
java -Dpandora.location=${sar} -jar study-start-1.0.0-SNAPSHOT.jar
```

其中${sar}为sar包的路径

### 通过mvn命令直接启动
第一次调用前先要执行

```sh
mvn install
```

如果maven工程的Artifact，group id，version等都未变化，只需执行一次即可。

然后直接通过命令执行start子工程

```sh
mvn -pl study-start pandora-boot:run
```

以上两个命令，如果选择了auto-config，可在命令后加

```sh 
-Dautoconfig.userProperties=study-start/antx.properties
```

通过-D参数指定antx.properties的位置，否则会进入autoconfig的交互模式properties的位置

## aone发布
请参考文档 http://gitlab.alibaba-inc.com/middleware-container/pandora-boot/wikis/aone-guide

## 相关链接
### Pandora Boot
* gitbook ： http://mw.alibaba-inc.com/products/pandoraboot/_book/
* 钉钉交流群 ： 11701173
* wiki ： http://gitlab.alibaba-inc.com/middleware-container/pandora-boot/wikis/home

### 开发者应用中心
* 线上 ： http://start.alibaba-inc.com
* 日常 ： http://start.taobao.net
* 文档 ： http://gitlab.alibaba-inc.com/middleware-container/tomcat-web/wikis/application-center

### Docker相关链接
* 如果工程有docker模板，目录是 APP-META，docker模板的说明文件是：APP-META/README.md
* docker参考说明：http://gitlab.alibaba-inc.com/middleware-container/pandora-boot/wikis/docker

### HSF
* gitbook ： http://mw.alibaba-inc.com/products/hsf/_book/
* wiki ： http://gitlab.alibaba-inc.com/middleware/hsf2-0/wikis/home
* HSF用法详细说明 ： http://gitlab.alibaba-inc.com/middleware-container/pandora-boot/wikis/spring-boot-hsf