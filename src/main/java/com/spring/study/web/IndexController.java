package com.spring.study.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.spring.study.config.auth.LoginUser;
import com.spring.study.config.auth.dto.SessionUser;
import com.spring.study.service.posts.PostsService;
import com.spring.study.web.dto.PostsResponseDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class IndexController
{
	private final PostsService postsService;

	@GetMapping("/")
	public String index(Model model, @LoginUser SessionUser user)
	{
		model.addAttribute("posts", postsService.findAllDesc());

		if (user != null)
		{
			model.addAttribute("userName", user.getName());
		}
		return "index";
	}

	@GetMapping("/posts/save")
	public String postsSave()
	{
		return "posts-save";
	}

	@GetMapping("/posts/update/{id}")
	public String postsUpdate(@PathVariable Long id, Model model)
	{
		PostsResponseDto dto = postsService.findById(id);
		model.addAttribute("post", dto);

		return "posts-update";
	}
}
