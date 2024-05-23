package blogsite.com.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import blogsite.com.models.entity.Admin;
import blogsite.com.services.BlogService;
import jakarta.servlet.http.HttpSession;

@Controller
public class BlogRegisterController {
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private HttpSession session;
	
	// blog登録画面の表示
	@GetMapping("/blog/register")
	public String getBlogRegisterPage(Model model) {
		// セッションからログインしている人の情報をadminという変数に格納
		Admin admin = (Admin) session.getAttribute("loginAdminInfo");
		// もしadmin==null ログイン画面にリダイレクトする
		// そうでない場合、ログインしている人の名前を画面に渡す
		// blog登録のhtmlを表示させる
		if (admin == null) {
			return "redirect:/admin/login";
		} else {
			model.addAttribute("adminName",admin.getAdminName());
			return "blog_register.html";
		}
	}
	
	// blog登録処理
	@PostMapping("/blog/register/process")
	public String blogRegisterProcess(
			@RequestParam String blogTitle, 
			@RequestParam String blogCategory, 
			@RequestParam MultipartFile blogImage, 
			@RequestParam String blogArticle) {
		// セッションからログインしている人の情報をadminという変数に格納
		Admin admin = (Admin) session.getAttribute("loginAdminInfo");
		
		// もしadmin==null ログイン画面にリダイレクトする
		// そうでない場合、画像のファイル名を取得
		// 画像のアップロード
		// もし、同じファイルの名前がなかったら保存処理
		// blog一覧画面にリダイレクトする
		// そうでない場合、blog登録画面にとどまります
		
		if(admin == null) {
			return "redirect:/admin/login";
		} else {
			// 画像のファイル名を取得
			String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-").format(new Date())+blogImage.getOriginalFilename();
			
			// ファイルの保存処理
			try {
				Files.copy(blogImage.getInputStream(), Path.of("src/main/resources/static/blog-img/"+fileName));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if(blogService.createBlog(blogTitle, blogCategory, fileName, blogArticle, admin.getAdminId())) {
				return "redirect:/blog/list";
			} else {
				return "blog_register.html";
			}
		}
		
	}
}
