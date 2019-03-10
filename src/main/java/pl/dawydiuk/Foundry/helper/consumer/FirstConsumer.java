package pl.dawydiuk.Foundry.helper.consumer;

import java.util.function.Consumer;

/**
 * Created by Judith on 05.03.2019.
 */
public class FirstConsumer implements Consumer<Integer>{


    @Override
    public void accept(Integer integer) {
        System.out.println(integer+1);
    }

}
