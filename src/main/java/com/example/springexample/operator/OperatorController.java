package com.example.springexample.operator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class OperatorController {

	@Autowired 
	OperatorDAO operatorDAO; 
	

	@PostMapping("/registerOperator")
	public String OperatorForm(Model model)
	{
		model.addAttribute("operatorForm",new Operator());
		 
		return "registerOperator";
	}
	
	@PostMapping("/addOperator")
	public String addOperator(Operator operator, Model model)
	{ 
		Operator savedoperator = operatorDAO.addOperator(operator);
		model.addAttribute("operatorId",savedoperator.getOperatorId());
		model.addAttribute("operatorName",savedoperator.getOperatorName());
		return "operatorRegisterSuccess";
	}
	
	@GetMapping("/OperatorList")
	public String OperatorList(Model model)
	{
		model.addAttribute("operators",operatorDAO.getAllOperator()); 
		return "registeredOperator";
	}
	
	@GetMapping("/adminHome")
	public String adminLogin(Model model) {
		model.addAttribute("operatorForm",new Operator());
		return "registerOperator";
	}
	
	
	@GetMapping("/operatorLogin")
	public String loginForm(Operator operator, Model model)
	{
		model.addAttribute("operatorForm",new Operator());
		return "operatorLogin";
	}
	
	
	@PostMapping("/operator/login")
	public ResponseEntity<Operator> authenticate(@RequestBody Operator operator, HttpSession session)
	{
		Operator savedOperator = operatorDAO.authenticate(operator);
		if(null != savedOperator)
		{
			
			session.setAttribute("operatorname", savedOperator.getOperatorName());
				return new ResponseEntity<Operator>(savedOperator, HttpStatus.OK);
			
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			

	}

	@GetMapping("/operatorLogout")
    public String logout(HttpServletRequest request) {
        //System.out.println("logout()");
        HttpSession httpSession = request.getSession();
        httpSession.invalidate();
        return "redirect:/operatorLogin";
    }
	
	@GetMapping("/operator/id/{operatorId}")
	public ResponseEntity<Operator> getOperatorById(@PathVariable(value="operatorId") int operatorId)
	{
		Operator operator = operatorDAO.getOperatorById(operatorId);
		
		if(null == operator )
		{
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().body(operator);
	}
	
	@PostMapping("/operatorHome")
	public String operatorHomne()
	{
		return "operatorLoginSuccessfull";
	}
	
	
	@GetMapping("/operatorHome")
	public String operatorHome(HttpServletRequest request, Model model)
	{
		HttpSession session = request.getSession();
		String operatorName = (String)session.getAttribute("operatorname");
		
		if(operatorName == null || operatorName == "")
		{
			model.addAttribute("operatorForm", new Operator());
			return "operatorLogin";
		}
		
		return "operatorLoginSuccessfull";
	}
	
	@GetMapping("/operator/{operatorName}")
	public ResponseEntity<Operator> getOperatorByOperatorName(@PathVariable(value="operatorName") String operatorName)
	{
		Operator operator = operatorDAO.getOperatorByOperatorName(operatorName);
		
		if(null == operator )
		{
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().body(operator);
	}
	
	@PutMapping("/operator/{operatorName}")
	public void updateOperator(@PathVariable(value="operatorName") String operatorName, @Valid @RequestBody Operator updatedOperator)
	{
		operatorDAO.updateOperator(operatorName , updatedOperator);
	}
	
	@DeleteMapping("/operator/id/{operatorId}")
	public void delete(@PathVariable(value="operatorId") int operatorId)
	{
		operatorDAO.deleteOperatorById(operatorId);
	}
	
	@DeleteMapping("/operator/{operatorName}")
	public void delete(@PathVariable(value="operatorName") String operatorName)
	{
		operatorDAO.deleteOperatorByName(operatorName);
	}
	
}
