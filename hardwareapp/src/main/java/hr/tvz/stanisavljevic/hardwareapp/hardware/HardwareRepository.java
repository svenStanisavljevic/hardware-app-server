package hr.tvz.stanisavljevic.hardwareapp.hardware;

import java.util.List;
import java.util.Optional;

public interface HardwareRepository {

    List<Hardware> findAll();

    Optional<Hardware> findByCode(String code);

    Optional<Hardware> saveNewHardware(HardwareCommand hardwareCommand);

    void removeByCode(String code);

    Optional<List<Hardware>> searchHardware(String searchTerm);

    Optional<Hardware> updateHardware(String code, HardwareCommand command);
}
