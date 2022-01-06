package com.android.demo.testpack;

import org.junit.Test;

/**
 * <p>
 *
 * @author anlc
 * @date 2019-10-18
 */
public class PackageTest {

    @Test
    public void testPackage(){
        System.out.println(getClass().getPackage().getName());
    }
}
