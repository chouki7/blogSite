package blogsite.com.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import blogsite.com.models.entity.Admin;
import blogsite.com.models.entity.Blog;
import blogsite.com.services.BlogService;
import jakarta.servlet.http.HttpSession;

@Controller
public class BlogListController {
	@Autowired
	private HttpSession session;
	
	@Autowired
	private BlogService blogService;
	
	// Blog一覧画面を表示する
	@GetMapping("/blog/list")
	public String getBlogList(Model model) {
		// セッションからログインしている人の情報を取得
		Admin admin = (Admin) session.getAttribute("loginAdminInfo");
		// もし、admin==null ログイン画面にリダイレクトする
		// そうでない場合、Blog一覧画面のhtmlを表示して、ログインしている人の情報をBlog一覧画面に表示
		if (admin == null) {
			return "redirect:/admin/login";
		} else {
			// blogの情報を取得する
			List<Blog> blogList = blogService.selectAllBlogList(admin.getAdminId());
			model.addAttribute("adminName", admin.getAdminName());
			model.addAttribute("blogList", blogList);
			return "blog_list.html";
		}
	}
}
