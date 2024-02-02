package lt.vibenchat.demo.dto.inputValidateDto;

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
public class LoginUserDto {
    @NotBlank(message = "{registerUserDto.username.blank}")
    private String username;
    @NotBlank(message = "{registerUserDto.password.blank}")
    @Size(min = 8, message = "{registerUserDto.password.size}")
    private String password;
}
