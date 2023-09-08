# sky-take-out

## 1.项目结构

| 模块             | 描述                                                         |
| ---------------- | ------------------------------------------------------------ |
| **sky-take-out** | **maven**父工程，统一管理依赖版本，聚合其他子模块            |
| sky-common       | 子模块，存放公共类，例如：工具类、常量类、异常类等           |
| sky-pojo         | 子模块，存放实体类、VO、DTO等                                |
| sky-server       | 子模块，后端服务，存放配置文件、Controller、Service、Mapper等 |



| **名称** | **说明**                                     |
| -------- | -------------------------------------------- |
| Entity   | 实体，通常和数据库中的表对应                 |
| DTO      | 数据传输对象，通常用于程序中各层之间传递数据 |
| VO       | 视图对象，为前端展示数据提供的对象           |
| POJO     | 普通Java对象，只有属性和对应的getter和setter |

- 接口文档：http://localhost:8080/doc.html
- Swagger 文档：http://localhost:8080/swagger-ui/index.html