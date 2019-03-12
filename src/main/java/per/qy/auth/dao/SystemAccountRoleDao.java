package per.qy.auth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import per.qy.auth.entity.SystemAccountRole;

import java.util.List;

public interface SystemAccountRoleDao extends JpaRepository<SystemAccountRole, Integer> {

    List<SystemAccountRole> findByAccountId(int accountId);
}
