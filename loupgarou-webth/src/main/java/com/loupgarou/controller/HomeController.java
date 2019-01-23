package com.loupgarou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping({"/crud", "/"})
	public String homeGet()
	{
		return "crud";
	}
}
