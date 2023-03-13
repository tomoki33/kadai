package com.example.kadai.user;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
@RequestMapping("/")
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping("")
	public String top(Model model) {
		return "/toppage";
	}


	@GetMapping("/users")
	public String Index(Model model) {
		List<User> users = service.getUsers();
		for (var user : users) {
			System.out.println(user.getName());
		}
		model.addAttribute("users", users);
		return "/index";
	}

	@GetMapping("/users/{test}")
	public String Index(@PathVariable int test,  Model model) {
		List<User> users = service.getUsers(test);
		int page = service.getpage();
		for (var user : users) {
			System.out.println(user.getName());
		}
		model.addAttribute("users", users);
		model.addAttribute("page",page);
		return "/index";
	}

	@GetMapping("/create")
	public String createUser(Model model) {
		return "/create";
	}

	@PostMapping("/create-confirm")
	public String createConfirmUser(@RequestParam("userName") String v,
			@RequestParam("userMail") String h, Model model) {
		model.addAttribute("confirmName", v);
		model.addAttribute("confirmMail", h);

		return "/create-confirm";
	}

	@PostMapping("/create-complete")
	public String createCompleteUser(@RequestParam("userName") String name,
			@RequestParam("userMail") String mail, Model model) {
		// DBへの登録
		service.createUser(name, mail);
		return "redirect:/users/1";
	}

	// 編集
	@PostMapping("/update")
	public String updateUser(@RequestParam("id") int id,
			@RequestParam("name") String v,
			@RequestParam("mail") String h, Model model) {
		model.addAttribute("id", id);
		model.addAttribute("name", v);
		model.addAttribute("mail", h);
		return "/update";
	}

	@PostMapping("/update_confirm")
	public String updateConfirmUser(@RequestParam("id") int id,
			@RequestParam("name") String v,
			@RequestParam("mail") String h, Model model) {
		model.addAttribute("id", id);
		model.addAttribute("name", v);
		model.addAttribute("mail", h);
		return "/update_confirm";
	}

	@PostMapping("/update_complete")
	public String updateCompleteUser(@RequestParam("id") int id,
			@RequestParam("name") String name,
			@RequestParam("mail") String mail, Model model) {
				model.addAttribute("id", id);
				model.addAttribute("name", name);
				model.addAttribute("mail", mail);
		service.updateUser(id, name, mail);
		return "redirect:/users";
	}

	// 削除
	@PostMapping("/delete")
	public String deleteUser(@RequestParam("id") int id,
	@RequestParam("name") String name,
	@RequestParam("mail") String mail, Model model) {
		model.addAttribute("id", id);
		model.addAttribute("name", name);
		model.addAttribute("mail", mail);
		return "/delete";
	}

	//削除完了
	@PostMapping("/delete_complete")
	public String deleteUser(@RequestParam("id") int id ,Model model) {
		model.addAttribute("id", id);
		service.deleteUser(id);
		return "redirect:/users/1";
	}
}