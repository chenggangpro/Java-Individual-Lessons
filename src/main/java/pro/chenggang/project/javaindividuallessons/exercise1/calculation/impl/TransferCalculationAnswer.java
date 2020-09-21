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
        Map<Integer, List<QueryInfo>> functionTypeMapQueryInfos = queryInfoList.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(QueryInfo::getFunctionType));
        Map<String, Map<String, List<QueryOperatorMetaData>>> functionTypeKeyMap = Maps.newHashMapWithExpectedSize(2);
        if (!CollectionUtils.isEmpty(functionTypeMapQueryInfos)) {
            functionTypeMapQueryInfos.forEach((functionType, queryInfos) -> {
                Map<String, List<QueryInfo>> groupNameMapQueryInfos = queryInfos.stream()
                        // # 想用Optional没有用出来。。。
                        .collect(Collectors.groupingBy(queryInfo -> {
                                    // GroupName不存在时，使用  "默认"  字符串
                                    if (StringUtils.isEmpty(queryInfo.getGroupName())) {
                                        return Exercise1Constant.DEFAULT_GROUP_NAME;
                                    } else {
                                        return queryInfo.getGroupName();
                                    }
                                })
                        );
                Map<String, List<QueryOperatorMetaData>> groupNameKeyMap = Maps.newHashMapWithExpectedSize(8);
                groupNameMapQueryInfos.forEach((groupName, queryInfosAfterGroup) -> {
                    List<QueryOperatorMetaData> metaDataList = queryInfosAfterGroup.stream()
                            .filter(Objects::nonNull)
                            // 有displaySort的按照displaySort排序
                            .sorted(Comparator.nullsLast(Comparator.comparing(QueryInfo::getDisplaySort)))
                            .map(item -> {
                                QueryOperatorMetaData queryOperatorMetaData = new QueryOperatorMetaData();
                                queryOperatorMetaData.setQueryOperator(Optional.of(QueryOperator.valueOf(item.getQueryCode())).orElse(null));
                                return queryOperatorMetaData;
                            })
                            .collect(Collectors.toList());
                    // 其中 QueryOperatorMetaData 对象中defaultFlag 字段，仅标记OperatorMetaDataList中的第一个
                    metaDataList.stream().findFirst().map(item -> {
                        item.setDefaultFlag(Boolean.TRUE);
                        return item;
                    });
                    groupNameKeyMap.put(groupName, metaDataList);
                });
                functionTypeKeyMap.put(functionType == 1 ? "查询功能" : "分组功能", groupNameKeyMap);
            });

        }

        return Collections.emptyMap();
    }
}
