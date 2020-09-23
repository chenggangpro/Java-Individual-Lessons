package pro.chenggang.project.javaindividuallessons.exercise1;

import org.junit.Test;
import pro.chenggang.project.javaindividuallessons.ExerciseExecutor;
import pro.chenggang.project.javaindividuallessons.exercise1.calculation.impl.TransferCalculationAnswer;
import pro.chenggang.project.javaindividuallessons.exercise1.calculation.impl.TransferCalculationAnswerDemo;

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

    @Test
    public void exercise1AnswerDemo() throws Exception{
        ExerciseExecutor exerciseExecutor = new Exercise1Executor(new TransferCalculationAnswerDemo());
        exerciseExecutor.execute();
    }

}
