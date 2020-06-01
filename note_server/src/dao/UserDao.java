package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

import bean.UserBean;
import util.DBUtil;

public class UserDao {
	// 向user表中插入一条数据（注册）
	public boolean regist(UserBean user) {
		com.mysql.jdbc.Connection c = DBUtil.getConnection();
		try {
			PreparedStatement pst = (PreparedStatement) c
					.prepareStatement("insert into user(name,password,tel,qq,wechat)values(?,?,?,?,?)");
			pst.setString(1, user.getName());
			pst.setString(2, user.getPassword());
			pst.setString(3, user.getTel());
			pst.setString(4, user.getQq());
			pst.setString(5, user.getWechat());
			pst.execute();
			DBUtil.close(c, pst, null);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	// 新建方法Login 用于处理登陆业务逻辑，登陆成功返回true，失败返回false
	public boolean login(String tel, String password) {
		com.mysql.jdbc.Connection c = DBUtil.getConnection();
		PreparedStatement pst = null;

		try {
			pst = (PreparedStatement) c.prepareStatement("select password from user where tel=?");
			pst.setString(1, tel);
			// 执行查询语句
			ResultSet rs = pst.executeQuery();
			if (rs.first()) {
				String passwordInDb = rs.getString("password");
				if (passwordInDb.equals(password))
					return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(c, pst, null);
		}
		return false;

	}

	public int getIDbyTel(String tel) {
		com.mysql.jdbc.Connection c = DBUtil.getConnection();
		PreparedStatement pst = null;
		int id = 0;
		try {
			pst = (PreparedStatement) c.prepareStatement("select id from user where tel=?");
			pst.setString(1, tel);
			ResultSet rs = pst.executeQuery();
			if (rs.first()) {
				id = rs.getInt("id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(c, pst, null);
		}
		return id;
	}

}
