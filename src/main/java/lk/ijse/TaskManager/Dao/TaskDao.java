package lk.ijse.TaskManager.Dao;

import lk.ijse.TaskManager.Entity.Impl.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskDao extends JpaRepository<Task,Long> {
}
