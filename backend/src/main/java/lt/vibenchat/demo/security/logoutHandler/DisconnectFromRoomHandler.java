package lt.vibenchat.demo.security.logoutHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lt.vibenchat.demo.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
public class DisconnectFromRoomHandler implements LogoutHandler {

    RoomService roomService;

    @Autowired
    public DisconnectFromRoomHandler(RoomService roomService) {
        this.roomService = roomService;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        roomService.disconnectFromRoom(request.getSession());
    }
}
