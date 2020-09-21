package pro.chenggang.project.javaindividuallessons.exercise1.content;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QueryOperatorMetaData {

    private QueryOperator queryOperator;
    private Boolean defaultFlag = Boolean.FALSE;
}