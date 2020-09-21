package pro.chenggang.project.javaindividuallessons.exercise1.calculation.impl;

import lombok.extern.slf4j.Slf4j;
import pro.chenggang.project.javaindividuallessons.exercise1.calculation.TransferCalculation;
import pro.chenggang.project.javaindividuallessons.exercise1.content.QueryInfo;
import pro.chenggang.project.javaindividuallessons.exercise1.content.QueryInfoMetaData;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author: chenggang
 * @date 2020-09-21.
 */
@Slf4j
public class TransferCalculationAnswer implements TransferCalculation {

    @Override
    public Map<String,Map<String,List<QueryInfoMetaData>>> transfer(List<QueryInfo> queryInfoList) {
        return Collections.emptyMap();
    }
}
