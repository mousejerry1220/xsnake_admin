package org.xsnake.admin.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xsnake.admin.service.AdminService;
import org.xsnake.admin.service.ServiceData;
import org.xsnake.web.action.BaseAction;

@Controller
@RequestMapping("/service")
public class ServiceAction extends BaseAction {

	@Autowired
	AdminService adminService;
	
	@RequestMapping("/serviceList")
	public String serviceList(String service,Model model){
		List<ServiceData> serviceList = adminService.getServiceList();
		model.addAttribute("serviceList",serviceList);
		return forward("serviceList");
	}

	
	
}
