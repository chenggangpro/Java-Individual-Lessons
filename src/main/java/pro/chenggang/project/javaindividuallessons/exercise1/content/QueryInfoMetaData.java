package pro.chenggang.project.javaindividuallessons.exercise1.content;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class QueryInfoMetaData implements Cloneable {

    private String queryCode;
    private String queryName;
    private List<QueryOperatorMetaData> queryOperatorMetaDataList;

    @Override
    public Object clone() throws CloneNotSupportedException {
        QueryInfoMetaData cloneQueryInfoMetaData = (QueryInfoMetaData) super.clone();
        cloneQueryInfoMetaData.queryOperatorMetaDataList = Lists.newArrayListWithCapacity(8);
        return cloneQueryInfoMetaData;
    }
}