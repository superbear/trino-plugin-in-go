package com.superbear.trino.plugin.scalar;

import static io.airlift.slice.Slices.utf8Slice;

import go.Client;
import io.airlift.slice.Slice;
import io.trino.spi.function.Description;
import io.trino.spi.function.LiteralParameters;
import io.trino.spi.function.ScalarFunction;
import io.trino.spi.function.SqlNullable;
import io.trino.spi.function.SqlType;
import io.trino.spi.type.StandardTypes;

public final class StringFunctions
{
    private StringFunctions(){}

    @Description("String to int")
    @ScalarFunction("atoi")
    @SqlNullable @SqlType(StandardTypes.BIGINT)
    public static Long Atoi(@SqlType(StandardTypes.VARCHAR) Slice slice) {
        return Client.Atoi(slice.toStringUtf8());
    }

    /*
     * https://github.com/trinodb/trino/blob/master/core/trino-main/src/main/java/io/trino/operator/scalar/StringFunctions.java
     */
    @Description("ToUpper returns s with all Unicode letters mapped to their upper case")
    @ScalarFunction("toupper")
    @LiteralParameters("x")
    @SqlType("varchar(x)")
    public static Slice ToUpper(@SqlType("varchar(x)") Slice slice) {
        return utf8Slice(Client.ToUpper(slice.toStringUtf8()));
    }
}
