package com.example.kolin.tutu.presentation.common;

/**
 * Created by kolin on 11.11.2016.
 */

public interface Updatable<T> {
    void updateTo(T to);

    void updateFrom(T from);

    void updateDate(T date);
}
