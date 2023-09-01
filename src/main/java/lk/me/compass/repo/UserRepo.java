package lk.me.compass.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import lk.me.compass.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
    
    String FIND_USER_BY_USERNAME="select u.id,u.name,u.password,r.role_name from user u inner join role r on u.role_id=r.id where u.username=?1 and u.status=1";
    @Query(value = FIND_USER_BY_USERNAME,nativeQuery = true)
    List<Object[]> findUserByUsername(String username);

}
