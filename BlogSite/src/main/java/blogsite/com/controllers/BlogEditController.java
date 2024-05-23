package blogsite.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import blogsite.com.models.entity.Admin;
import blogsite.com.models.entity.Blog;
import blogsite.com.services.BlogService;
import jakarta.servlet.http.HttpSession;

@Controller
public class BlogEditController {
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private HttpSession session;
	
	// blog編集画面を表示
	@GetMapping("/blog/edit/{blogId}")
	public String getBlogEditPage(@PathVariable Long blogId, Model model) {
		// セッションからログインしている人の情報をadminという変数に格納
		Admin admin = (Admin) session.getAttribute("loginAdminInfo");
		// もしadmin==null ログイン画面にリダイレクトする
		if (admin == null) {
			return "redirect:/admin/login";
		} else {
			// 編集画面を表示させる情報を変数に格納 blogs
			Blog blogs =  blogService.blogEditCheck(blogId);
			// もし、blog == nullだったら、blog一覧ページにリダイレクトする
			// そうでない場合、blog編集画面をに編集する内容を渡す
			// blog編集画面を表示
			if(blogs == null) {
				return "redirect:/blog/list";
			} else {
				model.addAttribute("adminName", admin.getAdminName());
				model.addAttribute("blogs",blogs);
				return "blog_edit.html";
			}
		}

	}
}
