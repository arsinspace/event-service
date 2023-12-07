package scm.serenityproject.eventservice.utills.mappers;

import org.springframework.jdbc.core.RowMapper;
import scm.serenityproject.eventservice.model.Event;
import scm.serenityproject.eventservice.model.EventStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class EventMapper implements RowMapper<Event> {
    @Override
    public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Event.builder()
                .eventId(UUID.fromString(rs.getString("event_id")))
                .title(rs.getString("title"))
                .description(rs.getString("description"))
                .address(rs.getString("address"))
                .start(rs.getTimestamp("start"))
                .finish(rs.getTimestamp("finish"))
                .status(EventStatus.valueOf(rs.getString("status")))
                .build();
    }
}
