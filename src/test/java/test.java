import com.ymedia.dao.Impl.LoginDAOImpl;
import com.ymedia.model.LoginModel;
import org.junit.Test;

public class test {

    @Test
    public void test() throws Exception{
        LoginModel loginModel = new LoginModel();
        loginModel.setUsername("fanzhiyi");
        loginModel.setPassword("1");
        LoginDAOImpl loginDAO = new LoginDAOImpl();

        System.out.println(loginDAO.login(loginModel));
    }
}
