package org.jtwig.reflection.extractor;

import com.google.common.base.Predicate;
import org.jtwig.reflection.model.bean.BeanMethod;
import org.jtwig.reflection.model.java.JavaMethod;
import org.junit.Test;

import java.util.Collection;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BeanMethodExtractorTest {
    BeanMethodExtractor underTest = new BeanMethodExtractor();

    @Test
    public void name() throws Exception {
        Collection<BeanMethod> result = underTest.extract(new Object(), new Predicate<JavaMethod>() {
            @Override
            public boolean apply(JavaMethod input) {
                return false;
            }
        });

        assertThat(result.size(), is(0));
    }
}