package hr.tvz.stanisavljevic.hardwareapp.hardware;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("hardware")
@CrossOrigin(origins = "http://localhost:4200")
public class HardwareController {

    private final HardwareService hardwareService;

    public HardwareController(HardwareService hardwareService) {
        this.hardwareService = hardwareService;
    }


    @GetMapping
    public List<HardwareDTO> getAllHardware() {
        return hardwareService.findAll();
    }

    @GetMapping(value = "{code}")
    public HardwareDTO getHardwareByCode (@PathVariable String code){
        if (hardwareService.findByCode(code) != null) {
            return hardwareService.findByCode(code);
        }
        else ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return null;
    }

    @Secured("ROLE_ADMIN")
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<HardwareDTO> save(@Valid @RequestBody final HardwareCommand command) {
        return
        hardwareService.saveHardware(command)
                .map(
                        hardwareDTO -> ResponseEntity.status(HttpStatus.CREATED).body(hardwareDTO)
                )
                .orElseGet(
                        () -> ResponseEntity.status(HttpStatus.CONFLICT).build()
                );
    }

    @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "{code}")
    public void delete(@PathVariable String code) {
        hardwareService.deleteByCode(code);
    }

    @Secured({"ROLE_ADMIN", "ROLE_UPDATER"})
    @PutMapping(value = "{code}")
    public ResponseEntity<HardwareDTO> update(@PathVariable String code, @Valid @RequestBody final HardwareCommand updateHardwareCommand) {
        return hardwareService.update(code, updateHardwareCommand)
                .map(ResponseEntity::ok)
                .orElseGet(
                        () -> ResponseEntity.notFound().build()
                );
    }
}

/*
{
    "name" : "NVIDIA RTX 3080",
    "code" : "692345",
    "price" : 2399.99,
    "type" : "GPU",
    "inStock" : 69
}
 */
