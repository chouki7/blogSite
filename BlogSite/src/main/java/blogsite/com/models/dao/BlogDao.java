package blogsite.com.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import blogsite.com.models.entity.Blog;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface BlogDao extends JpaRepository<Blog, Long> {

	// 保存処理と更新処理
	Blog save(Blog blog);
	
	// SELECT * FROM products
	// 用途：blog一覧を表示させるときに使用
	List<Blog>findAll();
	
	// SELECT * FROM blog WHERE blog_title = ?
	// 用途：blog登録チェックに使用(同じblogタイトルが登録されないようにするチェックに使用)
	Blog findByBlogTitle(String blogTitle);
	
	// SELECT * FROM blog WHERE blog_id = ?
	// 用途：編集画面を表示する際に使用
	Blog findByBlogId(Long blogId);
	
	// DELETE FROM blog WHERE blog_id = ?
	// 用途：削除使用します
	void deleteByBlogId(Long blogId);
}
