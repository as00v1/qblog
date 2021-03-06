package com.qiaohx.util.response;

import org.springframework.validation.BindingResult;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 通用的返回编码
 */
public class ResponseUtil {

    static final String SUCCESS = "success";
    /**
     * 请求成功
     * @param o 数据
     * @return BaseDataResponse
     */
    public static <T extends BaseDataResponse> T success(Class<T> o){
        return result(ErrorCodeEnums.SUCCESS, SUCCESS, o);
    }

    public static BaseDataResponse success(){
        return new BaseDataResponse(ErrorCodeEnums.SUCCESS.getCode(), ErrorCodeEnums.SUCCESS.getMessage());
    }

    /**
     * 请求失败
     * @param errorCode 错误码
     * @return BaseDataResponse
     */
    public static BaseDataResponse error(ErrorCodeEnums errorCode){
        return new BaseDataResponse(errorCode.getCode(), errorCode.getMessage());
    }

    /**
     * 校验失败
     * @param bindingResult 校验结果
     * @param o 返回的类型
     * @param <T> BaseDataResponse
     * @return BaseDataResponse
     */
    public static <T extends BaseDataResponse> T fail(BindingResult bindingResult, Class<T> o){
        String code = bindingResult.getFieldError() != null ? bindingResult.getFieldError().getCode() : "";
        switch (code) {
            case "NotEmpty":
                return result(ErrorCodeEnums.PARAM_EMPTY, bindingResult.getFieldError().getDefaultMessage(), o);
            case "NotBlank":
                return result(ErrorCodeEnums.PARAM_EMPTY, bindingResult.getFieldError().getDefaultMessage(), o);
            case "NotNull":
                return result(ErrorCodeEnums.PARAM_EMPTY, bindingResult.getFieldError().getDefaultMessage(), o);
            case "Pattern":
                return result(ErrorCodeEnums.PARAM_ERROR, bindingResult.getFieldError().getDefaultMessage(), o);
            case "Min":
                return result(ErrorCodeEnums.PARAM_ERROR, bindingResult.getFieldError().getDefaultMessage(), o);
            case "Max":
                return result(ErrorCodeEnums.PARAM_ERROR, bindingResult.getFieldError().getDefaultMessage(), o);
            case "Length":
                return result(ErrorCodeEnums.PARAM_ERROR, bindingResult.getFieldError().getDefaultMessage(), o);
            case "Range":
                return result(ErrorCodeEnums.PARAM_ERROR, bindingResult.getFieldError().getDefaultMessage(), o);
            case "Email":
                return result(ErrorCodeEnums.PARAM_ERROR, bindingResult.getFieldError().getDefaultMessage(), o);
            case "DecimalMin":
                return result(ErrorCodeEnums.PARAM_ERROR, bindingResult.getFieldError().getDefaultMessage(), o);
            case "DecimalMax":
                return result(ErrorCodeEnums.PARAM_ERROR, bindingResult.getFieldError().getDefaultMessage(), o);
            case "Size":
                return result(ErrorCodeEnums.PARAM_ERROR, bindingResult.getFieldError().getDefaultMessage(), o);
            case "Digits":
                return result(ErrorCodeEnums.PARAM_ERROR, bindingResult.getFieldError().getDefaultMessage(), o);
            case "Past":
                return result(ErrorCodeEnums.PARAM_ERROR, bindingResult.getFieldError().getDefaultMessage(), o);
            case "Future":
                return result(ErrorCodeEnums.PARAM_ERROR, bindingResult.getFieldError().getDefaultMessage(), o);
            default:
                return result(ErrorCodeEnums.UNKNOW_ERROR, bindingResult.getFieldError().getDefaultMessage(), o);
        }
    }

    /**
     * 返回错误
     * @param errorCode 错误码
     * @param o 返回类型
     * @param <T> BaseDataResponse
     * @return BaseDataResponse
     */
    public static <T extends BaseDataResponse> T result(ErrorCodeEnums errorCode, Class<T> o){
        return result(errorCode, null, o);
    }
    /**
     * 返回业务错误信息
     * @param errorCode 错误码
     * @param message 自定义消息
     * @return BaseDataResponse
     */
    public static <T extends BaseDataResponse> T result(ErrorCodeEnums errorCode, String message, Class<T> o){
        T t = null;
        try {
            Constructor c = o.getDeclaredConstructor(new Class[]{int.class,String.class});
            c.setAccessible(true);
            if (message != null && !"".equals(message))
                t = (T) c.newInstance(new Object[]{errorCode.getCode(), message});
            else
                t = (T) c.newInstance(new Object[]{errorCode.getCode(), errorCode.getMessage()});
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return t;
    }


}
