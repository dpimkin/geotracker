package om.dpimkin.geotracker.deliveryfleet.api;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Disposition {
    @NotNull Double latitude;
    @NotNull Double longitude;
}
