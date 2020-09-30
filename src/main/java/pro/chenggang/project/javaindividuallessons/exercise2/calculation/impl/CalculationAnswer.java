package pro.chenggang.project.javaindividuallessons.exercise2.calculation.impl;

import pro.chenggang.project.javaindividuallessons.exercise1.content.QueryInfo;
import pro.chenggang.project.javaindividuallessons.exercise2.calculation.Calculation;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author: chenggang
 * @date 2020-09-24.
 * @author: cuitao
 * @date 2020-09-30.
 */
public class CalculationAnswer implements Calculation {


    /**
     * 使用Stream 通过下标,进行循环输出，
     *
     * @param dataList 数据
     */
    @Override
    public void forEachWithStream(String[] dataList) {
        // 先创建一个数组下标的int有限流，在通过无限流遍历
        IntStream.range(0, dataList.length)
                .forEach(item -> System.out.println(dataList[item]));
    }

    /**
     * 使用Stream获取指定序列的数据
     *
     * @param dataList 数据
     * @param index    指定的下标
     * @return 目标数据
     */
    @Override
    public String getTargetItem(String[] dataList, int index) {
        return IntStream.range(index, index + 1)
                .boxed()
                .findAny()
                .map(item -> dataList[item])
                .orElse("NULL");
    }

    /**
     * 使用Stream生成指定指定步长的数据
     *
     * @param start      开始值
     * @param step       步长
     * @param totalCount 总共生成的数量
     * @return 例如，start = 1 , step = 3 ,totalCount = 3
     * return  [1,4,7]
     */
    @Override
    public Integer[] generateStepData(int start, int step, int totalCount) {
        return IntStream.range(start, start + step * totalCount)
                .filter(item -> item % step == 0 || item == start)
                .limit(totalCount)
                .boxed()
                .collect(Collectors.toList())
                .toArray(new Integer[]{});
    }

    /**
     * 1. QueryInfo中的 queryOperator 字段，将逗号分割后的数据按照字典 去重 排序
     * 2. step1 排序后的字段重新用逗号拼接,
     * 3. 按照step2中重新拼接后的值，去重，得到Set<QueryInfo>集合
     *
     * @param queryInfoList 待处理的数据集合
     * @return Set<QueryInfo> 处理成功的数据集合
     */
    @Override
    public Set<QueryInfo> distinctFunction(List<QueryInfo> queryInfoList) {
        return queryInfoList.stream()
                // 1. QueryInfo中的 queryOperator 字段，将逗号分割后的数据按照字典 去重 排序
                // 2. step1 排序后的字段重新用逗号拼接,
                .map(item -> {
                    item.setQueryOperator(Arrays.stream(item.getQueryOperator().split(","))
                            .distinct()
                            .sorted()
                            .collect(Collectors.joining(",")));
                    return item;
                }).collect(Collectors.collectingAndThen(
                        // 3. 按照step2中重新拼接后的值，去重，得到Set<QueryInfo>集合
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(QueryInfo::getQueryOperator))), HashSet::new)
                );
    }

    /**
     * 1. QueryInfo中的 queryOperator 字段，将逗号分割后的数据按照字典 去重 排序
     * 2. step1 排序后的字段重新用逗号拼接,
     * 3. 生成 按照step2中重新拼接后的值，作为key，List<QueryInfo> 作为Value 的Map，
     * 4. Value 中需要按照displaySort排序，key按照首字母字典顺序的倒序排序
     *
     * @param queryInfoList
     * @return
     */
    @Override
    public Map<String, List<QueryInfo>> distinctFunction2(List<QueryInfo> queryInfoList) {
        return queryInfoList.stream()
                // 1. QueryInfo中的 queryOperator 字段，将逗号分割后的数据按照字典 去重 排序
                // 2. step1 排序后的字段重新用逗号拼接,
                .map(item -> {
                    item.setQueryOperator(Arrays.stream(item.getQueryOperator().split(","))
                            .distinct()
                            .sorted()
                            .collect(Collectors.joining(",")));
                    return item;
                }).collect(Collectors.groupingBy(QueryInfo::getQueryCode))
                .entrySet()
                .stream()
                // 4. key按照首字母字典顺序的倒序排序
                .sorted(Map.Entry.<String, List<QueryInfo>>comparingByKey().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().stream()
                                // Value 中需要按照displaySort排序
                                .sorted(Comparator.comparing(QueryInfo::getDisplaySort))
                                .collect(Collectors.toList()),
                        (first, last) -> first,
                        LinkedHashMap::new
                        )
                );
    }
}
