package com.hpe.automatization.test.testSuiteDataloadService;

import com.hpe.dataload.generator.orm.model.ActionDetails;
import com.hpe.dataload.generator.ws.DataloadGenServiceImpl;
import org.junit.Test;

import javax.swing.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class TestDataloadGenServiceImpl {

    DataloadGenServiceImpl dataloadGenService = null;

    private DataloadGenServiceImpl getDataloadGenServiceImpl(){
        if(this.dataloadGenService == null) {
            return new DataloadGenServiceImpl();
        }else{
            return this.dataloadGenService;
        }

    }

    @Test
    public void saveActionsTest(){
        getDataloadGenServiceImpl().addActions(getActionString());
    }
    @Test
    public void saveActionsConfigurationTest(){
        List<ActionDetails> actionDetails = new ArrayList<>();
        actionDetails.add(new ActionDetails("prova"));
        Response response = getDataloadGenServiceImpl().saveActionsConfiguration(actionDetails);
    }

    private String getActionString() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<action xmlns:ns2=\"http://www.xml.td.hp.com/Model\">\n" +
                "    <ns2:name>zeta</ns2:name>\n" +
                "    <ns2:label></ns2:label>\n" +
                "    <ns2:description></ns2:description>\n" +
                "    <ns2:inputParameters>\n" +
                "        <ns2:inputParameter>\n" +
                "            <ns2:name></ns2:name>\n" +
                "            <ns2:type></ns2:type>\n" +
                "            <ns2:label></ns2:label>\n" +
                "            <ns2:description></ns2:description>\n" +
                "            <ns2:editable></ns2:editable>\n" +
                "            <ns2:mandatory></ns2:mandatory>\n" +
                "            <ns2:helpTexts>\n" +
                "                <ns2:helpText></ns2:helpText>\n" +
                "            </ns2:helpTexts>\n" +
                "        </ns2:inputParameter>\n" +
                "    </ns2:inputParameters>\n" +
                "    <ns2:outputParameters>\n" +
                "        <ns2:outputParameter>\n" +
                "            <ns2:name></ns2:name>\n" +
                "            <ns2:type></ns2:type>\n" +
                "            <ns2:label></ns2:label>\n" +
                "            <ns2:editable></ns2:editable>\n" +
                "            <ns2:includeInStateVector></ns2:includeInStateVector>\n" +
                "            <ns2:stateVectorName></ns2:stateVectorName>\n" +
                "        </ns2:outputParameter>\n" +
                "    </ns2:outputParameters>\n" +
                "</action>\n";
    }

    @Test
    public void testSaveActionsConfiguration(){
        getDataloadGenServiceImpl().saveActionsConfiguration(getListActionDetails());
    }

    private List<ActionDetails> getListActionDetails() {
        List<ActionDetails> actionDetails = new ArrayList<>();
        actionDetails.add(new ActionDetails("a"));
        actionDetails.add(new ActionDetails("b"));
        actionDetails.add(new ActionDetails("bnbvcx"));

        return actionDetails;
    }
}
