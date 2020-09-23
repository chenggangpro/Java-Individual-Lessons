package pro.chenggang.project.javaindividuallessons.exercise1.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: ZhangWufei
 * @Date: 2020/9/21 8:57 下午
 */
public class Exercise1Constant {

    public static final String DEFAULT_GROUP_NAME = "默认";

    @Getter
    @AllArgsConstructor
    public enum FunctionTypeEnum {
        SELECT(1, "查询功能"),
        GROUP(2, "分组功能"),
        ;
        public Integer functionTypeCode;
        public String functionTypeName;

    }
}
