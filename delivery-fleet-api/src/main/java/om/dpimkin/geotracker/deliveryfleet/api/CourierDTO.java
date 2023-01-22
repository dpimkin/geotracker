package om.dpimkin.geotracker.deliveryfleet.api;

import lombok.Data;

@Data
public class CourierDTO {
    private String id;
    private String description;
    private Disposition disposition;
}
