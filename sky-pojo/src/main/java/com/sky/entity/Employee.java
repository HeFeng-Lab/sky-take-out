package com.sky.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Employee implements Serializable {
  /**
   *   这一行代码是用于实现 Java 对象的序列化和反序列化过程中的版本控制的。
   *
   *   在 Java 中，当您将一个对象序列化（将其转换为字节流以便于传输或保存），然后再反序列化（从字节流中还原为对象）时，可能会遇到版本不匹配的问题。例如，当您序列化一个对象后，可能会对该对象进行更改，而在反序列化时，如果对象的结构已更改，就会出现问题。
   *
   *   serialVersionUID 是一个序列化版本号，用于标识一个类的不同版本。在反序列化时，Java 会检查序列化的对象与当前类定义的版本是否匹配。如果 serialVersionUID 不匹配，就会抛出 InvalidClassException。
   *
   *   通过显式地在类中定义 serialVersionUID，您可以控制序列化版本号，确保在对象结构发生变化时，可以控制是否允许进行反序列化。如果您不指定 serialVersionUID，Java 会根据类的结构和字段自动生成一个默认值，但这样可能会导致版本控制的问题。
   *
   *   总而言之，serialVersionUID 用于控制序列化版本，以确保在对象结构更改时仍然可以进行反序列化。它是一种用于版本控制的机制，有助于在类的结构发生变化时保持数据的一致性。
   */
  private static final long serialVersionUID = 1L;

  private Long id;
  private String name;
  private String username;
  private String password;
  private String phone;
  private String idNumber;
  private Integer status;

  private LocalDateTime createTime;
  private LocalDateTime updateTime;
  private Long createUser;
  private Long updateUser;
}
