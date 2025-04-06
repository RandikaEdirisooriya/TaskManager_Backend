package lk.ijse.TaskManager.Service.Impl;

import jakarta.transaction.Transactional;
import lk.ijse.TaskManager.CustomStatusCodes.SelectedErrorStatus;
import lk.ijse.TaskManager.Dao.UserDao;
import lk.ijse.TaskManager.Dto.Impl.UserDto;
import lk.ijse.TaskManager.Dto.UserStatus;
import lk.ijse.TaskManager.Entity.Impl.Task;
import lk.ijse.TaskManager.Entity.Impl.User;
import lk.ijse.TaskManager.Exceptions.DataPersistException;
import lk.ijse.TaskManager.Exceptions.UserNotFoundException;
import lk.ijse.TaskManager.Service.UserService;
import lk.ijse.TaskManager.Utill.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private Mapping mapping;
    @Override
    public void saveUser(UserDto userDTO) {
        User savedUser =
                userDao.save(mapping.toUserEntity(userDTO));
        if (savedUser == null) {
            throw new DataPersistException("User not saved");
        }
    }
    @Override
    public List<UserDto> getAllUsers() {
        List<User> allUsers = userDao.findAll();
        return mapping.asUserDTOList(allUsers);
    }

    @Override
    public UserStatus getUser(Long userId) {
        if(userDao.existsById(userId)){
            User selectedUser = userDao.getReferenceById(userId);
            return mapping.toUserDTO(selectedUser);
        }else {
            return new SelectedErrorStatus(2, "User with id " + userId + " not found");
        }
    }

    @Override
    public void deleteUser(Long userId) {
        Optional<User> existedUser = userDao.findById(userId);
        if(!existedUser.isPresent()){
            throw new DataPersistException("User with id " + userId + " not found");
        }else {
            userDao.deleteById(userId);
        }
    }

    @Override
    public void updateUser(Long userId, UserDto userDTO) {
        Optional<User> existingUserOpt = userDao.findById(userId);
        if(existingUserOpt.isPresent()){
            User user = mapping.toUserEntity(userDTO);
            user.setId(userId);
            userDao.save(user);
        }else {
            throw new DataPersistException("Cannot update. User ID not found: " + userId);

        }
    }

    @Override
    public UserDetailsService userDetailsService() {
        return userEmail ->
                userDao.findByEmail(userEmail)
                        .orElseThrow(()-> new UserNotFoundException("User Not Found"));
    }

}
