package pl.dawydiuk.Foundry.helper;

import pl.dawydiuk.Foundry.helper.consumer.FirstConsumer;
import pl.dawydiuk.Foundry.helper.consumer.SecondConsumer;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by Judith on 05.03.2019.
 */
public class Go {

    public static void main(String[] args) {
        Consumer<Integer> firstConsumer = new FirstConsumer();
        Consumer<Integer> secondConsumer = new SecondConsumer();
        firstConsumer.andThen(secondConsumer).accept(0);

        Function<String,String> firstFunction = new FirstFunction();
        Function<String,String> secondFunction = new SecondFunction();
        firstFunction.andThen(secondFunction).apply("YYY");
        firstFunction.compose(secondFunction).apply("YYY");
    }
}