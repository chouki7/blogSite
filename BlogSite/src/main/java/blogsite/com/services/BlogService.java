package blogsite.com.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blogsite.com.models.dao.BlogDao;
import blogsite.com.models.entity.Blog;

@Service
public class BlogService {
	@Autowired
	private BlogDao blogDao;
	
	// blog一覧のチェック
	// もしadminId==null 戻り値としてnull
	// findAll内容をコントローラークラスに渡す
	public List<Blog>selectAllBlogList(Long adminId) {
		if(adminId == null) {
			return null;
		} else {
			return blogDao.findAll();
		}
	}
	
	// blogの登録処理チェック
	// もし、findByBlogTitle == null 保存処理し、tureをコントローラーに返す
	// そうでない場合、falseをコントローラーに返す
	public boolean createBlog(
			String blogTitle, 
			String blogCategory, 
			String blogImage, 
			String blogArticle, 
			Long adminId) {
		if(blogDao.findByBlogTitle(blogTitle) == null) {
			blogDao.save(new Blog(
					blogTitle,
					blogCategory,
					blogImage,
					blogArticle,
					adminId));
			return true;
		} else {
			return false;
		}
	}
	
	// blog編集画面を表示する時のチェック
	// もし、blogId== null null
	// そうでない場合、findByBlogIdの情報をコントローラークラスに渡す
	public Blog blogEditCheck(Long blogId) {
		if(blogId == null) {
			return null;
		} else {
			return blogDao.findByBlogId(blogId);
		}
	}
	
	// 更新処理のチェック
	// もし、blogId== null 更新処理しない false
	// そうでない場合、blogIdを使って、編集する前のデータを取得
	// 変更 するべきところだけ、セッターを使用してデータの更新をする true
	public boolean blogUpdate(
			Long blogId,
			String blogTitle, 
			String blogCategory, 
			String blogImage, 
			String blogArticle, 
			Long adminId) {
		if(blogId == null) {
			return false;
		} else {
			Blog blogs = blogDao.findByBlogId(blogId);
			blogs.setBlogTitle(blogTitle);
			blogs.setBlogCategory(blogCategory);
			blogs.setBlogImage(blogImage);
			blogs.setBlogArticle(blogArticle);
			blogs.setAdminId(adminId);
			blogDao.save(blogs);
			return true;
		}
	}
	
	// 削除処理のチェック
	// もし、コントローラークラスにもらったblogIdがnullであれば
	// 削除できないこと false
	// そうでない場合
	// deleteByBlogIdを使用してblogの削除
	// true
	public boolean deleteBlog(Long blogId) {
		if (blogId == null) {
			return false;
		} else {
			blogDao.deleteByBlogId(blogId);
			return true;
		}
	}
}
