# exam-question-system

Spring Boot 3 + Spring Security 6 + JWT使用新版本实现身份验证

# 使用的技术

1. Spring Boot 3   
   要求使用JDK17+
2. Spring Security 6
3. SpringDoc  
   SpringFox-Swagger 中使用的是javax，而Spring Boot 3使用
   的是 jakarta。
4. PostgresSQL  
   数据库的使用无所谓，如果想使用MySQL，只需要在.yml中将配置修改
   一下。

```xml
spring:
        #配置数据库
        datasource:
        url: jdbc:mysql://localhost:3306/demo1
        username: mysql
        password: xiaoyi_wyx
        #配置Jpa
        jpa:
        hibernate:
        ddl-auto: update
        show-sql: true
        properties:
        hibernate:
        format_sql: true
        database: mysql
        # 使用的是MySQL8，版本号写到MySQL后就行
        # 比如57版本就写MySQL57Dialect
        database-platform: org.hibernate.dialect.MySQL8Dialect
```

其中`ddl-auto`可以设置为create-drop，就可以在启动时自动创建表、
退出后自动删除表

5. Redis  
   目前只使用在存储和检索token  
   关于其安装和启动这里就不介绍了。这里使用的是Spring Data Redis
6. Spring Data Jpa
7. Jwt

# 运行

配置好了之后，点击启动，在浏览器中访问  
http://localhost:8080/swagger-ui.html

# 流程

发送请求 ——> SecurityFilterChain ——> JwtAuthFilter ——>
UsernamePasswordAuthenticationFilter ——> 做出响应  

# 业务

## 规则
《阿里巴巴Java规范手册》中规定不得使用外键和级联，所以我对entity进行了修改。  
在所有有关联的地方加入了这段代码。
```java
	@OneToMany
	@JoinColumn(name = "team_id", foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
	private List<Course> courses;
	@OneToMany
	@JoinColumn(name = "team_id", foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
	private List<User> students;
	@OneToOne
	@JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
	private User headTeacher;
```
