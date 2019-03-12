package per.qy.auth.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import per.qy.auth.dao.SystemAccountDao;
import per.qy.auth.entity.SystemAccount;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class RegisterController {

    @Autowired
    private SystemAccountDao systemAccountDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    @ResponseBody
    public String register(HttpServletRequest request, HttpServletResponse response, String account,
                           String password) {
        if(StringUtils.isBlank(account)){
            return "账号不能为空";
        }
        if(StringUtils.isBlank(password)){
            return "账号不能为空";
        }
        SystemAccount systemAccount = new SystemAccount();
        systemAccount.setAccount(account);
        systemAccount.setPassword(passwordEncoder.encode(password));
        systemAccountDao.save(systemAccount);
        return "注册成功";
    }
}
