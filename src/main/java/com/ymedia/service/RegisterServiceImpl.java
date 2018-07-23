package com.ymedia.service;

import com.ymedia.dao.RegisterDAO;
import com.ymedia.model.RegisterModel;
import com.ymedia.utils.Constant;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RegisterServiceImpl implements RegisterService{

    @Autowired
    RegisterDAO registerDAO;

    @Override
    public String register(RegisterModel registerModel) throws Exception{

        List<Map<String, Object>> list = registerDAO.queryUser(registerModel.getUsername());

        if(list.size() == 0 || StringUtils.isBlank(list.get(0).get("username").toString())){

            int result = registerDAO.register(registerModel);

            if(result==1) {
                return "success";
            }else {
                return "fail";
            }
        }else {
            return "exist";
        }

    }
}
