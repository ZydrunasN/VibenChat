package lt.vibenchat.demo.dto.inputValidateDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lt.vibenchat.demo.validation.RepeatPassword;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@RepeatPassword
public class RegisterUserDto {
    @NotBlank(message = "{registerUserDto.username.blank}")
    private String username;
    @NotBlank(message = "{registerUserDto.email.blank}")
    @Email(message = "{registerUserDto.email.correct}")
    private String email;
    @NotBlank(message = "{registerUserDto.password.blank}")
    @Size(min = 8, message = "{registerUserDto.password.size}")
    private String password;
    @NotBlank(message = "{registerUserDto.confirmPassword.blank}")
    private String confirmPassword;
}
