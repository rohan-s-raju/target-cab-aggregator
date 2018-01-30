package com.target.impl.application.service.command;

import com.target.impl.domain.entity.DropPoint;
import com.target.impl.error.ErrorEnum;
import com.target.impl.exception.BusinessException;
import com.target.impl.port.DbRegistryPort;
import com.target.intf.ErrorDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rohan on 17/1/18.
 */
public class CreateDropPoint {


    public void create(LinkedHashMap<String, String> dropPointRequest) {
        validate(dropPointRequest);

        List<DropPoint> dropPointList = new ArrayList<>();
        boolean isTargetHeaderProccessed =  false;

        for (Map.Entry<String, String> stringEntry : dropPointRequest.entrySet()) {


            List<String> distance = Arrays.asList(stringEntry.getValue().split(","));
            int i=0;

            for (Map.Entry<String, String> innerEntry : dropPointRequest.entrySet()) {
                if(innerEntry.getKey().equals("target_headquarter") && isTargetHeaderProccessed){
                    continue;
                }

                if(Long.valueOf(distance.get(i)) == 0){
                    i++;
                }

                if(!stringEntry.getKey().equals(innerEntry.getKey()) && Long.valueOf(distance.get(i)) > 0)  {
                    dropPointList.add(new DropPoint.Builder().from(stringEntry.getKey()).to(innerEntry.getKey()).distance(Long.valueOf(distance.get(i)))
                            .createdBy("test").updatedBy("test").createdDate(new Date()).updatedDate(new Date()).build());
                    i++;
                }

            }
            if(!isTargetHeaderProccessed) {
                isTargetHeaderProccessed = stringEntry.getKey().equals("target_headquarter");
            }
        }

       DbRegistryPort.getDropPointRepository().save(dropPointList);
    }

    private void validate(LinkedHashMap<String, String> dropPointRequest) {
        if(dropPointRequest.isEmpty()){
            throw new BusinessException(new ErrorDTO(ErrorEnum.DROP_POINT_IS_NOT_NULL.name(), ErrorEnum.DROP_POINT_IS_NOT_NULL.getErrorMessage()));
        }

        if(dropPointRequest.get("target_headquarter") == null){
            throw new BusinessException(new ErrorDTO(ErrorEnum.DROP_POINT_HEAD_QUARTER_IS_NOT_PRESENT.name(), ErrorEnum.DROP_POINT_HEAD_QUARTER_IS_NOT_PRESENT.getErrorMessage()));
        }

        if(Arrays.asList(dropPointRequest.get("target_headquarter").split(",")).size() != dropPointRequest.keySet().size() - 1){
            throw new BusinessException(new ErrorDTO(ErrorEnum.DROP_POINT_INPUT_DATA_IS_NOT_VALID.name(), ErrorEnum.DROP_POINT_INPUT_DATA_IS_NOT_VALID.getErrorMessage()));
        }

        int i=-1;

        for (Map.Entry<String, String> stringEntry : dropPointRequest.entrySet()) {
            if(i>=0){
                List<String> values = Arrays.asList(stringEntry.getValue().split(","));
                if(Long.valueOf(values.get(i)) != 0){
                    throw new BusinessException(new ErrorDTO(ErrorEnum.DROP_POINT_INPUT_DATA_IS_NOT_VALID_AT.name(),
                            String.format(ErrorEnum.DROP_POINT_INPUT_DATA_IS_NOT_VALID_AT.getErrorMessage(),stringEntry.getKey())));
                }
            }
            i++;
        }

    }


}
