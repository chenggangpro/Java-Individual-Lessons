package pro.chenggang.project.javaindividuallessons.exercise1.calculation.impl;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
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
        Map<String, Map<String, List<QueryInfoMetaData>>> functionTypeKeyMap = Maps.newHashMapWithExpectedSize(2);
        Map<Integer, Map<String, List<QueryInfo>>> functionTypeMapGroupNameMapToQueryInfos = queryInfoList.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(QueryInfo::getFunctionType, Collectors.groupingBy(
                        queryInfo -> {
                            // GroupName不存在时，使用  "默认"  字符串
                            if (StringUtils.isEmpty(queryInfo.getGroupName())) {
                                return Exercise1Constant.DEFAULT_GROUP_NAME;
                            } else {
                                return queryInfo.getGroupName();
                            }
                        })
                        )
                );
        functionTypeMapGroupNameMapToQueryInfos.entrySet().stream().forEach(mapEntry -> {
            Map<String, List<QueryInfo>> groupNameMapQueryInfos = mapEntry.getValue();
            Map<String, List<QueryInfoMetaData>> groupNameMapQueryInfoMetaData = Maps.newHashMapWithExpectedSize(8);
            groupNameMapQueryInfos.entrySet().forEach(entry -> {
                List<QueryInfo> queryInfos = entry.getValue();
                Map<String, List<QueryInfo>> queryCodeMapQueryInfos = queryInfos.stream().collect(Collectors.groupingBy(QueryInfo::getQueryCode));
                queryCodeMapQueryInfos.entrySet().forEach(queryInfoEntrySet -> {
                            List<QueryInfo> queryInfos1 = queryInfoEntrySet.getValue();
                            List<QueryInfoMetaData> queryInfoMetaDataList = queryInfos1.stream()
                                    .filter(Objects::nonNull)
                                    // 有displaySort的按照displaySort排序
                                    .sorted(Comparator.nullsLast(Comparator.comparing(QueryInfo::getDisplaySort)))
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
                                    }).collect(Collectors.toList());
                            groupNameMapQueryInfoMetaData.put(queryInfoEntrySet.getKey(), queryInfoMetaDataList);
                        }
                );
            });
            functionTypeKeyMap.put(mapEntry.getKey() == 1 ? "查询功能" : "分组功能", groupNameMapQueryInfoMetaData);
        });
        return functionTypeKeyMap;
    }
}
