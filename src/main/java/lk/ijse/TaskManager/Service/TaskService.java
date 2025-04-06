package lk.ijse.TaskManager.Service;

import lk.ijse.TaskManager.Dto.Impl.TaskDto;

import java.util.List;

public interface TaskService {
    void saveTask(TaskDto taskDto);
    List<TaskDto> getAllTasks();
    TaskDto getTaskById(Long id);
    void updateTask(Long id, TaskDto taskDto);
    void deleteTask(Long id);
}
