package lt.vibenchat.demo.dto.inputValidateDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterUserDto {
    @NotBlank(message = "{registerUserDto.username.blank}")
    private String username;
    @Email(message = "{registerUserDto.email.correct}")
    private String email;
    @Size(min = 9, message = "{registerUserDto.password.size}")
    private String password;
    private String confirmPassword;
}
