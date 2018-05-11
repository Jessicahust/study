
## Docker相关链接

* Aone关于docker的文章目录（新人必看）：https://lark.alipay.com/aone/docker
* Dockerfile文件命名的规范：https://lark.alipay.com/aone/docker/rm2g1d
* 使用docker常见问题： http://www.atatech.org/articles/53899
* docker答疑群：钉钉搜索“Docker发布技术支持”

## PandoraBoot基础知识学习

> 不了解pandora boot应用如何部署的亲先阅读下方知识，再进行逐步操作

* Pandora Boot 应用部署标准： http://gitlab.alibaba-inc.com/middleware-container/pandora-boot/wikis/online-deploy
* Pandora Boot 应用Docker参考： http://gitlab.alibaba-inc.com/middleware-container/pandora-boot/wikis/docker
* pandora-boot-docker-demo： http://gitlab.alibaba-inc.com/middleware-container/pandora-boot-docker-demo

## 发布步骤

### 第一步：新建发布变更

### 第二步：确保$appName.release文件里有配置 build.tools.docker.args=--build-arg APP_NAME=${APP_NAME}

```
# 设置Dockerfile里的APP_NAME变量，必须要配置
build.tools.docker.args=--build-arg APP_NAME=${APP_NAME}
```

### 第三步：构建基础镜像

> 请确保app.release文件中jdk和dockerfile_base中的jdk是一个版本,若没有app.release文件请添加，参看 http://docs.alibaba-inc.com/pages/viewpage.action?pageId=252891532

0. 访问https://aone.alibaba-inc.com/appcenter/overview -》查找具体应用进入概述页面-》点击基础镜像
0. 创建基础镜像,填写第一步的变更仓库地址，成功创建基础镜像

* 为什么要创建基础镜像？

   把不常变化的内容(如基础RPM、启动脚本等)做成基础镜像，在应用发布的指定Dockerfile中FROM基础镜像，重复的内容不再执行，应用基础镜像被推送到全部的构建机器，提高镜像构建速度，再也不用坐在那里干等构建了

* environment目录什么作用？

    应用的通常脚本和配置都放在common目录下，如果应用在不同环境下面有自己的配置，则可以放在不同的目录下面，现在Dockerfile里复制到镜像里。

### 第四步: 用基础镜像地址替换其它的dockerfile的From关键字后面的地址

Dockerfile_base里的from不用改，它不能from自身。其它的Dockerfile_*改掉from即可。

### 第五步: 将变更提交发布到日常环境
