package com.superbear.trino.plugin;

import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.superbear.trino.plugin.scalar.StringFunctions;

import io.trino.spi.Plugin;

public class ExampleFunctionsPlugin implements Plugin
{
    @Override
    public Set<Class<?>> getFunctions()
    {
        return ImmutableSet.<Class<?>>builder()
            .add(StringFunctions.class)
            .build();
    }
}
