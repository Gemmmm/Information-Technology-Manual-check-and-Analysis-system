import java.io.IOException;

import dao.UserDaoImpl;
import keyword.WordUtil;
import model.User;

public class testkeywor {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		User user=new User();
		UserDaoImpl userDaoImpl=new UserDaoImpl();
		//user =userDaoImpl.load(1);
		String a="";
		//System.out.println(a);
		for (int i=1;i<=251;i++)
		{
			user =userDaoImpl.load(i);
			user.setKeyword(WordUtil.getStr(user.getContext()));
			userDaoImpl.update(user);
		}
		System.out.println("chenggong");
	}

}
