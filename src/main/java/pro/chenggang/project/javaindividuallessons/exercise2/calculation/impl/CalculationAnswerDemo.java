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

    @Override
    public void forEachWithStream(String[] dataList) {
        if(ArrayUtils.isEmpty(dataList)){
            return;
        }
        IntStream.range(0,dataList.length)
                .forEach(item-> System.out.println(dataList[item]));
    }

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

    @Override
    public Integer[] generateStepData(int start, int step, int totalCount) {
        return Stream.iterate(start, item->item+=step)
                .limit(totalCount)
                .collect(Collectors.toList())
                .toArray(new Integer[]{});
    }

    @Override
    public Set<QueryInfo> distinctFunction(List<QueryInfo> queryInfoList) {
        return null;
    }
}
