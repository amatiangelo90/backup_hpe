package com.hpe.automatization.test.testsuite;


import com.hpe.automatization.test.GenDataload_xmlPathInput;
import com.hpe.automatization.test.GenDataload_intExternal;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        GenDataload_xmlPathInput.class,
        GenDataload_intExternal.class
})
public class TestSuiteDataloadGen {
}
