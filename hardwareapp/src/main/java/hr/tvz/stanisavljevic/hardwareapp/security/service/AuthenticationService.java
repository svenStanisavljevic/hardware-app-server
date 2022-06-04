package hr.tvz.stanisavljevic.hardwareapp.security.service;

import hr.tvz.stanisavljevic.hardwareapp.security.command.LoginCommand;
import hr.tvz.stanisavljevic.hardwareapp.security.dto.LoginDTO;

import java.util.Optional;

public interface AuthenticationService {

    Optional<LoginDTO> login(LoginCommand command);

}
