package lk.ijse.TaskManager.CustomStatusCodes;

import lk.ijse.TaskManager.Dto.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectedErrorStatus implements UserStatus {
    private int statusCode;
    private String statusMessage;
}
