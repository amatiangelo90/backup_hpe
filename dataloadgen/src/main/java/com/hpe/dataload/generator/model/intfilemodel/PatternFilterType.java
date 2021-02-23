
package com.hpe.dataload.generator.model.intfilemodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for pattern_filterType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="pattern_filterType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="technology">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="SLU"/>
 *               &lt;enumeration value="ULL-MSAN"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="service" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cpe_type" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="rg_type" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="problem_type">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="D"/>
 *               &lt;enumeration value="I"/>
 *               &lt;enumeration value="P"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="pattern_number" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dslam" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="class_value" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="segment" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pattern_filterType", propOrder = {
        "technology",
        "service",
        "isUniqueCpe",
        "rgType",
        "problemType",
        "classValue",
        "segment"
})
public class PatternFilterType {

    @XmlElement(required = true)
    protected String technology;
    @XmlElement(required = true)
    protected String service;
    @XmlElement(name = "is_unique_cpe", required = true)
    protected String isUniqueCpe;
    @XmlElement(name = "rg_type", required = true)
    protected String rgType;
    @XmlElement(name = "problem_type", required = true)
    protected String problemType;
    @XmlElement(name = "class_value", required = true)
    protected String classValue;
    @XmlElement(required = true)
    protected String segment;

    /**
     * Gets the value of the technology property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getTechnology() {
        return technology;
    }

    /**
     * Sets the value of the technology property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setTechnology(String value) {
        this.technology = value;
    }

    /**
     * Gets the value of the service property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getService() {
        return service;
    }

    /**
     * Sets the value of the service property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setService(String value) {
        this.service = value;
    }

    /**
     * Gets the value of the isUniqueCpe property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getIsUniqueCpe() {
        return isUniqueCpe;
    }

    /**
     * Sets the value of the isUniqueCpe property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setIsUniqueCpe(String value) {
        this.isUniqueCpe = value;
    }

    /**
     * Gets the value of the rgType property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getRgType() {
        return rgType;
    }

    /**
     * Sets the value of the rgType property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setRgType(String value) {
        this.rgType = value;
    }

    /**
     * Gets the value of the problemType property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getProblemType() {
        return problemType;
    }

    /**
     * Sets the value of the problemType property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setProblemType(String value) {
        this.problemType = value;
    }

    /**
     * Gets the value of the classValue property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getClassValue() {
        return classValue;
    }

    /**
     * Sets the value of the classValue property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setClassValue(String value) {
        this.classValue = value;
    }

    /**
     * Gets the value of the segment property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getSegment() {
        return segment;
    }

    /**
     * Sets the value of the segment property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setSegment(String value) {
        this.segment = value;
    }

    @Override
    public String toString() {
        return "PatternFilterType{" +
                "technology='" + technology + '\'' +
                ", service='" + service + '\'' +
                ", isUniqueCpe='" + isUniqueCpe + '\'' +
                ", rgType='" + rgType + '\'' +
                ", problemType='" + problemType + '\'' +
                ", classValue='" + classValue + '\'' +
                ", segment='" + segment + '\'' +
                '}';
    }
}
