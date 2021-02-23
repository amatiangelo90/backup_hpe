
package com.hpe.dataload.generator.model.intfilemodel;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for blocks_execution_groupType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="blocks_execution_groupType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="block_execution_flow" type="{}block_execution_flowType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "blocks_execution_groupType", propOrder = {
    "blockExecutionFlow"
})
public class BlocksExecutionGroupType {

    @XmlElement(name = "block_execution_flow")
    protected List<BlockExecutionFlowType> blockExecutionFlow;

    /**
     * Gets the value of the blockExecutionFlow property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the blockExecutionFlow property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBlockExecutionFlow().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BlockExecutionFlowType }
     * 
     * 
     */
    public List<BlockExecutionFlowType> getBlockExecutionFlow() {
        if (blockExecutionFlow == null) {
            blockExecutionFlow = new ArrayList<BlockExecutionFlowType>();
        }
        return this.blockExecutionFlow;
    }

    @Override
    public String toString() {
        return "BlocksExecutionGroupType{" +
                "blockExecutionFlow=" + blockExecutionFlow +
                '}';
    }
}
