package com.example.kolin.tutu.data;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

public class Direction {
    /**
     * Created by kolin on 11.11.2016.
     */

    @Retention(RUNTIME)
    @IntDef({FROM, TO})
    public @interface TypeDirection {}

    public static final int FROM = 0;
    public static final int TO = 1;

}
