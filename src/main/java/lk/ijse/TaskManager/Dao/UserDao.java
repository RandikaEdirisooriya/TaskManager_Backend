package lk.ijse.TaskManager.Dao;

import lk.ijse.TaskManager.Entity.Impl.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
}
