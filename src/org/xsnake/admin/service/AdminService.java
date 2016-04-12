package org.xsnake.admin.service;

import java.util.List;

public interface AdminService {

	/**
	 * 根据查询的条件获取服务的分页对象
	 * @param name
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	List<ServiceData> getServiceList();
	
	void restart(String serverId);
	
	void restartAll();
	
}
