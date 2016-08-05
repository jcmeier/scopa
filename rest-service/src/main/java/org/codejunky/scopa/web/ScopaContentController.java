package org.codejunky.scopa.web;

import org.codejunky.scopa.models.Games;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ScopaContentController {
	@Autowired
	private Games games;
	
	@RequestMapping("")
	public String getStart(Model model) {
		return "game";
	}

}
