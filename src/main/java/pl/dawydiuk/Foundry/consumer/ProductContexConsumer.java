package pl.dawydiuk.Foundry.consumer;

import java.util.function.Consumer;

/**
 * Created by Judith on 17.03.2019.
 */
public interface ProductContexConsumer<T> extends Consumer<T> {

    default void accept(T newProduct) {
        execute(newProduct);
    }

    void execute(final T newProduct);
}
