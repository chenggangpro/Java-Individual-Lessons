package pro.chenggang.project.javaindividuallessons.exercise1.constants;

import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

/**
 * @Author: ZhangWufei
 * @Date: 2020/9/21 8:57 下午
 */
public class Exercise1Constant {

    public static final String DEFAULT_GROUP_NAME = "默认";

    @Getter
    @AllArgsConstructor
    public enum FunctionTypeEnum {
        /**
         * 查询功能
         */
        SELECT(1, "查询功能"),
        /**
         * 分组功能
         */
        GROUP(2, "分组功能"),
        ;
        public Integer functionTypeCode;
        public String functionTypeName;

        public static FunctionTypeEnum valueOfCode(Integer functionTypeCode){
            return EnumContainer.CONTAINER.get(functionTypeCode);
        }

        private static class EnumContainer{
            private static final Map<Integer,FunctionTypeEnum> CONTAINER;

            static {
                FunctionTypeEnum[] values = FunctionTypeEnum.values();
                CONTAINER = Maps.newHashMapWithExpectedSize(values.length);
                for (FunctionTypeEnum value : values) {
                    CONTAINER.put(value.getFunctionTypeCode(),value);
                }
            }
        }

    }
}
