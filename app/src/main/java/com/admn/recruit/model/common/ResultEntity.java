package com.admn.recruit.model.common;

/**
 * 
 * <p>Title: ResultEntity</p>
 * <p>Description: 返回页面的JSON封装对象</p>
 * @author wangyi
 * @date 2018年7月25日
 */
public class ResultEntity {
	
	private boolean success;
	
	private Object data;
	
	private String msg;
	
	public ResultEntity() {
		super();
	}

	public ResultEntity(boolean success) {
		super();
		this.success = success;
	}

	public ResultEntity(boolean success, String msg) {
		super();
		this.success = success;
		this.msg = msg;
	}

	public ResultEntity(boolean success, Object data) {
		super();
		this.success = success;
		this.data = data;
	}

	public ResultEntity(boolean success, Object data, String msg) {
		super();
		this.success = success;
		this.data = data;
		this.msg = msg;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	@Override
	public String toString() {
		return "ResultEntity [success=" + success + ", data=" + data + ", msg=" + msg + "]";
	}
	
}
