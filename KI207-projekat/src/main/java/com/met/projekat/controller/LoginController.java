package com.met.projekat.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.met.projekat.entities.Proizvod;
import com.met.projekat.entities.User;
import com.met.projekat.service.ProizvodServiceImpl;
import com.met.projekat.service.UserService;



@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProizvodServiceImpl proizvodServiceImpl;
	
	@GetMapping({"/index"})
	public ModelAndView index(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		return modelAndView;
	}

	@GetMapping({"/login"})
	public ModelAndView login(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	@GetMapping("/registracija")
	public ModelAndView registracija(){
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registracija");
		return modelAndView;
	}
	
	@PostMapping("/registracija")
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findUserByUserName(user.getUserName());
		if (userExists != null) {
			bindingResult
					.rejectValue("userName", "error.user",
							"Vec je registrovan korisnik sa istim korisnickim imenom");//////////////////////
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registracija");
		} else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "Uspesna registracija novog korisnika!");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("registracija");
			
		}
		return modelAndView;
	}
	
	@RequestMapping(value="kupac/home", method = RequestMethod.GET)
	public ModelAndView home(){
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByUserName(auth.getName());
		
		model.addObject("user", user);
		model.addObject("userName", "Dobrodosli " + user.getFirstName() + " " + user.getLastName() + " (" + user.getUserName() + ")");
		model.addObject("proizvod", new Proizvod());

		Set<Proizvod> proizvodi =  new HashSet<>(proizvodServiceImpl.listAllProizvodi());
		proizvodi.removeAll(user.getProizvodi());
		if (!proizvodi.isEmpty()) {
			model.addObject("proizvodi", proizvodi);
		}
		
		model.addObject("userName", "Dobrodosli " + user.getFirstName() + " " + user.getLastName() + " (" + user.getUserName() + ")");
		model.addObject("adminMessage","Korisnicka stranica");
		model.setViewName("kupac/home");
		return model;
	}
	
	/*@RequestMapping(value="/admin/home", method = RequestMethod.GET)
	public ModelAndView admin(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByUserName(auth.getName());
		modelAndView.addObject("userName", "Administrator " + user.getFirstName() + " " + user.getLastName() + " (" + user.getUserName() + ")");
		modelAndView.addObject("adminMessage","Admin stranica");
		modelAndView.setViewName("admin/home");
		return modelAndView;
	}
	*/


}
