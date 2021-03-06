package pro.chenggang.project.javaindividuallessons.exercise1;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import pro.chenggang.project.javaindividuallessons.ExerciseExecutor;
import pro.chenggang.project.javaindividuallessons.exercise1.calculation.TransferCalculation;
import pro.chenggang.project.javaindividuallessons.exercise1.content.QueryInfo;
import pro.chenggang.project.javaindividuallessons.exercise1.content.QueryInfoMetaData;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

/**
 * @author: chenggang
 * @date 2020-09-21.
 */
@Slf4j
@RequiredArgsConstructor
public class Exercise1Executor implements ExerciseExecutor {

    private final TransferCalculation transferCalculation;

    @Override
    public void execute() throws Exception {
        /*
         * 查询出来的结果结构
         * query_name  |  query_code  |  query_operator  |  query_limit_operator  |  function_type  |  display_sort  |  group_name
         */
        Resource resource = new ClassPathResource("exercise1/data.json");
        String dataJson = String.join("", Files.readAllLines(Paths.get(resource.getURI())));
        List<QueryInfo> queryInfoList = JSON.parseArray(dataJson, QueryInfo.class);
        Map<String, Map<String, List<QueryInfoMetaData>>> transferResult = transferCalculation.transfer(queryInfoList);
        System.out.println(JSON.toJSONString(transferResult, SerializerFeature.PrettyFormat));
    }
}
