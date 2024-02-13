package lt.vibenchat.demo.dto.inputValidateDto.chat;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SendChatMessageDto {
    @NotBlank(message = "{sendChatMessageDto.message.blank}")
    private String messageText;
}
