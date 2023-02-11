package pl.dolega.springcore.model.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Event {

    /**
     * Event id. UNIQUE.
     * @return Event Id
     */

    private long id;
    private String title;
    private Date date;

}
