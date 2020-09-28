package pro.chenggang.project.javaindividuallessons.exercise2.calculation.impl;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import pro.chenggang.project.javaindividuallessons.exercise1.content.QueryInfo;
import pro.chenggang.project.javaindividuallessons.exercise2.calculation.Calculation;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author: chenggang
 * @date 2020-09-24.
 */
public class CalculationAnswerDemo implements Calculation {

    /**
     * 使用Stream 通过下标,进行循环输出，
     *
     * @param dataList 数据
     */
    @Override
    public void forEachWithStream(String[] dataList) {
        if(ArrayUtils.isEmpty(dataList)){
            return;
        }
        IntStream.range(0,dataList.length)
                .forEach(item-> System.out.println(dataList[item]));
    }

    /**
     * 使用Stream获取指定序列的数据
     *
     * @param dataList 数据
     * @param index
     * @return
     */
    @Override
    public String getTargetItem(String[] dataList, int index) {
        if(ArrayUtils.isEmpty(dataList)){
            throw new IllegalArgumentException("dataList");
        }
        if(index >= dataList.length){
            throw new IllegalArgumentException("index");
        }
        return IntStream.range(0,dataList.length)
                .skip(index)
                .boxed()
                .findFirst()
                .map(item->dataList[item])
                .orElseThrow(IllegalStateException::new);
    }

    /**
     * 使用Stream生成指定指定步长的数据
     * @param start 开始值
     * @param step 步长
     * @param totalCount 总共生成的数量
     * @return
     * 例如，start = 1 , step = 3 ,totalCount = 3
     * return  [1,4,7]
     */
    @Override
    public Integer[] generateStepData(int start, int step, int totalCount) {
        return Stream.iterate(start, item->item+=step)
                .limit(totalCount)
                .collect(Collectors.toList())
                .toArray(new Integer[]{});
    }

    /**
     * 1. QueryInfo中的 queryOperator 字段，将逗号分割后的数据按照字典 去重 排序
     * 2. step1 排序后的字段重新用逗号拼接,
     * 3. 按照step2中重新拼接后的值，去重，得到Set<QueryInfo>集合
     * @param queryInfoList
     * @return
     */
    @Override
    public Set<QueryInfo> distinctFunction(List<QueryInfo> queryInfoList) {
        return queryInfoList.stream()
                .filter(distinctByKey(item-> Stream.of(StringUtils.split(item.getQueryOperator(),","))
                        .distinct()
                        .sorted(Comparator.naturalOrder())
                        .collect(Collectors.joining(","))))
                .collect(Collectors.toSet());
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
                .collect(Collectors.groupingBy(item-> Stream.of(StringUtils.split(item.getQueryOperator(),","))
                        .distinct()
                        .sorted(Comparator.naturalOrder())
                        .collect(Collectors.joining(",")))
                )
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(o -> StringUtils.substring(((Map.Entry<String,List<QueryInfo>>)o).getKey(), 0, 1)).reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry->entry.getValue().stream().sorted(Comparator.comparing(QueryInfo::getDisplaySort)).collect(Collectors.toList()),
                        (o1,o2)->o1,
                        LinkedHashMap::new
                ));
    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }
}
