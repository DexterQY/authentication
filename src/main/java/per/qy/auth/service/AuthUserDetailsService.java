package per.qy.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import per.qy.auth.dao.SystemAccountDao;
import per.qy.auth.dao.SystemAccountRoleDao;
import per.qy.auth.entity.SystemAccount;
import per.qy.auth.entity.SystemAccountRole;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Component
public class AuthUserDetailsService implements UserDetailsService {

    @Autowired
    private SystemAccountDao systemAccountDao;

    @Autowired
    private SystemAccountRoleDao systemAccountRoleDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<SystemAccount> list = systemAccountDao.findByAccount(username);
        if (list != null && !list.isEmpty()) {
            SystemAccount account = list.get(0);
            List<SystemAccountRole> roleList = systemAccountRoleDao.findByAccountId(account.getId());
            Collection<SimpleGrantedAuthority> authorities = new HashSet<>();
            if (roleList != null && !roleList.isEmpty()) {
                for (SystemAccountRole accountRole : roleList) {
                    authorities.add(new SimpleGrantedAuthority(String.valueOf(accountRole.getRoleId())));
                }
            }
            return new User(username, account.getPassword(),
                    account.getState() == 1, true, true, true,
                    authorities);
        }
        return null;
    }
}
