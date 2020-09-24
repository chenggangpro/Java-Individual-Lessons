package pro.chenggang.project.javaindividuallessons.exercise2.calculation.impl;

import pro.chenggang.project.javaindividuallessons.exercise1.content.QueryInfo;
import pro.chenggang.project.javaindividuallessons.exercise2.calculation.Calculation;

import java.util.List;
import java.util.Set;

/**
 * @author: chenggang
 * @date 2020-09-24.
 */
public class CalculationAnswer implements Calculation {

    @Override
    public void forEachWithStream(String[] dataList) {

    }

    @Override
    public String getTargetItem(String[] dataList, int index) {
        return null;
    }

    @Override
    public int[] generateStepData(int start, int step, int totalCount) {
        return new int[0];
    }

    @Override
    public Set<QueryInfo> distinctFunction(List<QueryInfo> queryInfoList) {
        return null;
    }
}
