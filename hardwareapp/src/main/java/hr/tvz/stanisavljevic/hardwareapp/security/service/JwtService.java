package hr.tvz.stanisavljevic.hardwareapp.security.service;

import hr.tvz.stanisavljevic.hardwareapp.security.domain.User;

public interface JwtService {

    boolean authenticate(String token);

    String createJwt(User user);

}
