package com.android.demo;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.android.demo", appContext.getPackageName());
    }

    @Test
    public void testUri() {
        Uri uri = Uri.parse("content://com.baidu.car/status/com.global.speed");
        Uri uri2 = Uri.parse("content://com.baidu.car/status");
//        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
//        uriMatcher.addURI("uri", "content://com.baidu.car/status/", 1);
//        uriMatcher.addURI("uri", "content://com.baidu.car/status/", 2);
//        uriMatcher.addURI("com.baidu.car", "status", 3);
//        uriMatcher.addURI("com.baidu.car", "status/com.global.speed", 4);
//
//        System.out.println("PathSegments: " + uri.getPathSegments());
//        System.out.println("match: " + uriMatcher.match(uri));
//        System.out.println("match: " + uriMatcher.match(uri2));

        System.out.println(uri.getPath());
        System.out.println(uri.getPathSegments());
        Log.d("PATH", "path: " + uri.getPath());
        Log.d("PATH", "path: " + uri.getPathSegments());
    }
}
