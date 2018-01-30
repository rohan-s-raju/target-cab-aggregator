package com.target.impl.application.service.command;

import com.target.impl.domain.entity.Cab;
import com.target.impl.error.ErrorEnum;
import com.target.impl.exception.BusinessException;
import com.target.impl.port.DbRegistryPort;
import com.target.intf.CabRequestDTO;
import com.target.intf.ErrorDTO;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by rohan on 18/1/18.
 */
public class CreateCabs {

    public void create(List<CabRequestDTO> cabRequestDTOS) {
        validate(cabRequestDTOS);
        List<Cab> cabs = cabRequestDTOS.stream().map(this::getCab).collect(Collectors.toList());
        DbRegistryPort.getCabRepository().save(cabs);
    }

    private Cab getCab(CabRequestDTO cabRequestDTO) {
        return new Cab.Builder().cabId(cabRequestDTO.getCabId()).capacity(cabRequestDTO.getCapacity()).cost(cabRequestDTO.getCost())
                .createdBy("test").updatedBy("test").createdDate(new Date()).updatedDate(new Date()).build();
    }

    private void validate(List<CabRequestDTO> cabRequestDTOS) {
        long totalCapacity = cabRequestDTOS.stream().mapToLong(CabRequestDTO::getCapacity).sum();

        long totalTeamMember = DbRegistryPort.getTeamMemberRepository().count();

        if (totalCapacity < totalTeamMember) {
            throw new BusinessException(new ErrorDTO(ErrorEnum.CAB_CAPACITY_DID_NOT_MATCH.name(),
                    String.format(ErrorEnum.CAB_CAPACITY_DID_NOT_MATCH.getErrorMessage())));
        }
    }
}
