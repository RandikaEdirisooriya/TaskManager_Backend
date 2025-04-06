package lk.ijse.TaskManager.Service;

import lk.ijse.TaskManager.Dto.Impl.UserDto;
import lk.ijse.TaskManager.Dto.UserStatus;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDTO);
    List<UserDto> getAllUsers();
    UserStatus getUser(Long userId);
    void deleteUser(Long userId);
    void updateUser(Long userId, UserDto userDTO);
    UserDetailsService userDetailsService();

}
