import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ymedia.dao.Impl.LoginDAOImpl;
import com.ymedia.model.LoginModel;
import org.junit.Test;

import java.util.List;

public class test {

    @Test
    public void test() throws Exception{
        LoginModel loginModel = new LoginModel();
        loginModel.setUsername("fanzhiyi");
        loginModel.setPassword("1");
        LoginDAOImpl loginDAO = new LoginDAOImpl();

        System.out.println(loginDAO.login(loginModel));
    }

    @Test
    public void testjson(){
        Gson gson = new Gson();
        LoginModel loginModel = new LoginModel();
        loginModel.setUsername("lishuopu");
        loginModel.setPassword("123");
        String str = gson.toJson(loginModel);
        System.out.println(str);
        loginModel = gson.fromJson(str, LoginModel.class);
        System.out.println(loginModel.getUsername());
        System.out.println(loginModel.getPassword());
    }
}
