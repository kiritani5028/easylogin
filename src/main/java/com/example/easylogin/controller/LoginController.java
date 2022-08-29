package com.example.easylogin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;

import com.example.easylogin.model.dao.UserRepository;
import com.example.easylogin.model.entity.User;

@Controller
public class LoginController {
	
	@Autowired //new演算子を使わずとも、インスタンス化がされます。
	UserRepository userRepos;
	
	@RequestMapping("/")// 指定のURLでリクエストされたときに、メソッドを動作させます。
	public String index( Model m) {
//		String message = "welcome";
//		m.addAttribute("message", message);
		return "index";
	}
	
	@RequestMapping("/login")// 指定のURLでリクエストされたときに、メソッドを動作させます。
	public String login(
		//@RequestParamはクライアントからのリクエストであることを意味し、formタグのname属性を指定します。
		//fromの値を受け取るには@RequestParamにnameの名前を記述する必要があります。
		//引数のModelはレスポンスとしてlogin.htmlに返すオブジェクトです。
		@RequestParam("userName") String userName,
		@RequestParam("password") String password,
		Model m) {
		
		String message = "welcome!";
		
		List<User> users = userRepos.findByUserNameAndPassword(userName, password);
		if(users.size() > 0) {
			User user = users.get(0);
			message += user.getFullName();
		} else {
			message += "guest";
		}
		
		m.addAttribute("message", message);
		
		return "login";
	}
}
