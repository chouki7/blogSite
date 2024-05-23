package blogsite.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import blogsite.com.models.entity.Admin;
import blogsite.com.services.BlogService;
import jakarta.servlet.http.HttpSession;

@Controller
public class BlogDeleteController {
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private HttpSession session;
	
	@PostMapping("/blog/delete")
	public String blogDelete(Long blogId) {
		// セッションからログインしている人の情報を取得
		Admin admin = (Admin) session.getAttribute("loginAdminInfo");
		// もし、admin==null ログイン画面にリダイレクトする
		if (admin == null) {
			return "redirect:/admin/login";
		} else {
			// もし、deleteBlogの結果がtrueだったら
			if(blogService.deleteBlog(blogId)) {
				//blog一覧ページにリダイレクトする
				return "redirect:/blog/list";
			} else {
				// そうでない場合 blog編集画面にリダイレクトする
				return "redirect:/blog/edit"+blogId;
			}
		}
	}
}
