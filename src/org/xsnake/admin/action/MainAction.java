package org.xsnake.admin.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xsnake.web.action.BaseAction;

@RequestMapping("/")
@Controller
public class MainAction extends BaseAction {

	public static final String LOGIN_USER = "LOGIN_USER";

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		Object loginUser = getSessionAttribute(LOGIN_USER);
		if (loginUser == null) {
			return forward("login");
		}
		return redirect("/main");
	}

	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(String username, String password) {
		if("jerry".equalsIgnoreCase(username)){
			return sendSuccessMessage(username);
		}
		return sendErrorMessage("账号或者密码有错误！");
	}

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main() {
		return forward("main");
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		sessionInvalidate();
		return redirect("/login");
	}

}
