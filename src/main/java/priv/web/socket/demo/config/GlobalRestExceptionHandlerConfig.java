package priv.web.socket.demo.config;

import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import priv.web.socket.demo.domain.CheckedException;
import priv.web.socket.demo.domain.ErrorCodeEnum;
import priv.web.socket.demo.domain.result.ExceptionResult;

import javax.servlet.http.HttpServletRequest;
import java.net.BindException;
import java.util.Arrays;

/**
 * @author lyqlbst
 * @description 全局rest异常捕获
 * @email 1098387108@qq.com
 * @date 2019/11/7 4:01 PM
 */
@Slf4j
@Configuration
@RestControllerAdvice("priv.web.socket.demo.web")
public class GlobalRestExceptionHandlerConfig {
    /**
     * 400 - the request sent by the client was syntactically incorrect.
     *
     * @param e HttpMessageNotReadableException
     * @return 错误实体
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ExceptionResult handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return getHttpStatus400ExceptionResult(e.getMessage());
    }

    /**
     * 400 - the request sent by the client was syntactically incorrect.
     *
     * @param e MissingServletRequestParameterException
     * @return 错误实体
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ExceptionResult handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        return getHttpStatus400ExceptionResult(e.getMessage());
    }

    /**
     * 400 - the request sent by the client was syntactically incorrect.
     *
     * @param e ServletRequestBindingException
     * @return 错误实体
     */
    @ExceptionHandler(ServletRequestBindingException.class)
    public ExceptionResult handleServletRequestBindingException(ServletRequestBindingException e) {
        return getHttpStatus400ExceptionResult(e.getMessage());
    }

    /**
     * 400 - the request sent by the client was syntactically incorrect.
     *
     * @param e TypeMismatchException
     * @return 错误实体
     */
    @ExceptionHandler(TypeMismatchException.class)
    public ExceptionResult handleTypeMismatchException(TypeMismatchException e) {
        return getHttpStatus400ExceptionResult(e.getMessage());
    }

    /**
     * 400 - the request sent by the client was syntactically incorrect.
     *
     * @param e MethodArgumentNotValidException
     * @return 错误实体
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ExceptionResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return getHttpStatus400ExceptionResult(e.getMessage());
    }

    /**
     * 400 - the request sent by the client was syntactically incorrect.
     *
     * @param e MissingServletRequestPartException
     * @return 错误实体
     */
    @ExceptionHandler(MissingServletRequestPartException.class)
    public ExceptionResult handleMissingServletRequestPartException(MissingServletRequestPartException e) {
        return getHttpStatus400ExceptionResult(e.getMessage());
    }

    /**
     * 400 - the request sent by the client was syntactically incorrect.
     *
     * @param e BindException
     * @return 错误实体
     */
    @ExceptionHandler(BindException.class)
    public ExceptionResult handleBindException(BindException e) {
        return getHttpStatus400ExceptionResult(e.getMessage());
    }


    /**
     * 404 - requested resource is not available.
     *
     * @param e NoHandlerFoundException
     * @return 错误实体
     */
    @ExceptionHandler(value = NoHandlerFoundException.class)
    public ExceptionResult handleNoHandlerFoundException(NoHandlerFoundException e) {
        return getHttpStatus400ExceptionResult(e.getMessage());
    }

    /**
     * 自定义异常处理
     *
     * @param e CheckedException
     * @return 错误实体
     */
    @ExceptionHandler(value = CheckedException.class)
    public ExceptionResult handleCheckedException(CheckedException e, HttpServletRequest request) {
        if (ErrorCodeEnum.ERROR.getCode() == e.getCode()) {
            log.error(getMessage(e, request));
        }
        return ExceptionResult.of(e.getCode(), e.getMessage());
    }

    /**
     * 服务器错误
     *
     * @param e Exception
     * @return 错误实体
     */
    @ExceptionHandler(value = Exception.class)
    public ExceptionResult handleHttpStatus500Exception(Exception e, HttpServletRequest request) {
        log.error(getMessage(e, request), e);
        return ExceptionResult.of(ErrorCodeEnum.ERROR);
    }

    /**
     * 获取打印的信息
     *
     * @param e       Exception
     * @param request HttpServletRequest
     * @return 要打印的信息
     */
    private String getMessage(Exception e, HttpServletRequest request) {
        // 获取http请求方法
        String method = request.getMethod();
        // 获取url
        StringBuffer requestURL = request.getRequestURL();
        // 获取请求参数，若没有则不拼接到url中
        String queryString = request.getQueryString();
        if (!Strings.isNullOrEmpty(queryString)) {
            requestURL.append('?').append(queryString);
        }
        // 获取出错行数
        StackTraceElement element = Arrays.stream(e.getStackTrace())
                .filter(s -> s.getClassName().contains("priv.web.socket.demo"))
                .findFirst().orElse(e.getStackTrace()[0]);
        // 打印日志
        return e + "\trequestUrl:" + requestURL.toString()
                + "\tmethod:" + method
                + "\t\tdetail:" + element.getFileName() + ":"
                + element.getClassName() + "." + element.getMethodName()
                + " \terrorNum:" + element.getLineNumber();
    }

    /**
     * 获取400错误实体
     *
     * @param message 错误描述
     * @return http status为400的错误实体
     */
    private ExceptionResult getHttpStatus400ExceptionResult(String message) {
        return ExceptionResult.of(ErrorCodeEnum.PARAM_ERROR.getCode(), message);
    }
}
