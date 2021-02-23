package com.hpe.automatization.test.testSuiteDataloadService;

import com.hpe.dataload.generator.exception.DataloadGeneratorException;
import org.junit.Test;

import static com.hpe.dataload.generator.engine.GroupBuilder.buildGroupFromIntermediateFile;

public class TestManageNextGroupAction {

    @Test
    public void generateGroupWithNextGroupState() throws DataloadGeneratorException {
        String s = buildGroupFromIntermediateFile(getIntermediateFile());
        System.out.println(s);
    }

    private String getIntermediateFile() {
        return "<tbs-flow xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'><pattern-input><segment>Dataload_TBS_RES</segment><technology>cadutaConnessione</technology><class-value>0</class-value><service>*</service><is-unique-cpe>*</is-unique-cpe><rg-type>*</rg-type><problem-type>*</problem-type></pattern-input><nodes><node><id>1585145262703</id><name>retryOOcs</name><x-pos>474</x-pos><y-pos>58</y-pos></node><node><id>1585145262948</id><name>EXIT</name><x-pos>604</x-pos><y-pos>153</y-pos><pattern-output>10000</pattern-output></node><node><id>1585145264623</id><name>EXIT</name><x-pos>412</x-pos><y-pos>291</y-pos><pattern-output>00001</pattern-output></node><node><id>1585148399150</id><name>NEXT_G_cadutaConnession</name><x-pos>446</x-pos><y-pos>158.86648559570312</y-pos></node><node><id>1585148399535</id><name>EXIT</name><x-pos>576</x-pos><y-pos>253.86648559570312</y-pos><pattern-output>10000</pattern-output></node></nodes><arches><arch><source>1585145262703</source><target>1585148399150</target><match-condition>00</match-condition></arch><arch><source>1585145262703</source><target>1585148399150</target><match-condition>01</match-condition></arch><arch><source>1585145262703</source><target>1585145262948</target><match-condition>ELSE</match-condition></arch><arch><source>1585148399150</source><target>1585145264623</target><match-condition>navigazioneLentaWifi</match-condition></arch><arch><source>1585148399150</source><target>1585148399535</target><match-condition>ELSE</match-condition></arch></arches></tbs-flow>";
    }
}
