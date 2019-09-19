import java.util.ArrayList;
import java.util.List;

import dao.UserDaoImpl;
import model.User;

public class a {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserDaoImpl userDaoImpl=new UserDaoImpl();
		List<User> lists=new ArrayList<User>();
		List<String> lists1=new ArrayList<String>();
		lists=userDaoImpl.load();
		for (User user :lists)
		{
			lists1.add(user.getId()+"#"+user.getKeyword());
		} 
		for(String st:lists1)
		{
			System.out.println(st);
		}
		System.out.println("11");
	}

}
