package com.sky.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Schema(description = "请求 data 对象")
public class EmployeeDTO implements Serializable {

  private Long id;

  @Schema(description = "必填", required = true, example = "test-username")
  private String username;

  @Schema(description = "必填", required = true, example = "test")
  private String name;

  @Schema(description = "必填", required = true, example = "12888889999")
  private String phone;

  @Schema(description = "必填, 男：1， 女： 0", required = true, example = "1")
  private String sex;

  @Schema(description = "必填", required = true, example = "360429202302022428")
  private String idNumber;
}
