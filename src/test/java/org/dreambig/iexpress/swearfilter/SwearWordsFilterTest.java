package org.dreambig.iexpress.swearfilter;

import org.junit.Assert;
import org.junit.Test;

public class SwearWordsFilterTest {

    @Test
    public void getInstance() {
        SwearWordsFilter instance1 = SwearWordsFilter.getInstance();
        SwearWordsFilter instance2 = SwearWordsFilter.getInstance();
        Assert.assertEquals(instance1,instance2);

    }

    @Test
    public void filter() {
        SwearWordsFilter instance = SwearWordsFilter.getInstance();
        Assert.assertTrue(instance.haveSwearWords("This is fucking good"));
        Assert.assertFalse(instance.haveSwearWords("This is just good"));

    }
}