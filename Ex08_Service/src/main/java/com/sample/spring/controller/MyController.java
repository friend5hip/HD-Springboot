package com.sample.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sample.spring.service.ISimpleBbsService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MyController {
	
	@Autowired
	ISimpleBbsService bbs;
	
	@RequestMapping("/")
	public String root() {
		return "redirect:list";
	}
	
	@RequestMapping("/list")
	public String listPage(Model model) {
		model.addAttribute("lists", bbs.list());
		model.addAttribute("count", bbs.count());
		return "list";
	}
	
	@RequestMapping("/view")	// view?id=1
	public String viewPage(HttpServletRequest request, Model model) {
		String sId = request.getParameter("id");
		model.addAttribute("dto", bbs.view(sId));
		return "view";
	}
	
	@RequestMapping("/writeForm")
	public String writePage() {
		return "writeForm";
	}
	
	@RequestMapping("/write")
	public String write(HttpServletRequest request) {
		bbs.write(
				request.getParameter("writer"),
				request.getParameter("title"),
				request.getParameter("content"));
		return "redirect:list";
	}
	
	@RequestMapping("/delete")	// delete?id=1
	public String deletePage(HttpServletRequest request) {
		bbs.delete(request.getParameter("id"));
		return "redirect:list";
	}
}
