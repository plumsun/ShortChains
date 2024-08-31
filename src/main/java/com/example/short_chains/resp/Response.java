package com.example.short_chains.resp;

/**
 * The type Response.
 *
 * @author plumsun Created on 2023-12-03
 */
public class Response {

    /**
     * The constant serialVersionUID.
     */
    private static final long serialVersionUID = 6310663381656773827L;

    /**
     * The Code.
     */
    private int code;

    /**
     * The Message.
     */
    private String message;

    /**
     * The Data.
     */
    private Object data;

    /**
     * timestamp
     */
    private long timestamp;

    /**
     * Instantiates a new Response.
     */
    public Response() {
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * Instantiates a new Response.
     *
     * @param code    the code
     * @param message the message
     * @param data    the data
     */
    public Response(int code, String message, Object data) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * Instantiates a new Response.
     *
     * @param code the code
     * @param data the data
     */
    public Response(int code, Object data) {
        this.code = code;
        this.data = data;
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * Instantiates a new Response.
     *
     * @param code    the code
     * @param message the message
     */
    public Response(int code, String message) {
        this.code = code;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * Gets code.
     *
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * Sets code.
     *
     * @param code the code
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets message.
     *
     * @param message the message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets data.
     *
     * @return the data
     */
    public Object getData() {
        return data;
    }

    /**
     * Sets data.
     *
     * @param data the data
     */
    public void setData(Object data) {
        this.data = data;
    }

    /**
     * Gets timestamp.
     *
     * @return the timestamp
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * Sets timestamp.
     *
     * @param timestamp the timestamp
     */
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Response{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", timestamp=" + timestamp +
                '}';
    }

    /**
     * Success response.
     *
     * @param data the data
     * @return the response
     */
    public static Response success(Object data) {
        Response r = new Response(200, data);
        r.setMessage("Success");
        return r;
    }

    /**
     * Success response.
     *
     * @return the response
     */
    public static Response success() {
        Response r = new Response(200, null);
        r.setMessage("Success");
        return r;
    }

    /**
     * Success response.
     *
     * @param message the message
     * @param data    the data
     * @return the response
     */
    public static Response success(String message, Object data) {
        return new Response(200, message, data);
    }

    /**
     * Err response.
     *
     * @return the response
     */
    public static Response err() {
        Response r = new Response(500, null);
        r.setMessage("服务异常");
        return r;
    }


    /**
     * Err response.
     *
     * @param msg  the msg
     * @param data the data
     * @return the response
     */
    public static Response err(String msg, Object data) {
        Response r = new Response(500, data);
        r.setMessage(msg);
        return r;
    }

    /**
     * Err response.
     *
     * @param msg the msg
     * @return the response
     */
    public static Response err(String msg) {
        Response r = new Response(500, msg);
        r.setData(null);
        return r;
    }

    /**
     * Err throwable.
     *
     * @param cause the cause
     * @return the throwable
     * @throws Throwable the throwable
     */
    public static Throwable err(Throwable cause) throws Throwable {
        throw cause.getClass().getDeclaredConstructor().newInstance();
    }

    /**
     * Err response.
     *
     * @param code the code
     * @param msg  the msg
     * @return the response
     */
    public static Response err(int code, String msg) {
        Response r = new Response(code, msg);
        r.setData(null);
        return r;
    }

    /**
     * Build response.
     *
     * @param code the code
     * @param msg  the msg
     * @param data the data
     * @return the response
     */
    public static Response build(Integer code, String msg, Object data) {
        Response r = new Response();
        r.setCode(code);
        r.setMessage(msg);
        r.setData(data);
        return r;
    }
}
