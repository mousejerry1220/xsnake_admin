package org.xsnake.admin.service;

import java.io.Serializable;

/**
 * 
 * @author Jerry.Zhao
 *
 */
public class ServiceInstanceData implements Serializable{

	private static final long serialVersionUID = 1L;

	String serverId;
	
	String host;
	
	int port;
	
	String url;

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
