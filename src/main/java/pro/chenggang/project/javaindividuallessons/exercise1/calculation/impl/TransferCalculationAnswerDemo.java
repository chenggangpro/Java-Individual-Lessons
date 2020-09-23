package pro.chenggang.project.javaindividuallessons.exercise1.calculation.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import pro.chenggang.project.javaindividuallessons.exercise1.calculation.TransferCalculation;
import pro.chenggang.project.javaindividuallessons.exercise1.constants.Exercise1Constant;
import pro.chenggang.project.javaindividuallessons.exercise1.content.QueryInfo;
import pro.chenggang.project.javaindividuallessons.exercise1.content.QueryInfoMetaData;
import pro.chenggang.project.javaindividuallessons.exercise1.content.QueryOperator;
import pro.chenggang.project.javaindividuallessons.exercise1.content.QueryOperatorMetaData;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: chenggang
 * @date 2020-09-23.
 */
@Slf4j
public class TransferCalculationAnswerDemo implements TransferCalculation {

    @Override
    public Map<String, Map<String, List<QueryInfoMetaData>>> transfer(List<QueryInfo> queryInfoList) {
        return queryInfoList.stream()
                .collect(Collectors.groupingBy(item-> Exercise1Constant.FunctionTypeEnum.valueOfCode(item.getFunctionType()).getFunctionTypeName()))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry->entry.getValue()
                                .stream()
                                .collect(Collectors.groupingBy(item-> StringUtils.isBlank(item.getGroupName()) ? Exercise1Constant.DEFAULT_GROUP_NAME : item.getGroupName()))
                                .entrySet()
                                .stream()
                                .collect(Collectors.toMap(
                                        Map.Entry::getKey,
                                        subEntry->subEntry.getValue().stream()
                                                .sorted(Comparator.comparing(QueryInfo::getDisplaySort))
                                                .map(item->{
                                                    String queryOperator = item.getQueryOperator();
                                                    String limitOperator = item.getQueryLimitOperator();
                                                    String[] queryOperators = StringUtils.split(queryOperator, ",");
                                                    String[] limitOperators = StringUtils.split(limitOperator, ",");
                                                    AtomicBoolean defaultFlag = new AtomicBoolean(false);
                                                    List<QueryOperatorMetaData> queryOperatorMetaDataList = Stream.of(queryOperators)
                                                            .filter(value -> ArrayUtils.contains(limitOperators, value))
                                                            .map(value -> {
                                                                QueryOperatorMetaData queryOperatorMetaData = new QueryOperatorMetaData();
                                                                queryOperatorMetaData.setQueryOperator(QueryOperator.valueOf(value));
                                                                queryOperatorMetaData.setDefaultFlag(defaultFlag.compareAndSet(false, true));
                                                                return queryOperatorMetaData;
                                                            })
                                                            .collect(Collectors.toList());
                                                    QueryInfoMetaData queryInfoMetaData = new QueryInfoMetaData();
                                                    queryInfoMetaData.setQueryCode(item.getQueryCode());
                                                    queryInfoMetaData.setQueryName(item.getQueryName());
                                                    queryInfoMetaData.setQueryOperatorMetaDataList(queryOperatorMetaDataList);
                                                    return queryInfoMetaData;
                                                })
                                                .collect(Collectors.toList())
                                ))
                ));
    }
}
