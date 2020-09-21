package pro.chenggang.project.javaindividuallessons.exercise1.content;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class QueryInfoMetaData {

    private String queryCode;
    private String queryName;
    private List<QueryOperatorMetaData> queryOperatorMetaDataList;
}