package com.dk.dento.care.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Login controller.
 */
@Controller
@RequestMapping("/")
public class LoginController {
	@RequestMapping("login")
	public String login() {
		return "login/layout";
	}

	@RequestMapping("login/layout")
	public String getPatientPartialPage() {
		return "login/layout";
	}
}