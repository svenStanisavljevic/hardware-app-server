package hr.tvz.stanisavljevic.hardwareapp.hardware;

import org.springframework.context.annotation.Primary;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Primary
@Repository
public class JdbcHardwareRepository implements HardwareRepository {

    private static final String SELECT_ALL = "SELECT * FROM hardware ";

    private final JdbcTemplate jdbc;
    private final SimpleJdbcInsert insert;

    public JdbcHardwareRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
        this.insert = new SimpleJdbcInsert(jdbc).withTableName("hardware")
                .usingGeneratedKeyColumns("id");
    }

    private Hardware mapRowToHardware(ResultSet rs, int rowNum) throws SQLException {
        return new Hardware(rs.getString("name"), rs.getString("code"),
                rs.getLong("price"), rs.getString("type"),
                rs.getInt("inStock"));
    }

    @Override
    public List<Hardware> findAll() {
        return jdbc.query(SELECT_ALL, this::mapRowToHardware);
    }

    @Override
    public Optional<Hardware> findByCode(String code) {
        try {
            return Optional.ofNullable(
                    jdbc.queryForObject(SELECT_ALL + "WHERE code = ?", this::mapRowToHardware, code)
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Hardware> saveNewHardware(HardwareCommand hardwareCommand) {
        Hardware hardware = new Hardware
                (hardwareCommand.getName(), hardwareCommand.getCode(),
                        hardwareCommand.getPrice(), hardwareCommand.getType().toString(),
                        hardwareCommand.getStock());
        try {
            saveHardwareDetails(hardware);
            return Optional.of(hardware);
        } catch (DuplicateKeyException e) {
            return Optional.empty();
        }
    }

    private long saveHardwareDetails(Hardware hardware) {
        Map<String, Object> values = new HashMap<>();

        values.put("code", hardware.getCode());
        values.put("name", hardware.getName());
        values.put("price", hardware.getPrice());
        values.put("type", hardware.getType());
        values.put("inStock", hardware.getStock());

        return insert.executeAndReturnKey(values).longValue();
    }


    @Override
    public void removeByCode(String code) {

        //jdbc.update("DELETE r FROM review INNER JOIN hardware ON r.hardware_id=hardware.id WHERE hardware.code = ?", code);
        jdbc.update("DELETE FROM review WHERE hardware_id IN (SELECT id FROM hardware WHERE code = ?)", code);
        jdbc.update("DELETE FROM hardware WHERE code = ?", code);

    }

    @Override
    public Optional<List<Hardware>> searchHardware(String searchTerm) {

        List<Hardware> hardwares = new ArrayList<>();
        searchTerm = searchTerm.toLowerCase();

        hardwares = jdbc.query(SELECT_ALL + " WHERE LOWER(name) LIKE '%" + searchTerm + "%';", this::mapRowToHardware);

        System.out.println("search: " + searchTerm);
        for (Hardware h : hardwares) {
            System.out.println(h.getName() + " " + h.getCode());
        }

        return Optional.of(hardwares);
    }

    @Override
    public Optional<Hardware> updateHardware(String code, HardwareCommand command) {
        jdbc.update("UPDATE hardware SET code = ?, name = ?, price = ?, type = ?, instock = ? WHERE code = ?",
                command.getCode(), command.getName(), command.getPrice(), command.getType(), command.getStock(), code);

        try {
            return Optional.ofNullable(
                    jdbc.queryForObject(SELECT_ALL + "WHERE code = ?", this::mapRowToHardware, code)
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
