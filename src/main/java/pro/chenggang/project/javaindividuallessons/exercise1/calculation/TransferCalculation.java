package pro.chenggang.project.javaindividuallessons.exercise1.calculation;

import pro.chenggang.project.javaindividuallessons.exercise1.content.QueryInfo;
import pro.chenggang.project.javaindividuallessons.exercise1.content.QueryOperatorMetaData;

import java.util.List;

/**
 * @author: chenggang
 * @date 2020-09-21.
 */
public interface TransferCalculation {

    /**
     * List<QueryInfo> 是原始数据
     * 目标数据结构为
     *
     * {
     *     "functionType的值": {
     *         "groupName的值": [
     *              QueryInfoMetaData对象
     *         ]
     *     }
     *
     * }
     *
     * 其中 QueryOperatorMetaData 对象中defaultFlag 字段，  仅标记OperatorMetaDataList中的第一个
     *
     * 排序：
     * 1.有displaySort的按照displaySort排序
     * 2.operator的排序需要按照查询出来的顺序显示，即查询出来的逗号分割字符串的顺序
     *
     * @param queryInfoList
     * @return
     */
    List<QueryOperatorMetaData> transfer(List<QueryInfo> queryInfoList);
}
