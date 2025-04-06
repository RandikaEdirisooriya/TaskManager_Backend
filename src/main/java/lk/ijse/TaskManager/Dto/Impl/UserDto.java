package lk.ijse.TaskManager.Dto.Impl;

import lk.ijse.TaskManager.Dto.UserStatus;
import lk.ijse.TaskManager.Entity.ENUM.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto implements UserStatus {
    private Long id;
    private String email;
    private String name;
    private String password;
    private Role role;
}
