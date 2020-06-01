package dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.sun.org.apache.xpath.internal.operations.Bool;

import bean.NoteBean;
import util.DBUtil;

public class NoteDao {
	public Boolean insert(String title, String content, String noteTime, String tel) {
		int id = new UserDao().getIDbyTel(tel);// 根据用户电话获取用户id
		if (id < 1)//
			return false;
		Connection c = DBUtil.getConnection();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
		String nowTime = sdf.format(new java.util.Date());// 获取当前时间

		try {
			PreparedStatement pst = (PreparedStatement) c
					.clientPrepareStatement("insert into note_table(title,content,note_time,user_id)values(?,?,?,?) ");
			pst.setString(1, title);
			pst.setString(2, content);
			pst.setString(3, nowTime);
			pst.setInt(4, id);
			pst.execute();
			DBUtil.close(c, pst, null);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public NoteBean getNoteByID(int id) {
		NoteBean note = new NoteBean();
		Connection c = DBUtil.getConnection();
		try {
			PreparedStatement pst = (PreparedStatement) c.prepareStatement("select * from note_table where id=?");
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.first()) {
				note.setId(id);
				note.setTitle(rs.getString("title"));
				note.setContent(rs.getString("content"));
				note.setCreateTime(rs.getString("create_time"));
				note.setUpdateTime(rs.getString("update_time"));
				note.setNoteTime(rs.getString("note_time"));
				note.setUserId(rs.getString("user_id"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return note;
	}

	// 根据id修改一条备忘录
	public boolean ModifyNote(int id, String title, String content, String noteTime) {
		if (id < 1) // 如果用户id小于1表示用户不存在，返回false
			return false;
		Connection c = DBUtil.getConnection();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
		String nowTime = sdf.format(new java.util.Date()); //

		try {
			PreparedStatement pst = (PreparedStatement) c
					.prepareStatement("update note_table set title=?,content=?,note_time=?,update_time=? where id=?");
			pst.setString(1, title);
			pst.setString(2, content);
			pst.setString(3, noteTime);
			pst.setString(4, nowTime);
			pst.setInt(5, id);
			pst.execute();
			DBUtil.close(c, pst, null);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	// 根据电话号码，返回用户的所有备忘录
	public ArrayList<NoteBean> getAllNotes(String tel) {
		ArrayList<NoteBean> all = new ArrayList<NoteBean>();
		Connection c = DBUtil.getConnection();
		int id = new UserDao().getIDbyTel(tel);// 根据电话号码获取id
		try {
			PreparedStatement pst = (PreparedStatement) c.prepareStatement("select * from note_table where user_id=?");
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				NoteBean note = new NoteBean();
				note.setId(rs.getInt("id"));
				note.setTitle(rs.getString("title"));
				note.setContent(rs.getString("content"));
				note.setCreateTime(rs.getString("create_time"));
				note.setUpdateTime(rs.getString("update_time"));
				note.setNoteTime(rs.getString("note_time"));
				note.setUserId(rs.getString("user_id"));
				all.add(note);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return all;
	}

	// 根据Id删除一条备忘录，删除成功返回true,失败返回false
	public boolean deleteById(int id) {
		Connection c = DBUtil.getConnection();
		try {
			PreparedStatement pst = (PreparedStatement) c.prepareStatement("delete from note_table where id=?");
			pst.setInt(1, id);
			pst.execute();
			DBUtil.close(c, pst, null);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

}
