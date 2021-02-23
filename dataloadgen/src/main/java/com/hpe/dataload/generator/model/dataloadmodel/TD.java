
package com.hpe.dataload.generator.model.dataloadmodel;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="solution" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="groups">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="group" type="{http://www.xml.td.hp.com/Model}GroupType" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="actions">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="action" type="{http://www.xml.td.hp.com/Model}ActionType" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="serviceTypes">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="serviceType" type="{http://www.xml.td.hp.com/Model}ServiceTypeType" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="workflowTemplates">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="workflowTemplate" type="{http://www.xml.td.hp.com/Model}WorkflowTemplateType" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="helpTexts">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="helpText" type="{http://www.xml.td.hp.com/Model}HelpTextType" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="defaultBundle" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "solution",
    "version",
    "groups",
    "actions",
    "serviceTypes",
    "workflowTemplates",
    "helpTexts"
})
@XmlRootElement(name = "TD", namespace = "http://www.xml.td.hp.com/Model")
public class TD {

    @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
    protected String solution;
    @XmlElement(namespace = "http://www.xml.td.hp.com/Model", defaultValue = "0")
    protected Integer version;
    @XmlElement(namespace = "http://www.xml.td.hp.com/Model", required = true)
    protected TD.Groups groups;
    @XmlElement(namespace = "http://www.xml.td.hp.com/Model", required = true)
    protected TD.Actions actions;
    @XmlElement(namespace = "http://www.xml.td.hp.com/Model", required = true)
    protected TD.ServiceTypes serviceTypes;
    @XmlElement(namespace = "http://www.xml.td.hp.com/Model", required = true)
    protected TD.WorkflowTemplates workflowTemplates;
    @XmlElement(namespace = "http://www.xml.td.hp.com/Model", required = true)
    protected TD.HelpTexts helpTexts;
    @XmlAttribute(name = "defaultBundle")
    protected String defaultBundle;

    /**
     * Gets the value of the solution property.

     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSolution() {
        return solution;
    }

    /**
     * Sets the value of the solution property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSolution(String value) {
        this.solution = value;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setVersion(Integer value) {
        this.version = value;
    }

    /**
     * Gets the value of the groups property.
     * 
     * @return
     *     possible object is
     *     {@link TD.Groups }
     *     
     */
    public TD.Groups getGroups() {
        return groups;
    }

    /**
     * Sets the value of the groups property.
     * 
     * @param value
     *     allowed object is
     *     {@link TD.Groups }
     *     
     */
    public void setGroups(TD.Groups value) {
        this.groups = value;
    }

    /**
     * Gets the value of the actions property.
     * 
     * @return
     *     possible object is
     *     {@link TD.Actions }
     *     
     */
    public TD.Actions getActions() {
        return actions;
    }

    /**
     * Sets the value of the actions property.
     * 
     * @param value
     *     allowed object is
     *     {@link TD.Actions }
     *     
     */
    public void setActions(TD.Actions value) {
        this.actions = value;
    }

    /**
     * Gets the value of the serviceTypes property.
     * 
     * @return
     *     possible object is
     *     {@link TD.ServiceTypes }
     *     
     */
    public TD.ServiceTypes getServiceTypes() {
        return serviceTypes;
    }

    /**
     * Sets the value of the serviceTypes property.
     * 
     * @param value
     *     allowed object is
     *     {@link TD.ServiceTypes }
     *     
     */
    public void setServiceTypes(TD.ServiceTypes value) {
        this.serviceTypes = value;
    }

    /**
     * Gets the value of the workflowTemplates property.
     * 
     * @return
     *     possible object is
     *     {@link TD.WorkflowTemplates }
     *     
     */
    public TD.WorkflowTemplates getWorkflowTemplates() {
        return workflowTemplates;
    }

    /**
     * Sets the value of the workflowTemplates property.
     * 
     * @param value
     *     allowed object is
     *     {@link TD.WorkflowTemplates }
     *     
     */
    public void setWorkflowTemplates(TD.WorkflowTemplates value) {
        this.workflowTemplates = value;
    }

    /**
     * Gets the value of the helpTexts property.
     * 
     * @return
     *     possible object is
     *     {@link TD.HelpTexts }
     *     
     */
    public TD.HelpTexts getHelpTexts() {
        return helpTexts;
    }

    /**
     * Sets the value of the helpTexts property.
     * 
     * @param value
     *     allowed object is
     *     {@link TD.HelpTexts }
     *     
     */
    public void setHelpTexts(TD.HelpTexts value) {
        this.helpTexts = value;
    }

    /**
     * Gets the value of the defaultBundle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaultBundle() {
        return defaultBundle;
    }

    /**
     * Sets the value of the defaultBundle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaultBundle(String value) {
        this.defaultBundle = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="action" type="{http://www.xml.td.hp.com/Model}ActionType" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "action"
    })
    public static class Actions {

        @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
        protected List<ActionType> action;

        /**
         * Gets the value of the action property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the action property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAction().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ActionType }
         * 
         * 
         */
        public List<ActionType> getAction() {
            if (action == null) {
                action = new ArrayList<ActionType>();
            }
            return this.action;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="group" type="{http://www.xml.td.hp.com/Model}GroupType" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "group"
    })
    public static class Groups {

        @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
        protected List<GroupType> group;

        /**
         * Gets the value of the group property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the group property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getGroup().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link GroupType }
         * 
         * 
         */
        public List<GroupType> getGroup() {
            if (group == null) {
                group = new ArrayList<GroupType>();
            }
            return this.group;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="helpText" type="{http://www.xml.td.hp.com/Model}HelpTextType" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "helpText"
    })
    public static class HelpTexts {

        @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
        protected List<HelpTextType> helpText;

        /**
         * Gets the value of the helpText property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the helpText property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getHelpText().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link HelpTextType }
         * 
         * 
         */
        public List<HelpTextType> getHelpText() {
            if (helpText == null) {
                helpText = new ArrayList<HelpTextType>();
            }
            return this.helpText;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="serviceType" type="{http://www.xml.td.hp.com/Model}ServiceTypeType" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "serviceType"
    })
    public static class ServiceTypes {

        @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
        protected List<ServiceTypeType> serviceType;

        /**
         * Gets the value of the serviceType property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the serviceType property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getServiceType().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ServiceTypeType }
         * 
         * 
         */
        public List<ServiceTypeType> getServiceType() {
            if (serviceType == null) {
                serviceType = new ArrayList<ServiceTypeType>();
            }
            return this.serviceType;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="workflowTemplate" type="{http://www.xml.td.hp.com/Model}WorkflowTemplateType" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "workflowTemplate"
    })
    public static class WorkflowTemplates {

        @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
        protected List<WorkflowTemplateType> workflowTemplate;

        /**
         * Gets the value of the workflowTemplate property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the workflowTemplate property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getWorkflowTemplate().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link WorkflowTemplateType }
         * 
         * 
         */
        public List<WorkflowTemplateType> getWorkflowTemplate() {
            if (workflowTemplate == null) {
                workflowTemplate = new ArrayList<WorkflowTemplateType>();
            }
            return this.workflowTemplate;
        }

    }

}
