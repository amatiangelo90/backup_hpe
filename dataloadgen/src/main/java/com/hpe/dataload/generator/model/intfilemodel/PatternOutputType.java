
package com.hpe.dataload.generator.model.intfilemodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for pattern_outputType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="pattern_outputType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="interventionRequired" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tt_recall" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tt_backbone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="noProblemDetected" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="wifi_actions" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pattern_outputType", propOrder = {
        "interventionRequired",
        "problemNotSolved",
        "noProblemDetected",
        "massiveDisservice",
        "problemSolved"

})
public class PatternOutputType {

    @XmlElement(name = "intervention_required", required = true)
    protected String interventionRequired;
    @XmlElement(name = "problem_not_solved", required = true)
    protected String problemNotSolved;
    @XmlElement(name = "no_problem_detected", required = true)
    protected String noProblemDetected;
    @XmlElement(name = "massive_disservice", required = true)
    protected String massiveDisservice;
    @XmlElement(name = "problem_solved", required = true)
    protected String problemSolved;

    /**
     * Gets the value of the interventionRequired property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getInterventionRequired() {
        return interventionRequired;
    }

    public String getProblemSolved() {
        return problemSolved;
    }

    public void setProblemSolved(String problemSolved) {
        this.problemSolved = problemSolved;
    }

    public String getMassiveDisservice() {

        return massiveDisservice;
    }

    public void setMassiveDisservice(String massiveDisservice) {
        this.massiveDisservice = massiveDisservice;
    }

    /**
     * Sets the value of the interventionRequired property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setInterventionRequired(String value) {
        this.interventionRequired = value;
    }

    /**
     * Gets the value of the problemNotSolved property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getProblemNotSolved() {
        return problemNotSolved;
    }

    /**
     * Sets the value of the problemNotSolved property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setProblemNotSolved(String value) {
        this.problemNotSolved = value;
    }

    /**
     * Gets the value of the noProblemDetected property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getNoProblemDetected() {
        return noProblemDetected;
    }

    /**
     * Sets the value of the noProblemDetected property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setNoProblemDetected(String value) {
        this.noProblemDetected = value;
    }

    @Override
    public String toString() {
        return "{" +
                "interventionRequired='" + interventionRequired + '\'' +
                ", problemNotSolved='" + problemNotSolved + '\'' +
                ", noProblemDetected='" + noProblemDetected + '\'' +
                ", massiveDisservice='" + massiveDisservice + '\'' +
                ", problemSolved='" + problemSolved + '\'' +
                '}';
    }
}
