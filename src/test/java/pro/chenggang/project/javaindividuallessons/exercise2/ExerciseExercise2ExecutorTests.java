package pro.chenggang.project.javaindividuallessons.exercise2;

import org.junit.Test;
import pro.chenggang.project.javaindividuallessons.ExerciseExecutor;
import pro.chenggang.project.javaindividuallessons.exercise2.calculation.impl.CalculationAnswer;
import pro.chenggang.project.javaindividuallessons.exercise2.calculation.impl.CalculationAnswerDemo;

/**
 * @author: chenggang
 * @date 2020-09-21.
 */
public class ExerciseExercise2ExecutorTests {

    @Test
    public void exercise2Answer() throws Exception {
        ExerciseExecutor exerciseExecutor = new Exercise2Executor(new CalculationAnswer());
        exerciseExecutor.execute();
    }

    @Test
    public void exercise2AnswerDemo() throws Exception {
        ExerciseExecutor exerciseExecutor = new Exercise2Executor(new CalculationAnswerDemo());
        exerciseExecutor.execute();
    }

}
