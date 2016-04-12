package org.xsnake.web.dao;

import java.io.NotSerializableException;
import java.io.Serializable;

import com.mchange.v2.ser.SerializableUtils;

public class CacheQueryCondition implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Override
	public String toString() {
		try {
			byte[] bytes = SerializableUtils.toByteArray(this);
			return new String(bytes);
		} catch (NotSerializableException e) {
			throw new RuntimeException(" NotSerializableException ");
		}
	}
	
	public CacheQueryCondition(Object... objects){
		this.args = objects;
	}
	
	private Object[] args;

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}
	
}
