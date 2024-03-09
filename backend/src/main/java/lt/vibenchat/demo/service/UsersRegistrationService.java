package lt.vibenchat.demo.service;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import lt.vibenchat.demo.dto.entityDto.UserDto;
import lt.vibenchat.demo.pojo.Authority;
import lt.vibenchat.demo.pojo.User;
import lt.vibenchat.demo.repository.AuthorityRepository;
import lt.vibenchat.demo.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersRegistrationService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    public void register(UserDto userDto) throws DataIntegrityViolationException {
        final Set<Authority> authorities = authorityRepository.findAll().stream()
                .filter(authority -> authority.getName().equals("USER"))
                .collect(Collectors.toSet());

        userRepository.save(
                User.builder()
                        .email(userDto.getEmail())
                        .username(userDto.getUsername())
                        .password(new BCryptPasswordEncoder().encode(userDto.getPassword()))
                        .authorities(authorities)
                        .build()
        );
    }
}