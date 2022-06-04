package hr.tvz.stanisavljevic.hardwareapp.hardware;

import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.*;

@Repository
public class MockHardwareRepository implements HardwareRepository, Serializable {

    private List<Hardware> MOCKED_HARDWARE = new ArrayList<>(Arrays.asList(
            new Hardware("Intel Core i7-12700K", "123456", (long) 354.99, "CPU", 561),
            new Hardware("AMD Threadripper 3990X", "521341", (long) 5018.06, "CPU", 141),
            new Hardware("Corsair Vengeance LPX 16 GB", "222333", (long) 77.98, "RAM", 1213),
            new Hardware("Samsung 970 Evo Plus 1 TB", "412311", (long) 114.99, "STORAGE", 1145),
            new Hardware("Corsair HX Platinum", "231698", (long) 184.99, "OTHER", 512),
            new Hardware("Corsair SF", "654981", (long) 166.09, "OTHER", 13)
    ));

    @Override
    public List<Hardware> findAll() {
        return MOCKED_HARDWARE;
    }

    @Override
    public Optional<Hardware> findByCode(final String code) {
        return MOCKED_HARDWARE.stream().filter(it -> Objects.equals(it.getCode(), code)).findAny();
    }

    @Override
    public Optional<Hardware> saveNewHardware (HardwareCommand hardwareCommand) {

        Optional<Hardware> optionalHardware = MOCKED_HARDWARE
                .stream()
                .filter(h -> Objects.equals(h.getCode(), hardwareCommand.getCode())).findAny();

        Optional<Hardware> hardware = Optional.empty();
        if (optionalHardware.isEmpty()) {
            hardware = Optional.of(new Hardware(hardwareCommand.getName(), hardwareCommand.getCode(),
                    hardwareCommand.getPrice(), hardwareCommand.getType().toString(), hardwareCommand.getStock()));
            MOCKED_HARDWARE.add(hardware.get());
        }

        return hardware;
    }


    @Override
    public void removeByCode(String code) {
        MOCKED_HARDWARE.removeIf(hardware -> hardware.getCode().equals(code));
    }

    @Override
    public Optional<List<Hardware>> searchHardware(String searchTerm) {
        return Optional.empty();
    }

    @Override
    public Optional<Hardware> updateHardware(String code, HardwareCommand command) {
        return Optional.empty();
    }


}
