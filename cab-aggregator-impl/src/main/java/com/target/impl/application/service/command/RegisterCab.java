package com.target.impl.application.service.command;

import com.target.impl.domain.entity.Gender;
import com.target.impl.domain.entity.TeamMember;
import com.target.impl.error.ErrorEnum;
import com.target.impl.exception.BusinessException;
import com.target.impl.port.DbRegistryPort;
import com.target.intf.ErrorDTO;
import com.target.intf.RegisterCabRequestDTO;

import java.util.Date;

/**
 * Created by rohan on 18/1/18.
 */
public class RegisterCab {
    public void register(RegisterCabRequestDTO registerCabRequestDTO) {

        if (DbRegistryPort.getDropPointRepository().findByFrom(registerCabRequestDTO.getDropPoint()).isEmpty()) {
            throw new BusinessException(new ErrorDTO(ErrorEnum.DROP_POINT_NOT_FOUND.name(), ErrorEnum.DROP_POINT_NOT_FOUND.getErrorMessage()));
        }

        TeamMember teamMember = new TeamMember.Builder().teamMemberID(registerCabRequestDTO.getTeamMemberID()).gender(Gender.valueOf(registerCabRequestDTO.getGender()))
                .dropPoint(registerCabRequestDTO.getDropPoint()).createdBy("test").updatedBy("test").createdDate(new Date()).updatedDate(new Date()).build();

        DbRegistryPort.getTeamMemberRepository().save(teamMember);
    }
}
