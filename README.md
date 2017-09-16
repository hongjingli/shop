# 116商城

项目clone导入eclipse就可以了，还有些重要的配置：

tomcat 建议自己搭建

该自己本地的数据库：
帐号：ct116
密码：scutct116

TestController有些注释掉的部分是要数据库有数据才能跑起来的，这里注意下：
我数据库同一使用了innodb，有外键约束，插入顺序不当会失败，建议使用图形化界面操作，
我用phpstudy自带的mysql-font

### push到你们的分支上，禁止直接push到master

## Service
通过查询数据库构建模型

## Controller
接受前端数据，判断合法性（IForm.validate）【这个规则自行设计，并整理成文档】调用Serice提供的方法，拿到数据反馈给前端

## 强烈建议
基于一个原则设计代码：

# 避免重复代码

#### 大家加油

好累
