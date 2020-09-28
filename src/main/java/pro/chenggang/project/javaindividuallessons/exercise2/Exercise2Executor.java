package pro.chenggang.project.javaindividuallessons.exercise2;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import pro.chenggang.project.javaindividuallessons.ExerciseExecutor;
import pro.chenggang.project.javaindividuallessons.exercise1.content.QueryInfo;
import pro.chenggang.project.javaindividuallessons.exercise2.calculation.Calculation;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * @author: chenggang
 * @date 2020-09-21.
 */
@Slf4j
@RequiredArgsConstructor
public class Exercise2Executor implements ExerciseExecutor {

    private final Calculation calculation;

    @Override
    public void execute() throws Exception {
        String[] data = IntStream.range(0, RandomUtils.nextInt(10, 20)).mapToObj(item -> RandomStringUtils.randomAlphanumeric(4)).toArray(String[]::new);
        System.out.println("-----For Each With Stream :");
        calculation.forEachWithStream(data);
        int index = RandomUtils.nextInt(0, data.length);
        String targetItem = calculation.getTargetItem(data, index);
        System.out.println("-----Get Target Item Data ,Index : "+index+" Data :");
        System.out.println(targetItem);
        Integer[] generateStepData = calculation.generateStepData(RandomUtils.nextInt(0, 10), RandomUtils.nextInt(1, 10), RandomUtils.nextInt(5, 20));
        System.out.println("-----Generate Step Data :");
        System.out.println(JSON.toJSONString(generateStepData, SerializerFeature.PrettyFormat));
        Resource resource = new ClassPathResource("exercise1/data.json");
        String dataJson = String.join("", Files.readAllLines(Paths.get(resource.getURI())));
        List<QueryInfo> queryInfoList = JSON.parseArray(dataJson, QueryInfo.class);
        Set<QueryInfo> distinctFunctionResult = calculation.distinctFunction(queryInfoList);
        System.out.println("-----Distinct Data :");
        System.out.println(JSON.toJSONString(distinctFunctionResult, SerializerFeature.PrettyFormat));
    }
}
