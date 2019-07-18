// IAidlInterface.aidl
package com.android.demo;

// Declare any non-default types here with import statements

interface IAidlInterface {

    String aidlGetString();

    void setAidlString(String str);

    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
}
