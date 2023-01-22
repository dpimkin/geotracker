package com.dpimkin.geotracker.deliveryfleet.mapper;

import com.dpimkin.geotracker.deliveryfleet.model.CourierDocument;
import om.dpimkin.geotracker.deliveryfleet.api.CourierDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CourierMapper {

    CourierDTO mapCourierDocument(CourierDocument document);

}
