package per.qy.auth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import per.qy.auth.entity.SystemAccount;

import java.util.List;

public interface SystemAccountDao extends JpaRepository<SystemAccount, Integer> {

    List<SystemAccount> findByAccount(String account);
}
