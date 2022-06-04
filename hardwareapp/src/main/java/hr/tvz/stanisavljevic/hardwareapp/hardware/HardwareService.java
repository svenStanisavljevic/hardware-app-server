package hr.tvz.stanisavljevic.hardwareapp.hardware;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface HardwareService {

    List<HardwareDTO> findAll();

    HardwareDTO findByCode(String code);

    Optional<HardwareDTO> saveHardware(HardwareCommand command);

    void deleteByCode(String code);

    Optional<HardwareDTO> update(String code, HardwareCommand command);

}
