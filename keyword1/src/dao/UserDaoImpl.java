package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import Util.DBUtil;
import Util.UserException;
import model.User;


public class UserDaoImpl implements IUserDao {

	public void add(User user) {
//		//获得链接对象
//		Connection connection = DBUtil.getConnection();
//		//准备sql语句
//		String sql = "select count(*) from data where username = ?";
//		//创建语句传输对象
//		PreparedStatement preparedStatement = null;
//		ResultSet resultSet = null;
//		try {
//			preparedStatement = connection.prepareStatement(sql);
//			preparedStatement.setString(1, user.getUsername());
//			//接收结果集
//			resultSet = preparedStatement.executeQuery();
//			//遍历结果集
//			while(resultSet.next()) {
//				if (resultSet.getInt(1) > 0) {
//					throw new UserException("用户已存在") ;
//				}
//			}
//			
//			sql = "insert into data(username,password,nickname,type,status) values (?,?,?,?,?)";
//			preparedStatement = connection.prepareStatement(sql);
//			preparedStatement.setString(1, user.getUsername());
//			preparedStatement.setString(2, user.getPassword());
//			preparedStatement.setString(3, user.getNickname());
//			preparedStatement.setInt(4, user.getType());
//			preparedStatement.setInt(5, user.getStatus());
//			preparedStatement.executeUpdate();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally {
//			//关闭资源
//			DBUtil.close(resultSet);
//			DBUtil.close(preparedStatement);
//			DBUtil.close(connection);
//		}
		
	}

	
	public void delete(int id) {
//		Connection connection = DBUtil.getConnection();
//		String sql = "delete from data where id = ?";
//		PreparedStatement preparedStatement = null;
//		
//		try {
//			preparedStatement = connection.prepareStatement(sql);
//			preparedStatement.setInt(1, id);
//			preparedStatement.executeUpdate();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally {
//			DBUtil.close(preparedStatement);
//			DBUtil.close(connection);
//		}
		
	}


	public void update(User user) {
		Connection connection = DBUtil.getConnection();
		//准备sql语句
		String sql = "update words set keyword =? where id = ?";
		//创建语句传输对象
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getKeyword());
			
			preparedStatement.setInt(2, user.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
		}
	}


	public User load(int id) {
		Connection connection = DBUtil.getConnection();
		//准备sql语句
		String sql = "select * from words  where id = ?";
		//创建语句传输对象
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		User user = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				user = new User();
				user.setId(id);
				user.setContext(resultSet.getString("expl"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(resultSet);
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
		}
		return  user;
	}


	public User load(String username) {
		// TODO Auto-generated method stub
//		Connection connection = DBUtil.getConnection();
//		//准备sql语句
//		String sql = "select * from data  where username = ?";
//		//创建语句传输对象
//		PreparedStatement preparedStatement = null;
//		ResultSet resultSet = null;
//		User user = null;
//		try {
//			preparedStatement = connection.prepareStatement(sql);
//			preparedStatement.setString(1, username);
//			resultSet = preparedStatement.executeQuery();
//			while(resultSet.next()) {
//				user = new User();
//				user.setId(resultSet.getInt("id"));
//				user.setUsername(resultSet.getString("username"));
//				user.setPassword(resultSet.getString("password"));
//				user.setNickname(resultSet.getString("nickname"));
//				user.setType(resultSet.getInt("type"));
//				user.setStatus(resultSet.getInt("status"));
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally {
//			DBUtil.close(resultSet);
//			DBUtil.close(preparedStatement);
//			DBUtil.close(connection);
//		}
//		return  user;
		return null;
	}

	public List<User> load() {
		Connection connection = DBUtil.getConnection();
		//准备sql语句
		String sql = "select * from words ";
		//创建语句传输对象
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		//集合中只能放入user对象
		List<User> users = new ArrayList<User>();
		User user = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				user = new User();
				user.setId(resultSet.getInt("id"));
				user.setKeyword(resultSet.getString("keyword"));
				users.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(resultSet);
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
		}
		return  users;
	}


	public User load(String username, String password) {
//		Connection connection = DBUtil.getConnection();
//		//准备sql语句
//		String sql = "select * from data  where username = ?";
//		//创建语句传输对象
//		PreparedStatement preparedStatement = null;
//		ResultSet resultSet = null;
//		User user = null;
//		try {
//			preparedStatement = connection.prepareStatement(sql);
//			preparedStatement.setString(1, username);
//			resultSet = preparedStatement.executeQuery();
//			while(resultSet.next()) {
//				user = new User();
//				user.setId(resultSet.getInt("id"));
//				user.setUsername(resultSet.getString("username"));
//				user.setPassword(resultSet.getString("password"));
//				user.setNickname(resultSet.getString("nickname"));
//				user.setType(resultSet.getInt("type"));
//				user.setStatus(resultSet.getInt("status"));
//			}
//			if (user == null) {
//				throw new UserException("用户名不存在");
//			}
//			if(!password.equals(user.getPassword().trim()))
////			if (!user.getPassword().equals(password.trim())) 
//			{
//				throw new UserException("密码不正确");
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally {
//			DBUtil.close(resultSet);
//			DBUtil.close(preparedStatement);
//			DBUtil.close(connection);
//		}
//		return  user;
		return null;
	}
}
