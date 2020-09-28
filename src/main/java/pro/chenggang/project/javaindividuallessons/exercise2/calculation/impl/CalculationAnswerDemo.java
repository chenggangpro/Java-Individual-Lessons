package pro.chenggang.project.javaindividuallessons.exercise2.calculation.impl;

import org.apache.commons.lang3.ArrayUtils;
import pro.chenggang.project.javaindividuallessons.exercise1.content.QueryInfo;
import pro.chenggang.project.javaindividuallessons.exercise2.calculation.Calculation;

import java.util.List;
import java.util.Set;
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
     * 1. QueryInfo中的 queryOperator 字段，将逗号分割后的数据按照字典 排序 去重
     * 2. step1 排序后的字段重新用逗号拼接,
     * 3. 按照step2中重新拼接后的值，去重，得到Set<QueryInfo>集合
     * @param queryInfoList
     * @return
     */
    @Override
    public Set<QueryInfo> distinctFunction(List<QueryInfo> queryInfoList) {
        return null;
    }
}
