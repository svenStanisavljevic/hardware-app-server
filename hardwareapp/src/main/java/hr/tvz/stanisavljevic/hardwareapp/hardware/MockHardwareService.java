package hr.tvz.stanisavljevic.hardwareapp.hardware;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MockHardwareService implements HardwareService, Serializable {

    private final HardwareRepository hardwareRepository;

    public MockHardwareService(HardwareRepository hardwareRepository) {
        this.hardwareRepository = hardwareRepository;
    }


    @Override
    public List<HardwareDTO> findAll() {
        return hardwareRepository.findAll().stream().map(this::mapHardwareToDTO).collect(Collectors.toList());
    }

    @Override
    public HardwareDTO findByCode(String code) {
        return hardwareRepository.findByCode(code).map(this::mapHardwareToDTO).orElse(null);
    }

    @Override
    public Optional<HardwareDTO> saveHardware(HardwareCommand command) {
        return hardwareRepository.saveNewHardware(command).map(this::mapHardwareToDTO);
    }

    @Override
    public void deleteByCode(String code) {
        this.hardwareRepository.removeByCode(code);
    }

    @Override
    public Optional<HardwareDTO> update(String code, HardwareCommand command) {
        this.hardwareRepository.updateHardware(code, command);
        return null;
    }


    private HardwareDTO mapHardwareToDTO(Hardware hardware){
        HardwareDTO helpDTO = new HardwareDTO(hardware.getName(), hardware.getPrice(), hardware.getCode(), hardware.getStock());
        return helpDTO;
    }

}
