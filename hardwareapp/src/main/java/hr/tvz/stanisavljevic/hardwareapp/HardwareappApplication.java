package hr.tvz.stanisavljevic.hardwareapp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@SpringBootApplication
public class HardwareappApplication {

    public static void main(String[] args) {
        SpringApplication.run(HardwareappApplication.class, args);
    }
}
