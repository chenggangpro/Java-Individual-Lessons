package pro.chenggang.project.javaindividuallessons.exercise1.content;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class QueryInfo {

    private String groupName;
    private String queryName;
    private String queryCode;
    private String queryLimitOperator;
    private String queryOperator;
    private Integer functionType;
    private Double displaySort;

}