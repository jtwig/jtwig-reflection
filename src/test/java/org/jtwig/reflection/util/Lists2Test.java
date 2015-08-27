package org.jtwig.reflection.util;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class Lists2Test {
    @Test
    public void transform() throws Exception {
        List<Integer> inputList = asList(1);

        List<String> result = Lists2.transform(inputList, new Function<Integer, String>() {
            @Override
            public String apply(Integer input) {
                return input.toString();
            }
        });

        assertThat(result.get(0), is("1"));
    }
    @Test
    public void filter() throws Exception {
        List<Integer> inputList = asList(1);

        List<Integer> result = Lists2.filter(inputList, new Predicate<Integer>() {
            @Override
            public boolean apply(Integer input) {
                return input != 1;
            }
        });

        assertThat(result.size(), is(0));
    }

    @Test
    public void findWhenExists() throws Exception {
        List<Integer> inputList = asList(1);

        Optional<Integer> result = Lists2.find(inputList, new Predicate<Integer>() {
            @Override
            public boolean apply(Integer input) {
                return input == 1;
            }
        });

        assertThat(result.isPresent(), is(true));
        assertThat(result.get(), is(1));
    }


    @Test
    public void findWhenNotExists() throws Exception {
        List<Integer> inputList = asList(2);

        Optional<Integer> result = Lists2.find(inputList, new Predicate<Integer>() {
            @Override
            public boolean apply(Integer input) {
                return input == 1;
            }
        });

        assertThat(result.isPresent(), is(false));
    }

    @Test
    public void positionOfWhenExists() throws Exception {
        List<Integer> inputList = asList(1);

        int result = Lists2.positionOf(inputList, new Predicate<Integer>() {
            @Override
            public boolean apply(Integer input) {
                return input == 1;
            }
        });

        assertThat(result, is(0));
    }

    @Test
    public void positionOfWhenNotExists() throws Exception {
        List<Integer> inputList = asList(2);

        int result = Lists2.positionOf(inputList, new Predicate<Integer>() {
            @Override
            public boolean apply(Integer input) {
                return input == 1;
            }
        });

        assertThat(result, is(-1));
    }
}