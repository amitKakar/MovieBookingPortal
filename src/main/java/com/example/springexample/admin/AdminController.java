package com.example.springexample.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AdminController {

	@Autowired
	AdminDAO adminDAO;

	@GetMapping("/adminLogin")
	public String adminLogin(Model model) {
		model.addAttribute("adminform", new Admin());
		return "adminLogin"; 
	}
	
	@PostMapping("/add/admin")
	public String addAdmin( @RequestBody Admin admin, Model model)
	{
		return "";
	}

	@PostMapping("/admin/login")
	public ResponseEntity<Admin> authenticate(@RequestBody Admin admin, HttpSession session) {
		Admin savedAdmin = adminDAO.authenticate(admin);
		if (null != savedAdmin) {

			session.setAttribute("adminname", savedAdmin.getAdminName());
			return new ResponseEntity<Admin>(savedAdmin, HttpStatus.OK);

		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}
	
	@GetMapping("/adminLogout")
    public String logout(HttpServletRequest request) {
        System.out.println("logout()");
        HttpSession httpSession = request.getSession();
        httpSession.invalidate();
        return "redirect:/adminLogin";
    }
	
	

}
