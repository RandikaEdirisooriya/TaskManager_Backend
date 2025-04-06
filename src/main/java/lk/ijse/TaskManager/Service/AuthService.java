package lk.ijse.TaskManager.Service;

import lk.ijse.TaskManager.Dto.Impl.UserDto;
import lk.ijse.TaskManager.secure.JWTAuthResponse;
import lk.ijse.TaskManager.secure.SignIn;

public interface AuthService {
    JWTAuthResponse signIn(SignIn signIn);
    JWTAuthResponse signUp(UserDto userDTO);
    JWTAuthResponse refreshToken(String accessToken);
}
