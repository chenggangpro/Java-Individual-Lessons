package pro.chenggang.project.javaindividuallessons.exercise1.calculation.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import pro.chenggang.project.javaindividuallessons.exercise1.calculation.TransferCalculation;
import pro.chenggang.project.javaindividuallessons.exercise1.constants.Exercise1Constant;
import pro.chenggang.project.javaindividuallessons.exercise1.content.QueryInfo;
import pro.chenggang.project.javaindividuallessons.exercise1.content.QueryInfoMetaData;
import pro.chenggang.project.javaindividuallessons.exercise1.content.QueryOperator;
import pro.chenggang.project.javaindividuallessons.exercise1.content.QueryOperatorMetaData;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: chenggang
 * @date 2020-09-21.
 * @author: zhangwufei
 * @data 2020-09-23.
 */
@Slf4j
public class TransferCalculationAnswer implements TransferCalculation {
    /*
     * 查询出来的结果结构
     * query_name  |  query_code  |  query_operator  |  query_limit_operator  |  function_type  |  display_sort  |  group_name
     * 目标数据结构为
     * {
     *     "functionType的值": {
     *         "groupName的值": [
     *              QueryInfoMetaData对象
     *         ]
     *     }
     * }
     */
    @Override
    public Map<String, Map<String, List<QueryInfoMetaData>>> transfer(List<QueryInfo> queryInfoList) {
        return queryInfoList.stream()
                .collect(Collectors.groupingBy(queryInfo -> {
                    // GroupName不存在时，使用  "默认"  字符串
                    if (Exercise1Constant.FunctionTypeEnum.SELECT.functionTypeCode.equals(queryInfo.getFunctionType())) {
                        return Exercise1Constant.FunctionTypeEnum.SELECT.functionTypeName;
                    } else {
                        return Exercise1Constant.FunctionTypeEnum.GROUP.functionTypeName;
                    }
                }))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().stream()
                                .collect(Collectors.groupingBy(queryInfo -> {
                                    // GroupName不存在时，使用  "默认"  字符串
                                    if (StringUtils.isEmpty(queryInfo.getGroupName())) {
                                        return Exercise1Constant.DEFAULT_GROUP_NAME;
                                    } else {
                                        return queryInfo.getGroupName();
                                    }
                                }))
                ))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().entrySet()
                                .stream()
                                .collect(Collectors.toMap(
                                        Map.Entry::getKey,
                                        groupNameMapQueryInfoEntry -> groupNameMapQueryInfoEntry.getValue()
                                                .stream()
                                                .map(queryInfo -> {
                                                    QueryInfoMetaData queryInfoMetaData = new QueryInfoMetaData();
                                                    queryInfoMetaData.setQueryName(queryInfo.getQueryName());
                                                    queryInfoMetaData.setQueryCode(queryInfo.getQueryCode());
                                                    List<String> limitOperator = Arrays.asList(queryInfo.getQueryLimitOperator().split(","));
                                                    List<String> operators = Arrays.asList(queryInfo.getQueryOperator().split(","));
                                                    List<String> intersectionOperators = limitOperator.stream()
                                                            .filter(operators::contains)
                                                            .collect(Collectors.toList());
                                                    List<QueryOperatorMetaData> queryOperatorMetaDataList = intersectionOperators.stream()
                                                            .map(item -> {
                                                                QueryOperatorMetaData queryOperatorMetaData = new QueryOperatorMetaData();
                                                                queryOperatorMetaData.setQueryOperator(QueryOperator.valueOf(item));
                                                                return queryOperatorMetaData;
                                                            })
                                                            .collect(Collectors.toList());
                                                    // 其中 QueryOperatorMetaData 对象中defaultFlag 字段，仅标记OperatorMetaDataList中的第一个
                                                    queryOperatorMetaDataList.stream().findFirst().map(queryOperatorMetaData -> {
                                                        queryOperatorMetaData.setDefaultFlag(Boolean.TRUE);
                                                        return queryOperatorMetaData;
                                                    });
                                                    queryInfoMetaData.setQueryOperatorMetaDataList(queryOperatorMetaDataList);
                                                    return queryInfoMetaData;
                                                }).collect(Collectors.toList())
                                )))
                );
    }
}
