package lk.ijse.TaskManager.Utill;

import lk.ijse.TaskManager.Dto.Impl.TaskDto;
import lk.ijse.TaskManager.Entity.Impl.Task;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

}
