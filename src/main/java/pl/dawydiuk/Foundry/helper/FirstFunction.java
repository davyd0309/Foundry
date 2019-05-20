package pl.dawydiuk.Foundry.helper;

import java.util.function.Function;

/**
 * Created by Konrad on 05.03.2019.
 */
public class FirstFunction implements Function<String,String> {

    @Override
    public String apply(String s) {
        String s1 = s + "I am FIRST";
        System.out.println(s1);
        return s1;
    }
}
