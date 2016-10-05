package com.nimblemodule.spring;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nimblemodule.spring.model.LoginBean;
import com.nimblemodule.spring.model.Person;
import com.nimblemodule.spring.service.PersonService;

@Controller
public class PersonController {
	
	private PersonService personService;
	
	@Autowired(required=true)
	@Qualifier(value="personService")
	public void setPersonService(PersonService ps){
		this.personService = ps;
	}
	
	 @RequestMapping(value="/index.action",method=RequestMethod.GET)
   public ModelAndView signup(HttpServletRequest request, HttpServletResponse response)
   {
     LoginBean loginBean=new  LoginBean();
     loginBean.setPassword("aa");
     loginBean.setUsername("bb");
     ModelAndView model = new ModelAndView("login");
     //LoginBean loginBean = new LoginBean();
     model.addObject("loginBean", loginBean);
     request.setAttribute("message", "Login ");
     request.setAttribute("msg", "Sign Up ");
     
     
     return model;
   }
   
	
	
	
	@RequestMapping(value = "/persons", method = RequestMethod.GET)
	public String listPersons(Model model) {
		model.addAttribute("person", new Person());
		List<Person> list=	this.personService.listPersons();
		model.addAttribute("listPersons",list );
		return "person";
	}
	
	
	 @RequestMapping(value = "/afterlogin", method = RequestMethod.POST)
	  public String afterlogin(Model model) {
	   
	   
	    return "redirect:/persons";

	  }

	 @RequestMapping(value="/signupp",method=RequestMethod.GET)
   public ModelAndView sign(HttpServletRequest request, HttpServletResponse response, LoginBean loginBean)
   {
     ModelAndView model = new ModelAndView("login");
     //LoginBean loginBean = new LoginBean();
     model.addObject("loginBean", loginBean);
   
     request.setAttribute("message", "Sign Up ");
     
     
     return model;
   }
	
	
	
	
	
	//For add and update person both
	@RequestMapping(value= "/person/add", method = RequestMethod.POST)
	public String addPerson(@ModelAttribute("person") Person p){
	 
	  String country=p.getCountry();
	  System.out.println(country);
	String name=  p.getName();
		
		if(p.getId() == 0){
			//new person, add it
			this.personService.addPerson(p);
		}else{
			//existing person, call update
			this.personService.updatePerson(p);
		}
		
		return "redirect:/persons";
		
	}
	
	@RequestMapping("/remove/{id}")
    public String removePerson(@PathVariable("id") int id){
		
        this.personService.removePerson(id);
        return "redirect:/persons";
    }
 
    @RequestMapping("/edit/{id}")
    public String editPerson(@PathVariable("id") int id, Model model){
        model.addAttribute("person", this.personService.getPersonById(id));
        model.addAttribute("listPersons", this.personService.listPersons());
        return "person";
    }
	
}
