package com.loupgarou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/codenames")
public class CNController {
	@GetMapping
	public String afficheCD()
	{
		return "codenames";
	}
}
