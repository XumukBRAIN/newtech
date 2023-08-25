package ru.kudryashov.newtech.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import ru.kudryashov.newtech.dto.ManagerDTO;
import ru.kudryashov.newtech.entities.Manager;

@Mapper(componentModel = "spring")
public interface ManagerMapper {
    ManagerDTO map(Manager manager);

    @InheritInverseConfiguration
    Manager map(ManagerDTO managerDTO);
}
