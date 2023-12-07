package scm.serenityproject.eventservice.model;

import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Event {

    private UUID eventId;
    private Timestamp finish;
    private Timestamp start;
    private EventStatus status;
    private String title;
    private String description;
    private String address;
}
