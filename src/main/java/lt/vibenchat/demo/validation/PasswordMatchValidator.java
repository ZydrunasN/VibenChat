package lt.vibenchat.demo.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lt.vibenchat.demo.dto.inputValidateDto.RegisterUserDto;

import java.util.Objects;

public class PasswordMatchValidator implements ConstraintValidator<RepeatPassword, RegisterUserDto> {

    @Override
    public boolean isValid(RegisterUserDto registerUserDto, ConstraintValidatorContext context) {
        return Objects.nonNull(registerUserDto.getPassword()) && registerUserDto.getPassword().equals(registerUserDto.getConfirmPassword());
    }
}