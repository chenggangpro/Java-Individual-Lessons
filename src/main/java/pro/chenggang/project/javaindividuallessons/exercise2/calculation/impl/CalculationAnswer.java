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


    /**
     * 使用Stream 通过下标,进行循环输出，
     *
     * @param dataList 数据
     */
    @Override
    public void forEachWithStream(String[] dataList) {

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
        return null;
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
        return new Integer[0];
    }

    /**
     * 1. QueryInfo中的 queryOperator 字段，将逗号分割后的数据按照字典 去重 排序
     * 2. step1 排序后的字段重新用逗号拼接,
     * 3. 按照step2中重新拼接后的值，去重，得到Set<QueryInfo>集合
     *
     * @param queryInfoList
     * @return
     */
    @Override
    public Set<QueryInfo> distinctFunction(List<QueryInfo> queryInfoList) {
        return null;
    }
}
