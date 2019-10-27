package com.newtouch.cloud.demo.service.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.xml.bind.ValidationException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;


/**
 * 异常处理类
 */
@RestControllerAdvice
@Slf4j
public class CommonExceptionAdvice {

    /**
     * 自动转换日期类型的字段格式
     */
  @InitBinder
  public void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
  }

  /**
   * 400 - Bad Request
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({HttpMessageNotReadableException.class, MissingServletRequestParameterException.class})
  public ResponseData<Object> handleHttpMessageNotReadableException(Exception e) {
      log.error("参数解析失败", e);
      return new ResponseData<>(-1, "参数解析失败");
  }

  /**
   * 400 - Bad Request
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseData<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    log.error("参数验证失败", e);
      return new ResponseData<>(-1, "参数绑定失败" + parseErrorMessage(e.getBindingResult()));

  }

  /**
   * 400 - Bad Request
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(BindException.class)
  public ResponseData<Object> handleBindException(BindException e) {
    log.error("参数绑定失败", e);
      return new ResponseData<>(-1, "参数绑定失败" + parseErrorMessage(e.getBindingResult()));
  }

    private String parseErrorMessage(BindingResult result) {
    FieldError error = result.getFieldError();
        String field = null;
        try {
            field = error.getField();

        } catch (NullPointerException e) {
            if (null == field) field = "";
        }

        String message = null;
        try {
            message = error.getDefaultMessage();
        } catch (NullPointerException e) {
            if (null == message) message = "";
        }

        return String.format("%s:%s", field, message);
  }

  /**
   * 400 - Bad Request
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseData<Object> handleServiceException(ConstraintViolationException e) {
    log.error("参数验证失败", e);

    Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
    ConstraintViolation<?> violation = violations.iterator().next();
    String message = violation.getMessage();

      return new ResponseData<>(-1, String.format("参数验证失败: %s", message));
  }

  /**
   * 400 - Bad Request
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(ValidationException.class)
  public ResponseData<Object> handleValidationException(ValidationException e) {
    log.error("参数验证失败", e);

      return new ResponseData<>(-1, String.format("参数验证失败: %s", e.getMessage()));
  }

  /**
   * 404 - Not Found
   */
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(NoHandlerFoundException.class)
  public ResponseData<Object> noHandlerFoundException(NoHandlerFoundException e) {

    log.error("Not Found", e);

      return new ResponseData<>(-1, "请求资源不存在");
  }


  /**
   * 405 - Method Not Allowed
   */
  @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public ResponseData<Object> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
    log.error("不支持当前请求方法", e);
      return new ResponseData<>(-1, "请求方法不正确");
  }

  /**
   * 415 - Unsupported Media Type
   */
  @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
  @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
  public ResponseData<Object> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
    log.error("不支持当前媒体类型", e);

      return new ResponseData<>(-1, "不支持当前媒体类型");
  }

  /**
   * 业务层需要自己声明异常的情况
   */
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(BusinessException.class)
  public ResponseData<Object> handleServiceException(BusinessException e) {
    log.error("业务逻辑异常", e);
      return new ResponseData<>(-1, String.format("业务逻辑异常：%s", e.getMessage()));
  }

  /**
   * 获取其它异常。包括500
   */
  @ExceptionHandler({Exception.class, Throwable.class})
  public ResponseData<Object> defaultErrorHandler(Throwable e) {
      log.error("Exception", e);
      return new ResponseData<>(-1, "发生异常：未知错误");
  }

}
