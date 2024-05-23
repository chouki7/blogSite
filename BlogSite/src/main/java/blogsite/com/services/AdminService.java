package blogsite.com.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blogsite.com.models.dao.AdminDao;
import blogsite.com.models.entity.Admin;

@Service
public class AdminService {
	@Autowired
	private AdminDao adminDao;
	
	// 登録処理
	// もし、findByAdminEmail==nullだったら登録処理処理します
	// saveメソッドを使用して登録処理をする
	// 保存ができたらtrue
	// そうでない場合、保存処理失敗 false
	
	public boolean createAdmin(String adminEmail,String adminName,String password) {
		if(adminDao.findByAdminEmail(adminEmail)==null) {
			adminDao.save(new Admin(adminEmail,adminName,password));
			return true;
		} else {
			return false;
		}
	}
	
	//ログイン処理
	//もし、emailとpasswordがfindByAdminEmailAndPasswordを使用して存在しなかった場合==nullの場合、
	//その場合、存在しないnullであることをコントローラークラスに知らせる
	//そうでない場合、ログインしている人の情報をコントローラークラスに渡す
	public Admin loginCheck(String adminEmail, String password) {
		Admin admin = adminDao.findByAdminEmailAndPassword(adminEmail, password);
		if(admin == null) {
			return null;
		} else {
			return admin;
		}
	}
}
