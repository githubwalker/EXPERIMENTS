package com.alprojects;
import java.util.Arrays;

/**
 * Created by andrew on 04.06.2017.
 */

public enum EventSeverity
{
    INFO(1), ERROR(2);

    private final int value;

    EventSeverity(int value)
    {
        this.value = value;
    }

    public static EventSeverity int2Severity(int val)
    {
        return Arrays.stream(EventSeverity.values())
                        .filter(v->v.value == val)
                        .findFirst()
                        .orElse(null);
    }
}
