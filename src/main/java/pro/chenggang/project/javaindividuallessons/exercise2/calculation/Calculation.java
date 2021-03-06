package pro.chenggang.project.javaindividuallessons.exercise2.calculation;

import pro.chenggang.project.javaindividuallessons.exercise1.content.QueryInfo;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author: chenggang
 * @date 2020-09-24.
 */
public interface Calculation {

    /**
     * 使用Stream 通过下标,进行循环输出，
     * @param dataList 数据
     */
    void forEachWithStream(String[] dataList);

    /**
     * 使用Stream获取指定序列的数据
     * @param dataList 数据
     * @param index
     * @return
     */
    String getTargetItem(String[] dataList,int index);

    /**
     * 使用Stream生成指定指定步长的数据
     * @param start 开始值
     * @param step 步长
     * @param totalCount 总共生成的数量
     * @return
     * 例如，start = 1 , step = 3 ,totalCount = 3
     * return  [1,4,7]
     */
    Integer[] generateStepData(int start,int step,int totalCount);

    /**
     * 1. QueryInfo中的 queryOperator 字段，将逗号分割后的数据按照字典 去重 排序
     * 2. step1 排序后的字段重新用逗号拼接,
     * 3. 按照step2中重新拼接后的值，去重，得到Set<QueryInfo>集合
     * @param queryInfoList
     * @return
     */
    Set<QueryInfo> distinctFunction(List<QueryInfo> queryInfoList);

    /**
     * 1. QueryInfo中的 queryOperator 字段，将逗号分割后的数据按照字典 去重 排序
     * 2. step1 排序后的字段重新用逗号拼接,
     * 3. 生成 按照step2中重新拼接后的值，作为key，List<QueryInfo> 作为Value 的Map，
     * 4. Value 中需要按照displaySort排序，key按照首字母字典顺序的倒序排序
     *
     * @param queryInfoList
     * @return
     */
    Map<String,List<QueryInfo>> distinctFunction2(List<QueryInfo> queryInfoList);
}
