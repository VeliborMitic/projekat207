
package com.met.projekat.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.met.projekat.entities.Proizvod;
import com.met.projekat.entities.User;
import com.met.projekat.service.ProizvodServiceImpl;
import com.met.projekat.service.UserServiceImpl;



@Controller
@RequestMapping("/kupac")/////////////////////////////////////////////////////
public class ProizvodController {
	
	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private ProizvodServiceImpl proizvodServiceImpl;
	
	/*@GetMapping("home")///////////////////////////////
	public ModelAndView proizvodi() {
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

		model.setViewName("kupac/home");//////////////////////////////////////
		return model;
	}*/
	
	@GetMapping("/dodatiProizvodi")/////////////////////////////////////
	public ModelAndView dodatiProizvodi(){
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByUserName(auth.getName());

		model.addObject("user", user);
		
		Set<Proizvod> proizvodi = user.getProizvodi();
		
		
		if (!proizvodi.isEmpty()) {
			model.addObject("proizvodi", proizvodi);
		}
		model.setViewName("/kupac/dodatiProizvodi");////////////
		return model;
	}
    
	@GetMapping("/items/{id}/update")///////////////////////////////////////////////////////
	public String showUpdateUserForm(@PathVariable("id") String id, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByUserName(auth.getName());
		
		Proizvod proizvod = proizvodServiceImpl.findOne(Integer.valueOf(id));
		
		user.getProizvodi().add(proizvod);
		
		userService.save(user);

		model.addAttribute("user", user);
		model.addAttribute("proizvodi", user.getProizvodi());
		return "redirect:/kupac/home";//////////////////////////////////

	}
	
	@GetMapping("/items/{id}/remove")///////////////////////////////////////////////////////
	public String showUpdateUserFormRemoveProizvod(@PathVariable("id") String id, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByUserName(auth.getName());
		
		Proizvod proizvod = proizvodServiceImpl.findOne(Integer.valueOf(id));
		
		user.getProizvodi().remove(proizvod);
		
		userService.save(user);

		model.addAttribute("user", user);
		model.addAttribute("proizvodi", user.getProizvodi());
		return "redirect:/kupac/dodatiProizvodi";//////////////////////////////////

	}
}
