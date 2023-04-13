package com.lhs.pdfFileConverter.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lhs.pdfFileConverter.service.DashboardService;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping(value="/home/")
public class DashboardController {

	@Autowired
	DashboardService dashboardService;
	@GetMapping("/dashboard")
	public String createDirectory() {
		dashboardService.createDirectory();
		return null;
	}
}
