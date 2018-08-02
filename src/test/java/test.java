import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ymedia.dao.Impl.LoginDAOImpl;
import com.ymedia.model.LoginModel;
import com.ymedia.model.PersonInfoModel;
import com.ymedia.utils.Bean2Map;
import freemarker.ext.beans.HashAdapter;
import org.apache.commons.collections.map.HashedMap;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class test {

    //@Test
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

    @Test
    public void test1() throws Exception{
        Map<String, Object> map = new HashMap<>();
        map.put("name","123");
        map.put("username","234");

        Bean2Map bean2Map = new Bean2Map();
        PersonInfoModel personInfoModel = bean2Map.toBean(PersonInfoModel.class,map);
        System.out.println(personInfoModel.getName());
    }
}
