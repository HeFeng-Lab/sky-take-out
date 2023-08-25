package com.sky.result;


import lombok.Data;
import java.io.Serializable;

@Data
public class Result<T> implements Serializable {
  private Integer code;
  private String msg;
  private T data;

  public static <T> Result<T> success() {
    Result<T> result = new Result<T>();
    result.code = 1;
    result.msg = "success";
    return result;
  }

  public static <T> Result<T> success(T object) {
    Result<T> result = new Result<T>();
    result.code = 1;
    result.data = object;
    result.msg = "success";
    return result;
  }

  public static <T> Result<T> error(String msg) {
    Result result = new Result();
    result.code = 0;
    result.msg = msg;

    return result;
  }
}
