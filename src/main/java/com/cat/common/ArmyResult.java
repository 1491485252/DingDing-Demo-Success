package com.cat.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 统一返回结果
 * 
 * @author DevinHe
 * 
 */
public class ArmyResult implements Serializable {

    private static final long serialVersionUID = -5440424819480013927L;
    public static final int OK = 200;
    public static final int WARN = 203;
    public static final int ERROR = 400;
    public static final int UNAUTHORIZED = 401;
    public static final int SECOND_UNAUTHORIZED = 402;
    public static final int FORBIDDEN = 403;
    public static final int SERVER_ERROR = 500;

    private int status;
    private List<String> msgs = new ArrayList<String>(1);
    private Object data;

    public ArmyResult(int status, List<String> msgs, Object data) {
        super();
        this.status = status;
        this.msgs = msgs;
        this.data = data;
        
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<String> getMsgs() {
        return msgs;
    }

    public void setMsgs(List<String> msgs) {
        this.msgs = msgs;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ArmyResult [status=" + status + ", msgs=" + msgs + ", data=" + data + "]";
    }

    /**
     * 验证是否成功
     * 
     * @param response
     * @return
     */
    public static final boolean isOK(ArmyResult response) {
        return response.getStatus() == OK;
    }

    /**
     * 是否验证成功
     * 
     * @param response
     * @return
     */
    public static final boolean isNotOK(ArmyResult response) {
        return !isOK(response);
    }

    /**
     * 验证是否警告
     * 
     * @param response
     * @return
     */
    public static final boolean isWARN(ArmyResult response) {
        return response.getStatus() == WARN;
    }

    /**
     * 返回失败响应结果
     * 
     * @param key 消息KEY
     * @return
     */
    public static final ArmyResult error(String key) {
        return error(key, null, null);
    }

    /**
     * 返回失败响应结果
     * 
     * @param key 消息KEY
     * @param params 参数
     * @param content 返回结果
     * @return
     */
    public static final ArmyResult error(String key, Object[] params, Object content) {
        List<String> list = new ArrayList<String>();
        list.add(MessageUtils.getMessage(key));
        return of(ERROR, list, content);
    }

    /**
     * 返回内容
     * 
     * @param content
     * @return
     */
    public static final ArmyResult of(Object content) {
        ArmyEmptyRow armyEmptyRow = new ArmyEmptyRow();
        armyEmptyRow.setRows(content);
        return of(OK, new ArrayList<String>(), armyEmptyRow);
    }

    /**
     * 返回成功响应结果
     * 
     * @param key 消息Key
     * @return
     */
    public static final ArmyResult ok(String key) {
        return ok(key, null, null);
    }

    /**
     * 返回成功响应结果
     * 
     * @param key 消息Key
     * @return
     */
    public static final ArmyResult ok(String key, Object content) {
        return ok(key, null, content);
    }

    /**
     * 添加返回参数设置
     * 
     * @param key
     * @param params
     * @return
     */
    public static final ArmyResult ok(String key, Object[] params) {
        return ok(key, params, null);
    }

    /**
     * 返回成功响应结果
     * 
     * @param key 消息key
     * @param params 消息参数
     * @return
     */
    public static final ArmyResult ok(String key, Object[] params, Object content) {
        List<String> list = new ArrayList<String>();
        list.add(MessageUtils.getMessage(key));
        return of(OK, list, content);
    }

    /**
     * 返回成功响应结果
     * 
     * @return
     */
    public static final ArmyResult OK() {
        return of(OK, null, null);
    }

    /**
     * 返回警告响应结果
     * 
     * @param key 消息key
     * @return
     */
    public static final ArmyResult warn(String key) {
        return warn(key, null, null);
    }

    /**
     * 返回警告响应结果
     * 
     * @param key 消息key
     * @param params 消息参数
     * @return
     */
    public static final ArmyResult warn(String key, Object content) {
        return warn(key, null, content);
    }

    /**
     * 返回警告响应结果
     * 
     * @param key 消息KEY
     * @param params 参数
     * @param content 返回结果
     * @return
     */
    public static final ArmyResult warn(String key, Object... params) {
        return warn(key, params, null);
    }

    /**
     * 返回警告响应结果
     * 
     * @param key 消息KEY
     * @param params 参数
     * @param content 返回结果
     * @return
     */
    public static final ArmyResult warn(String key, Object[] params, Object content) {
        List<String> list = new ArrayList<String>();
        list.add(MessageUtils.getMessage(key));
        return of(WARN, list, content);
    }

    /**
     * 返回响应结果
     * 
     * @param status 状态
     * @param msg 消息Key和参数的MAP集合
     * @param content 内容
     * @return
     */
    private static ArmyResult of(int status, List<String> list, Object content) {
        return new ArmyResult(status, list, content);
    }
}
