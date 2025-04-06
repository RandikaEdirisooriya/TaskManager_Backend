package lk.ijse.TaskManager.Controller;

import lk.ijse.TaskManager.Dto.Impl.TaskDto;
import lk.ijse.TaskManager.Dto.Impl.UserDto;
import lk.ijse.TaskManager.Dto.UserStatus;
import lk.ijse.TaskManager.Exceptions.DataPersistException;
import lk.ijse.TaskManager.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@CrossOrigin
@RequiredArgsConstructor
public class UserController {
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;

    @PostMapping(value = "/save", produces = "application/json")
    public ResponseEntity<Void> saveTask(@RequestBody UserDto userDto) {
        try {
            String rawPassword = userDto.getPassword();
            String encodedPassword = passwordEncoder.encode(rawPassword);
            userDto.setPassword(encodedPassword);
            userService.saveUser(userDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserStatus getSelectedUser(@PathVariable("userId") Long userId){
        return userService.getUser(userId);
    }
    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") Long userId){
        try {

            userService.deleteUser(userId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (DataPersistException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDto> getAllUsers(){
        return userService.getAllUsers();
    }

    @PutMapping(value = "/{userId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Void> updateUser(@PathVariable("userId") Long userId, @RequestBody UserDto userDto) {
        try {
            userService.updateUser(userId, userDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
