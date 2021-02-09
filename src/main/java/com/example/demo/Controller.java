package com.example.demo;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class Controller {
	
	@Autowired
	DirService dirService;
	@Autowired
	Location path;
	
	
		@RequestMapping(method = RequestMethod.GET,path = "/")
		private ModelAndView getHome(ModelAndView modelAndView,HttpServletRequest httpServletRequest ) {
			String location = dirService.getSessionAttr(httpServletRequest, "location");
			if(location.isEmpty()) {
				modelAndView.setViewName("home");
				modelAndView.addObject("display", "none");
				return modelAndView;
			}
			
			path.setLocation(location);
			Map<String,Object> map = new HashMap<>();
			ArrayList<String []> listFiles = dirService.getDuplicates(path);
			int n = listFiles.size();
			if(n > 0 && listFiles.get(0).length == 1) {
				//help to display errors
				map.put("display", "");
				// clearing what's added to a list in catch block due to FileNotFoundException
				listFiles.clear();
				n = 0;
			}
			else {
				map.put("display", "none");
			}
			Iterable<String[]> files = (Iterable<String[]>) listFiles;
		
			map.put("files",files );
			map.put("size", n);
			modelAndView.addAllObjects(map);
			modelAndView.setViewName("home");
			return modelAndView;
		}
	
		

		@RequestMapping(method = RequestMethod.GET,path = "/test")
		private String test() {
			return "test api";
		}
		// post method
		@RequestMapping(method = RequestMethod.POST,path = "/files")
		private ModelAndView getFiles(ModelAndView modelAndView,@ModelAttribute("path") Location location,HttpServletRequest httpServletRequest) {
			dirService.clearSession(httpServletRequest, "location");
			dirService.setSessionAttr(httpServletRequest,location,"location");
			
			return new ModelAndView("redirect:/");
		}
		
		@RequestMapping(method = RequestMethod.POST,path = "/delete")
		private ModelAndView deleteFiles(ModelAndView modelAndView ) {
			
			dirService.deleteDuplicates();
			
			return new ModelAndView("redirect:/");
		}
		
		
		
}
