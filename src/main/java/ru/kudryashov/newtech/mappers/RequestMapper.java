package ru.kudryashov.newtech.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import ru.kudryashov.newtech.dto.RequestDTO;
import ru.kudryashov.newtech.entities.Request;

@Mapper(componentModel = "spring")
public interface RequestMapper {
    RequestDTO map(Request user);

    @InheritInverseConfiguration
    Request map(RequestDTO userDTO);
}
