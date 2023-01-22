package com.dpimkin.geotracker.deliveryfleet.mapper;

import com.dpimkin.geotracker.deliveryfleet.model.CourierDocument;
import om.dpimkin.geotracker.deliveryfleet.api.CourierDTO;
import org.springframework.stereotype.Component;

@Component
public class CourierMapper {

    public CourierDTO mapCourierDocument(CourierDocument document) {
        var result = new CourierDTO();
        result.setId(document.getId());
        result.setDescription(document.getDescription());
        return result;
    }

}
