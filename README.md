# 模板项目

- [模板项目](#模板项目)
  - [脚本部分](#脚本部分)
  - [项目部分](#项目部分)

## 脚本部分

- gradle空项目创建脚本
  - [脚本根目录位置](/scripts/gradle-project/)
  - 复制[gradle-init.ps1](/scripts/gradle-project/gradle-init.ps1)到新项目目录
  - 复制[build.gradle](/scripts/gradle-project/build.gradle)到新项目目录
  - 复制[.gitignore](/scripts/gradle-project/.gitignore)到新项目目录
  - 执行`gradle-init.ps1`初始化项目
- gradle命令脚本
  - [脚本根目录位置](/scripts/)
  - 修改[java-home-template.ps1](/scripts/java-home-template.ps1)文件为`java-home.ps1`
  - 修改[gradle-home-template.ps1](/scripts/gradle-home-template.ps1)文件为`gradle-home.ps1`
  - 修改上面两个文件中的目录信息指向到真实的jdk和gradle目录即可
  - 执行[java-cmd.ps1](/scripts/java-cmd.ps1)可以得到一个java的命令执行环境
  - 执行[gradle-cmd.ps1](/scripts/gradle-cmd.ps1)可以得到一个gradle的命令执行环境

## 项目部分

- 后端项目说明
  - 为了安全没有提供`application.yml`，但是在测试文件夹里面提供了`application.yml.template`配置模板文件
  - 配置文件中ENC(加密密码)可以用MainTest中的加解密测试生成
- [springboot模板项目](/springboot-template/)
  - 项目需要配置redis和mysql，[文档ui访问地址](http://127.0.0.1:20000/doc.html)
  - 数据库脚本请查看项目的sql目录说明
  - 系统核心配置在SystemConfig实体类中，该对象的默认值会被初始化到redis
  - 用户登录信息保存在redis中，通过AuthInfo对象（使用spring的request作用域注入）
  - 项目使用了[lombok](https://projectlombok.org/)，需要配置ide相关插件
