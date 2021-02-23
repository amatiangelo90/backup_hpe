
package com.hpe.dataload.generator.model.dataloadmodel;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for WorkflowTemplateType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WorkflowTemplateType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="serviceType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="actionName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="actionSolution" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="workflow" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="executionNode" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="ExecuteMacro"/>
 *               &lt;enumeration value="StartJobAndWait"/>
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
@XmlType(name = "WorkflowTemplateType", namespace = "http://www.xml.td.hp.com/Model", propOrder = {
    "serviceType",
    "actionName",
    "actionSolution",
    "workflow",
    "executionNode"
})
@XmlRootElement
public class WorkflowTemplateType {

    @XmlElement(namespace = "http://www.xml.td.hp.com/Model", required = true)
    protected String serviceType;
    @XmlElement(namespace = "http://www.xml.td.hp.com/Model", required = true)
    protected String actionName;
    @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
    protected String actionSolution;
    @XmlElement(namespace = "http://www.xml.td.hp.com/Model", required = true)
    protected String workflow;
    @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
    protected String executionNode;

    /**
     * Gets the value of the serviceType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceType() {
        return serviceType;
    }

    /**
     * Sets the value of the serviceType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceType(String value) {
        this.serviceType = value;
    }

    /**
     * Gets the value of the actionName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActionName() {
        return actionName;
    }

    /**
     * Sets the value of the actionName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActionName(String value) {
        this.actionName = value;
    }

    /**
     * Gets the value of the actionSolution property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActionSolution() {
        return actionSolution;
    }

    /**
     * Sets the value of the actionSolution property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActionSolution(String value) {
        this.actionSolution = value;
    }

    /**
     * Gets the value of the workflow property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkflow() {
        return workflow;
    }

    /**
     * Sets the value of the workflow property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkflow(String value) {
        this.workflow = value;
    }

    /**
     * Gets the value of the executionNode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExecutionNode() {
        return executionNode;
    }

    /**
     * Sets the value of the executionNode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExecutionNode(String value) {
        this.executionNode = value;
    }

}
