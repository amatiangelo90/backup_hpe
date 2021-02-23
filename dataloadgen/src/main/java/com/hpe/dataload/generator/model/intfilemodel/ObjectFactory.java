
package com.hpe.dataload.generator.model.intfilemodel;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.hpe.dataload.generator.intfilemodel package.
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _DataSet_QNAME = new QName("", "data-set");
    private final static QName _BlockExecutionFlowTypeBlockName_QNAME = new QName("", "block_name");
    private final static QName _BlockExecutionFlowTypeMatchCond_QNAME = new QName("", "match_cond");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.hpe.dataload.generator.intfilemodel
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DataSetItem }
     * 
     */
    public DataSetItem createDataSetType() {
        return new DataSetItem();
    }

    /**
     * Create an instance of {@link ItemType }
     * 
     */
    public ItemType createItemType() {
        return new ItemType();
    }

    /**
     * Create an instance of {@link BlocksExecutionGroupType }
     * 
     */
    public BlocksExecutionGroupType createBlocksExecutionGroupType() {
        return new BlocksExecutionGroupType();
    }

    /**
     * Create an instance of {@link BlockExecutionFlowType }
     * 
     */
    public BlockExecutionFlowType createBlockExecutionFlowType() {
        return new BlockExecutionFlowType();
    }

    /**
     * Create an instance of {@link PatternFilterType }
     * 
     */
    public PatternFilterType createPatternFilterType() {
        return new PatternFilterType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataSetItem }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "data-set")
    public JAXBElement<DataSetItem> createDataSet(DataSetItem value) {
        return new JAXBElement<DataSetItem>(_DataSet_QNAME, DataSetItem.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "block_name", scope = BlockExecutionFlowType.class)
    public JAXBElement<String> createBlockExecutionFlowTypeBlockName(String value) {
        return new JAXBElement<String>(_BlockExecutionFlowTypeBlockName_QNAME, String.class, BlockExecutionFlowType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "match_cond", scope = BlockExecutionFlowType.class)
    public JAXBElement<String> createBlockExecutionFlowTypeMatchCond(String value) {
        return new JAXBElement<String>(_BlockExecutionFlowTypeMatchCond_QNAME, String.class, BlockExecutionFlowType.class, value);
    }

}
