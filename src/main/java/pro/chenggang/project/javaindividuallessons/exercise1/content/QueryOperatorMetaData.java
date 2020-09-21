package pro.chenggang.project.javaindividuallessons.exercise1.content;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QueryOperatorMetaData implements Cloneable {

    private QueryOperator queryOperator;
    private Boolean defaultFlag = Boolean.FALSE;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}