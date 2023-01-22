package com.dpimkin.geotracker.deliveryfleet.model;


import lombok.Data;
import lombok.experimental.Accessors;
import om.dpimkin.geotracker.deliveryfleet.api.Disposition;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Data
@Document(collection = "courier")
@Accessors(chain = true)
public class CourierDocument {

    @Id
    private String id;

    @Version
    private Long version;

    private Disposition disposition;
}
