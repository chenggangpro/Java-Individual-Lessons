package pro.chenggang.project.javaindividuallessons.exercise1.calculation.impl;

import lombok.extern.slf4j.Slf4j;
import pro.chenggang.project.javaindividuallessons.exercise1.calculation.TransferCalculation;
import pro.chenggang.project.javaindividuallessons.exercise1.content.QueryInfo;
import pro.chenggang.project.javaindividuallessons.exercise1.content.QueryOperatorMetaData;

import java.util.Collections;
import java.util.List;

/**
 * @author: chenggang
 * @date 2020-09-21.
 */
@Slf4j
public class TransferCalculationAnswer implements TransferCalculation {

    @Override
    public List<QueryOperatorMetaData> transfer(List<QueryInfo> queryInfoList) {
        return Collections.emptyList();
    }
}
