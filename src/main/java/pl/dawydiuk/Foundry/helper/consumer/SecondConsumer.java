package pl.dawydiuk.Foundry.helper.consumer;

import java.util.function.Consumer;

/**
 * Created by Konrad on 05.03.2019.
 */
public class SecondConsumer implements Consumer<Integer> {

    @Override
    public void accept(Integer integer) {
        System.out.println(integer+2);
    }
}
