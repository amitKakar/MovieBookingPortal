package com.example.springexample.welcome;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

//import com.example.springexample.theatre.Theatre;

//import com.example.springexample.theatre.Theatre;

@Controller
public class WelcomeController {

	

	@RequestMapping("/message")
	public String message(Model model)
	{
		model.addAttribute("message","welcome to spring");
		return "message";
	}
}