package pro.chenggang.project.javaindividuallessons.exercise1.content;

import lombok.Getter;

@Getter
public enum QueryOperator {

    /**
     * 等于
     */
    EQUAL,
    /**
     * 不等于
     */
    NOT_EQUAL,
    /**
     * 小于
     */
    LESS,
    /**
     * 大于
     */
    GREATER,
    /**
     * 小于等于
     */
    LESS_OR_EQUAL,
    /**
     * 大于等于
     */
    GREATER_OR_EQUAL,
    /**
     * 区间
     */
    BETWEEN,
    /**
     * 包含任意一个
     */
    IN,
    /**
     * 单值包含
     */
    SINGLE_IN,
    /**
     * 单值不包含
     */
    SINGLE_NOT_IN,
    /**
     * 不包含任意一个(暂时不配置使用)
     */
    NOT_IN,
    /**
     * find in set
     */
    FIND_IN,
    /**
     * find in set
     */
    FIND_NOT_IN,
    /**
     * find in set
     */
    FIND_SELECT,
    /**
     * 匹配
     */
    LIKE,
    /**
     * 未匹配
     */
    NOT_LIKE,

    /**
     * 绝对时间
     */
    ABSOLUTE_TIME,

    /**
     * 相对于当前时间
     */
    RELATIVE_CURRENT_TIME,
    /**
     * 相对于固定时间
     */
    RELATIVE_FIX_TIME,
    /**
     * 是null
     */
    IS_NULL,
    /**
     * 不是null
     */
    IS_NOT_NULL,
    /**
     * 选择
     */
    SELECT,
    /**
     * 地理位置
     */
    GEOGRAPHY,
    /**
     * 没有任何
     */
    NONE_HAVE,
    /**
     * 仅有
     */
    ONLY_HAVE,
    /**
     * 包含所有
     */
    ALL_HAVE,
    /**
     * 仅有
     */
    FIND_ONLY_HAVE,
    /**
     * 包含所有
     */
    FIND_ALL_HAVE,

    ;

}