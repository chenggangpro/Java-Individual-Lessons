package pro.chenggang.project.javaindividuallessons.exercise1.calculation;

import pro.chenggang.project.javaindividuallessons.exercise1.content.QueryInfo;
import pro.chenggang.project.javaindividuallessons.exercise1.content.QueryInfoMetaData;

import java.util.List;
import java.util.Map;

/**
 * @author: chenggang
 * @date 2020-09-21.
 */
public interface TransferCalculation {

    /**
     * List<QueryInfo> 是原始数据
     * 查询出来的结果结构
     * query_name  |  query_code  |  query_operator  |  query_limit_operator  |  function_type  |  display_sort  |  group_name
     *
     * 目标数据结构为
     *
     * {
     *     "functionType的值": {
     *         "groupName的值": [
     *              QueryInfoMetaData对象
     *         ]
     *     }
     * }
     *
     * 其中 QueryOperatorMetaData 对象中defaultFlag 字段，仅标记OperatorMetaDataList中的第一个
     *
     * 排序：
     * 1.有displaySort的按照displaySort排序
     * 2.operator的排序需要按照查询出来的顺序显示，即查询出来的逗号分割字符串的顺序
     *
     * GroupName不存在时，使用  "默认"  字符串
     *
     * functionType 为1 时，表示  查询功能
     * functionType 为2 时，表示  分组功能
     *
     *
     * @param queryInfoList
     * @return
     */
    Map<String,Map<String,List<QueryInfoMetaData>>> transfer(List<QueryInfo> queryInfoList);
}
