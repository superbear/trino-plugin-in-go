package com.superbear.trino.plugin.scalar;

import static io.trino.spi.type.BigintType.BIGINT;
import static io.trino.spi.type.VarcharType.createVarcharType;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

import com.superbear.trino.plugin.ExampleFunctionsPlugin;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import io.trino.metadata.InternalFunctionBundle;
import io.trino.operator.scalar.AbstractTestFunctions;
import io.trino.sql.query.QueryAssertions;

@TestInstance(PER_CLASS)
public class TestStringFunctions
        extends AbstractTestFunctions
{

    private QueryAssertions assertions;

    @BeforeAll
    public void init()
    {
        assertions = new QueryAssertions();

        assertions
            .addFunctions(InternalFunctionBundle
                          .extractFunctions(new ExampleFunctionsPlugin().getFunctions()));
    }

    @AfterAll
    public void teardown()
    {
        assertions.close();
        assertions = null;
    }

    @Test
    public void testAtoi()
    {
        assertThat(assertions.function("atoi", "VARCHAR ''"))
            .isNull(BIGINT);
        assertThat(assertions.function("atoi", "VARCHAR '123'"))
            .isEqualTo(123L);
        assertThat(assertions.function("atoi", "VARCHAR '3.14'"))
            .isNull(BIGINT);
        assertThat(assertions.function("atoi", "VARCHAR '1_2_3_4_5'"))
            .isNull(BIGINT);
        assertThat(assertions.function("atoi", "VARCHAR '0'"))
            .isEqualTo(0L);
        assertThat(assertions.function("atoi", "VARCHAR '-2'"))
            .isEqualTo(-2L);
    }

    @Test
    public void testToUpper()
    {
        assertThat(assertions.function("toupper", "''"))
            .hasType(createVarcharType(0))
            .isEqualTo("");
        assertThat(assertions.function("toupper", "'hello world'"))
            .hasType(createVarcharType(11))
            .isEqualTo("HELLO WORLD");
    }
}
