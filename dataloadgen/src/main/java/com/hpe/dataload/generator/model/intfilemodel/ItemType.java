
package com.hpe.dataload.generator.model.intfilemodel;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for itemType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="itemType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pattern_filter" type="{}pattern_filterType"/>
 *         &lt;element name="blocks_execution_group" type="{}blocks_execution_groupType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "itemType", propOrder = {
        "patternFilter",
        "blocksExecutionGroup",
        "patternOutput"
})
public class ItemType {

    @XmlElement(name = "pattern_filter", required = true)
    protected PatternFilterType patternFilter;
    @XmlElement(name = "blocks_execution_group", required = true)
    protected BlocksExecutionGroupType blocksExecutionGroup;
    @XmlElement(name = "pattern_output", required = true)
    protected PatternOutputType patternOutput;
    /**
     * Gets the value of the patternFilter property.
     *
     * @return
     *     possible object is
     *     {@link PatternFilterType }
     *
     */
    public PatternFilterType getPatternFilter() {
        return patternFilter;
    }

    /**
     * Sets the value of the patternFilter property.
     *
     * @param value
     *     allowed object is
     *     {@link PatternFilterType }
     *
     */
    public void setPatternFilter(PatternFilterType value) {
        this.patternFilter = value;
    }

    /**
     * Gets the value of the blocksExecutionGroup property.
     *
     * @return
     *     possible object is
     *     {@link BlocksExecutionGroupType }
     *
     */
    public BlocksExecutionGroupType getBlocksExecutionGroup() {
        return blocksExecutionGroup;
    }

    /**
     * Sets the value of the blocksExecutionGroup property.
     *
     * @param value
     *     allowed object is
     *     {@link BlocksExecutionGroupType }
     *
     */
    public void setBlocksExecutionGroup(BlocksExecutionGroupType value) {
        this.blocksExecutionGroup = value;
    }

    public PatternOutputType getPatternOutput() {
        return patternOutput;
    }

    public void setPatternOutput(PatternOutputType patternOutput) {
        this.patternOutput = patternOutput;
    }

    @Override
    public String toString() {
        return "ItemType{" +
                "patternFilter=" + patternFilter +
                ", blocksExecutionGroup=" + blocksExecutionGroup +
                ", patternOutput=" + patternOutput +
                '}';
    }
}
