
package com.hpe.dataload.generator.model.intfilemodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for block_execution_flowType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="block_execution_flowType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="block_name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="match_cond" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="00"/>
 *               &lt;enumeration value="01"/>
 *               &lt;enumeration value="02"/>
 *               &lt;enumeration value="03"/>
 *               &lt;enumeration value="RPV"/>
 *               &lt;enumeration value="EXIT"/>
 *               &lt;enumeration value="05"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "block_execution_flowType", propOrder = {
        "blockName",
        "blockId",
        "xPosition",
        "yPosition",
        "matchCond"
})
public class BlockExecutionFlowType {

    @XmlElement(name = "block_name", required = true)
    protected String blockName;
    @XmlElement(name = "block_id", required = true)
    protected String blockId;
    @XmlElement(name = "x_position", required = true)
    protected String xPosition;
    @XmlElement(name = "y_position", required = true)
    protected String yPosition;
    @XmlElement(name = "match_cond")
    protected String matchCond;

    @Override
    public String toString() {
        return "{" +
                "blockName='" + blockName + '\'' +
                ", matchCond='" + matchCond + '\'' +
                '}';
    }

    /**
     * Gets the value of the blockName property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getBlockName() {
        return blockName;
    }

    /**
     * Sets the value of the blockName property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setBlockName(String value) {
        this.blockName = value;
    }

    /**
     * Gets the value of the matchCond property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getMatchCond() {
        return matchCond;
    }

    /**
     * Sets the value of the matchCond property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setMatchCond(String value) {
        this.matchCond = value;
    }


    public String getBlockId() {
        return blockId;
    }

    public void setBlockId(String blockId) {
        this.blockId = blockId;
    }

    public String getxPosition() {
        return xPosition;
    }

    public void setxPosition(String xPosition) {
        this.xPosition = xPosition;
    }

    public String getyPosition() {
        return yPosition;
    }

    public void setyPosition(String yPosition) {
        this.yPosition = yPosition;
    }
}
