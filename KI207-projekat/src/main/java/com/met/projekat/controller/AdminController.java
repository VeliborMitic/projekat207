package com.met.projekat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.met.projekat.entities.User;
import com.met.projekat.service.ProizvodServiceImpl;
import com.met.projekat.service.UserService;

@Controller
public class AdminController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProizvodServiceImpl proizvodServiceImpl;
	
	@RequestMapping(value="/admin/home", method = RequestMethod.GET)
	public ModelAndView admin(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByUserName(auth.getName());
		modelAndView.addObject("userName", "Administrator " + user.getFirstName() + " " + user.getLastName() + " (" + user.getUserName() + ")");
		modelAndView.addObject("adminMessage","Admin stranica");
		modelAndView.setViewName("admin/home");
		return modelAndView;
	}
	

}
