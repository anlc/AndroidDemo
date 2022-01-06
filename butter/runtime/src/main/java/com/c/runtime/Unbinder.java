package com.c.runtime;

import androidx.annotation.UiThread;

/**
 * <p>
 *
 * @author anlc
 * @date 2019-09-08
 */
public interface Unbinder {

    @UiThread
    void unbind();

    Unbinder EMPTY = new Unbinder() {
        @Override
        public void unbind() {

        }
    };
}
