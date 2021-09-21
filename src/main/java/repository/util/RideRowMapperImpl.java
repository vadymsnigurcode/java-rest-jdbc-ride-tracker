package repository.util;

import model.Ride;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RideRowMapperImpl implements RowMapper<Ride> {
    @Override
    public Ride mapRow(ResultSet resultSet, int i) throws SQLException {
        Ride ride = new Ride() {{
            setId(resultSet.getInt("id"));
            setName(resultSet.getString("name"));
            setDuration(resultSet.getInt("duration"));
        }};
        return ride;
    }
}
