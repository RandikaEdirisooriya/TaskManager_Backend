package lk.ijse.TaskManager.Utill;

import lk.ijse.TaskManager.Dto.Impl.TaskDto;
import lk.ijse.TaskManager.Dto.Impl.UserDto;
import lk.ijse.TaskManager.Entity.Impl.Task;
import lk.ijse.TaskManager.Entity.Impl.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;
    public Task toTaskEntity(TaskDto taskDto) {
        return modelMapper.map(taskDto, Task.class);
    }
    public TaskDto toTaskDto(Task task) {
        return modelMapper.map(task, TaskDto.class);
    }

    public User toUserEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }
    public UserDto toUserDTO(User user) {
        return modelMapper.map(user, UserDto.class);
    }
    public List<UserDto> asUserDTOList(List<User> users) {
        return modelMapper.map(users, new TypeToken<List<UserDto>>() {}.getType());
    }
}
