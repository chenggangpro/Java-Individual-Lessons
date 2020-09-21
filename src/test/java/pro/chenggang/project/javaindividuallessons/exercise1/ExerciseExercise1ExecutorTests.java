package pro.chenggang.project.javaindividuallessons.exercise1;

import org.junit.Test;
import pro.chenggang.project.javaindividuallessons.ExerciseExecutor;
import pro.chenggang.project.javaindividuallessons.exercise1.calculation.impl.TransferCalculationAnswer;

/**
 * @author: chenggang
 * @date 2020-09-21.
 */
public class ExerciseExercise1ExecutorTests{

    @Test
    public void exercise1Answer() throws Exception{
        ExerciseExecutor exerciseExecutor = new Exercise1Executor(new TransferCalculationAnswer());
        exerciseExecutor.execute();
    }
    /*
     * 在 calculation 包下实现 TransferCalculation 接口
     * 完成接口功能，
     * 并提供测试类执行，如上形式即可
     */

}
