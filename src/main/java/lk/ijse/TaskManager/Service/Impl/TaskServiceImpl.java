package lk.ijse.TaskManager.Service.Impl;

import jakarta.transaction.Transactional;
import lk.ijse.TaskManager.Dao.TaskDao;
import lk.ijse.TaskManager.Dto.Impl.TaskDto;
import lk.ijse.TaskManager.Entity.Impl.Task;
import lk.ijse.TaskManager.Exceptions.DataPersistException;
import lk.ijse.TaskManager.Service.TaskService;
import lk.ijse.TaskManager.Utill.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskDao taskDao;

    @Autowired
    private Mapping taskMapping;


    @Override
    public void saveTask(TaskDto taskDto) {
        Task savedTask = taskDao.save(taskMapping.toTaskEntity(taskDto));
        if (savedTask == null) {
            throw new DataPersistException("Task not saved");
        }
    }


    @Override
    public List<TaskDto> getAllTasks() {
        List<Task> taskList = taskDao.findAll();
        return taskList.stream()
                .map(taskMapping::toTaskDto)
                .collect(Collectors.toList());
    }


    @Override
    public TaskDto getTaskById(Long id) {
        Optional<Task> taskOptional = taskDao.findById(id);
        if (taskOptional.isPresent()) {
            return taskMapping.toTaskDto(taskOptional.get());
        } else {
            throw new DataPersistException("Task not found with ID: " + id);
        }
    }


    @Override
    public void updateTask(Long id, TaskDto taskDto) {
        Optional<Task> existing = taskDao.findById(id);
        if (existing.isPresent()) {
            Task task = taskMapping.toTaskEntity(taskDto);
            task.setId(id);
            taskDao.save(task);
        } else {
            throw new DataPersistException("Cannot update. Task ID not found: " + id);
        }
    }


    @Override
    public void deleteTask(Long id) {
        Optional<Task> taskOptional = taskDao.findById(id);
        if (taskOptional.isPresent()) {
            taskDao.deleteById(id);
        } else {
            throw new DataPersistException("Cannot delete. Task not found with ID: " + id);
        }
    }
}
