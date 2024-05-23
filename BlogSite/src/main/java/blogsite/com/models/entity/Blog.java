package blogsite.com.models.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Blog {
	// blog_idの設定
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long blogId;
	
	// blog_titleの設定
	private String blogTitle;

	// blog_categoryの設定
	private String blogCategory;

	// blog_imageの設定
	private String blogImage;

	// blog_articleの設定
	private String blogArticle;
	
	// admin_idの設定
	private Long adminId;

	// 空のコンストラクタ
	public Blog() {
	}

	// コンストラクタ
	public Blog(String blogTitle, String blogCategory, String blogImage, String blogArticle, Long adminId) {
		this.blogTitle = blogTitle;
		this.blogCategory = blogCategory;
		this.blogImage = blogImage;
		this.blogArticle = blogArticle;
		this.adminId = adminId;
	}

	// ゲッターとセッター
	public Long getBlogId() {
		return blogId;
	}

	public void setBlogId(Long blogId) {
		this.blogId = blogId;
	}

	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	public String getBlogCategory() {
		return blogCategory;
	}

	public void setBlogCategory(String blogCategory) {
		this.blogCategory = blogCategory;
	}

	public String getBlogImage() {
		return blogImage;
	}

	public void setBlogImage(String blogImage) {
		this.blogImage = blogImage;
	}

	public String getBlogArticle() {
		return blogArticle;
	}

	public void setBlogArticle(String blogArticle) {
		this.blogArticle = blogArticle;
	}

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	
	
	
	
}
