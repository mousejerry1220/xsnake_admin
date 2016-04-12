package org.xsnake.admin.service;

import java.io.Serializable;
import java.util.Date;

public class ServiceData implements Serializable{

	private static final long serialVersionUID = 1L;

	private String interfaceName;
	
	private String maxVersion;

	private int versionCount;

	private Date lastStartupDate;

	private boolean run;
	
	public boolean isRun() {
		return run;
	}
	public void setRun(boolean run) {
		this.run = run;
	}
	public Date getLastStartupDate() {
		return lastStartupDate;
	}
	public void setLastStartupDate(Date lastStartupDate) {
		this.lastStartupDate = lastStartupDate;
	}
	public String getInterfaceName() {
		return interfaceName;
	}
	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}
	public String getMaxVersion() {
		return maxVersion;
	}
	public void setMaxVersion(String maxVersion) {
		this.maxVersion = maxVersion;
	}
	public int getVersionCount() {
		return versionCount;
	}
	public void setVersionCount(int versionCount) {
		this.versionCount = versionCount;
	}
 
}
