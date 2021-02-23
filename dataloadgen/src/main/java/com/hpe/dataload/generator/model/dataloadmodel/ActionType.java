
package com.hpe.dataload.generator.model.dataloadmodel;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.*;


/**
 * <p>Java class for ActionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ActionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="solution" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="label" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="type" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="recover/resolve"/>
 *               &lt;enumeration value="test"/>
 *               &lt;enumeration value="audit"/>
 *               &lt;enumeration value="read-only-test"/>
 *               &lt;enumeration value="internal"/>
 *               &lt;enumeration value="escalate"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="actionMode" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="Open Loop"/>
 *               &lt;enumeration value="Closed Loop"/>
 *               &lt;enumeration value="None"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="outputParser" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="None"/>
 *               &lt;enumeration value="xpath"/>
 *               &lt;enumeration value="regex"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="dispatchType" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="HPSA"/>
 *               &lt;enumeration value="OO"/>
 *               &lt;enumeration value="Shell Script"/>
 *               &lt;enumeration value="Alarm"/>
 *               &lt;enumeration value="Trouble Ticket"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="troubleTicketAction" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="create_tt"/>
 *               &lt;enumeration value="update_tt"/>
 *               &lt;enumeration value="check_tt"/>
 *               &lt;enumeration value="close_tt"/>
 *               &lt;enumeration value="dissociate_tt"/>
 *               &lt;enumeration value="associate_tt"/>
 *               &lt;enumeration value="dissociate_close_tt"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="alarmAction" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="update_alarm"/>
 *               &lt;enumeration value="terminate_alarm"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="cost" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="throbber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="icon" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="averageDurationTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="includeMajorInStateVector" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="majorStateVectorName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="includeMinorInStateVector" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="minorStateVectorName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TDSynchQueue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="inputParameters" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="inputParameter" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="type">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;enumeration value="String"/>
 *                                   &lt;enumeration value="Integer"/>
 *                                   &lt;enumeration value="Float"/>
 *                                   &lt;enumeration value="Boolean"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="label" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="historyLabel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="defaultValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="editable" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="hidden" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="includeInStateVector" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="stateVectorName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="extractFromStateVector" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="includeInMetaData" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="metaDataName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="extractFromMetaData" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="includeInStickyData" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="stickyDataName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="displayFormatType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="displayFormat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="mandatory" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="setAsCustomer" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;simpleContent>
 *                                   &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>boolean">
 *                                     &lt;attribute name="field" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                   &lt;/extension>
 *                                 &lt;/simpleContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="setAsServiceType" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;simpleContent>
 *                                   &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>boolean">
 *                                     &lt;attribute name="field" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                   &lt;/extension>
 *                                 &lt;/simpleContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="setAsProblem" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;simpleContent>
 *                                   &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>boolean">
 *                                     &lt;attribute name="field" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                   &lt;/extension>
 *                                 &lt;/simpleContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;choice>
 *                               &lt;element name="setAsResourceType" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                               &lt;element name="setAsResourceInstance" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;/choice>
 *                             &lt;element name="helpTexts" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="helpText" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="icon" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="inputGroups" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="inputGroup" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="parameter" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="outputParameters" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="outputParameter" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="type">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                   &lt;enumeration value="String"/>
 *                                   &lt;enumeration value="Integer"/>
 *                                   &lt;enumeration value="Float"/>
 *                                   &lt;enumeration value="Boolean"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="label" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="historyLabel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="defaultValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="editable" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="hidden" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="includeInStateVector" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="stateVectorName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="includeInMetaData" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="metaDataName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="includeInStickyData" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="stickyDataName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="displayFormatType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="displayFormat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="mandatory" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="setAsCustomer" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;simpleContent>
 *                                   &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>boolean">
 *                                     &lt;attribute name="field" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                   &lt;/extension>
 *                                 &lt;/simpleContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="setAsServiceType" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;simpleContent>
 *                                   &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>boolean">
 *                                     &lt;attribute name="field" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                   &lt;/extension>
 *                                 &lt;/simpleContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="setAsProblem" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;simpleContent>
 *                                   &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>boolean">
 *                                     &lt;attribute name="field" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                   &lt;/extension>
 *                                 &lt;/simpleContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="helpTexts" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="helpText" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="icon" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="serviceValidation" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="tabbed" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ActionType", namespace = "http://www.xml.td.hp.com/Model", propOrder = {
    "name",
    "solution",
    "label",
    "description",
    "type",
    "actionMode",
    "outputParser",
    "dispatchType",
    "troubleTicketAction",
    "alarmAction",
    "cost",
    "throbber",
    "icon",
    "averageDurationTime",
    "includeMajorInStateVector",
    "majorStateVectorName",
    "includeMinorInStateVector",
    "minorStateVectorName",
    "tdSynchQueue",
    "inputParameters",
    "inputGroups",
    "outputParameters"
})
@XmlRootElement
public class ActionType {

    @XmlElement(namespace = "http://www.xml.td.hp.com/Model", required = true)
    protected String name;
    @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
    protected String solution;
    @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
    protected String label;
    @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
    protected String description;
    @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
    protected String type;
    @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
    protected String actionMode;
    @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
    protected String outputParser;
    @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
    protected String dispatchType;
    @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
    protected String troubleTicketAction;
    @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
    protected String alarmAction;
    @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
    protected String cost;
    @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
    protected String throbber;
    @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
    protected String icon;
    @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
    protected Integer averageDurationTime;
    @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
    protected Boolean includeMajorInStateVector;
    @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
    protected String majorStateVectorName;
    @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
    protected Boolean includeMinorInStateVector;
    @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
    protected String minorStateVectorName;
    @XmlElement(name = "TDSynchQueue", namespace = "http://www.xml.td.hp.com/Model")
    protected String tdSynchQueue;
    @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
    protected ActionType.InputParameters inputParameters;
    @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
    protected ActionType.InputGroups inputGroups;
    @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
    protected ActionType.OutputParameters outputParameters;
    @XmlAttribute(name = "serviceValidation")
    protected Boolean serviceValidation;
    @XmlAttribute(name = "tabbed")
    protected Boolean tabbed;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

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
     * Gets the value of the label property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLabel() {
        return label;
    }

    /**
     * Sets the value of the label property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLabel(String value) {
        this.label = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the actionMode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActionMode() {
        return actionMode;
    }

    /**
     * Sets the value of the actionMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActionMode(String value) {
        this.actionMode = value;
    }

    /**
     * Gets the value of the outputParser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutputParser() {
        return outputParser;
    }

    /**
     * Sets the value of the outputParser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutputParser(String value) {
        this.outputParser = value;
    }

    /**
     * Gets the value of the dispatchType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDispatchType() {
        return dispatchType;
    }

    /**
     * Sets the value of the dispatchType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDispatchType(String value) {
        this.dispatchType = value;
    }

    /**
     * Gets the value of the troubleTicketAction property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTroubleTicketAction() {
        return troubleTicketAction;
    }

    /**
     * Sets the value of the troubleTicketAction property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTroubleTicketAction(String value) {
        this.troubleTicketAction = value;
    }

    /**
     * Gets the value of the alarmAction property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlarmAction() {
        return alarmAction;
    }

    /**
     * Sets the value of the alarmAction property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlarmAction(String value) {
        this.alarmAction = value;
    }

    /**
     * Gets the value of the cost property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCost() {
        return cost;
    }

    /**
     * Sets the value of the cost property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCost(String value) {
        this.cost = value;
    }

    /**
     * Gets the value of the throbber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getThrobber() {
        return throbber;
    }

    /**
     * Sets the value of the throbber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setThrobber(String value) {
        this.throbber = value;
    }

    /**
     * Gets the value of the icon property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIcon() {
        return icon;
    }

    /**
     * Sets the value of the icon property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIcon(String value) {
        this.icon = value;
    }

    /**
     * Gets the value of the averageDurationTime property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAverageDurationTime() {
        return averageDurationTime;
    }

    /**
     * Sets the value of the averageDurationTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAverageDurationTime(Integer value) {
        this.averageDurationTime = value;
    }

    /**
     * Gets the value of the includeMajorInStateVector property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIncludeMajorInStateVector() {
        return includeMajorInStateVector;
    }

    /**
     * Sets the value of the includeMajorInStateVector property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIncludeMajorInStateVector(Boolean value) {
        this.includeMajorInStateVector = value;
    }

    /**
     * Gets the value of the majorStateVectorName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMajorStateVectorName() {
        return majorStateVectorName;
    }

    /**
     * Sets the value of the majorStateVectorName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMajorStateVectorName(String value) {
        this.majorStateVectorName = value;
    }

    /**
     * Gets the value of the includeMinorInStateVector property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIncludeMinorInStateVector() {
        return includeMinorInStateVector;
    }

    /**
     * Sets the value of the includeMinorInStateVector property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIncludeMinorInStateVector(Boolean value) {
        this.includeMinorInStateVector = value;
    }

    /**
     * Gets the value of the minorStateVectorName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMinorStateVectorName() {
        return minorStateVectorName;
    }

    /**
     * Sets the value of the minorStateVectorName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMinorStateVectorName(String value) {
        this.minorStateVectorName = value;
    }

    /**
     * Gets the value of the tdSynchQueue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTDSynchQueue() {
        return tdSynchQueue;
    }

    /**
     * Sets the value of the tdSynchQueue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTDSynchQueue(String value) {
        this.tdSynchQueue = value;
    }

    /**
     * Gets the value of the inputParameters property.
     * 
     * @return
     *     possible object is
     *     {@link ActionType.InputParameters }
     *     
     */
    public ActionType.InputParameters getInputParameters() {
        return inputParameters;
    }

    /**
     * Sets the value of the inputParameters property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActionType.InputParameters }
     *     
     */
    public void setInputParameters(ActionType.InputParameters value) {
        this.inputParameters = value;
    }

    /**
     * Gets the value of the inputGroups property.
     * 
     * @return
     *     possible object is
     *     {@link ActionType.InputGroups }
     *     
     */
    public ActionType.InputGroups getInputGroups() {
        return inputGroups;
    }

    /**
     * Sets the value of the inputGroups property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActionType.InputGroups }
     *     
     */
    public void setInputGroups(ActionType.InputGroups value) {
        this.inputGroups = value;
    }

    /**
     * Gets the value of the outputParameters property.
     * 
     * @return
     *     possible object is
     *     {@link ActionType.OutputParameters }
     *     
     */
    public ActionType.OutputParameters getOutputParameters() {
        return outputParameters;
    }

    /**
     * Sets the value of the outputParameters property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActionType.OutputParameters }
     *     
     */
    public void setOutputParameters(ActionType.OutputParameters value) {
        this.outputParameters = value;
    }

    /**
     * Gets the value of the serviceValidation property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isServiceValidation() {
        return serviceValidation;
    }

    /**
     * Sets the value of the serviceValidation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setServiceValidation(Boolean value) {
        this.serviceValidation = value;
    }

    /**
     * Gets the value of the tabbed property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTabbed() {
        return tabbed;
    }

    /**
     * Sets the value of the tabbed property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTabbed(Boolean value) {
        this.tabbed = value;
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
     *         &lt;element name="inputGroup" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="parameter" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
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
    @XmlType(name = "", propOrder = {
        "inputGroup"
    })
    public static class InputGroups {

        @XmlElement(namespace = "http://www.xml.td.hp.com/Model", required = true)
        protected List<ActionType.InputGroups.InputGroup> inputGroup;

        /**
         * Gets the value of the inputGroup property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the inputGroup property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getInputGroup().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ActionType.InputGroups.InputGroup }
         * 
         * 
         */
        public List<ActionType.InputGroups.InputGroup> getInputGroup() {
            if (inputGroup == null) {
                inputGroup = new ArrayList<ActionType.InputGroups.InputGroup>();
            }
            return this.inputGroup;
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
         *         &lt;element name="parameter" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
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
            "parameter"
        })
        public static class InputGroup {

            @XmlElement(namespace = "http://www.xml.td.hp.com/Model", required = true)
            protected List<String> parameter;

            /**
             * Gets the value of the parameter property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the parameter property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getParameter().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link String }
             * 
             * 
             */
            public List<String> getParameter() {
                if (parameter == null) {
                    parameter = new ArrayList<String>();
                }
                return this.parameter;
            }

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
     *         &lt;element name="inputParameter" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="type">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;enumeration value="String"/>
     *                         &lt;enumeration value="Integer"/>
     *                         &lt;enumeration value="Float"/>
     *                         &lt;enumeration value="Boolean"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="label" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="historyLabel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="defaultValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="editable" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="hidden" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="includeInStateVector" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="stateVectorName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="extractFromStateVector" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="includeInMetaData" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="metaDataName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="extractFromMetaData" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="includeInStickyData" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="stickyDataName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="displayFormatType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="displayFormat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="mandatory" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="setAsCustomer" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;simpleContent>
     *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>boolean">
     *                           &lt;attribute name="field" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                         &lt;/extension>
     *                       &lt;/simpleContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="setAsServiceType" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;simpleContent>
     *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>boolean">
     *                           &lt;attribute name="field" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                         &lt;/extension>
     *                       &lt;/simpleContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="setAsProblem" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;simpleContent>
     *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>boolean">
     *                           &lt;attribute name="field" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                         &lt;/extension>
     *                       &lt;/simpleContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;choice>
     *                     &lt;element name="setAsResourceType" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                     &lt;element name="setAsResourceInstance" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;/choice>
     *                   &lt;element name="helpTexts" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="helpText" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="icon" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
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
    @XmlType(name = "", propOrder = {
        "inputParameter"
    })
    public static class InputParameters {

        @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
        protected List<ActionType.InputParameters.InputParameter> inputParameter;

        /**
         * Gets the value of the inputParameter property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the inputParameter property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getInputParameter().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ActionType.InputParameters.InputParameter }
         * 
         * 
         */
        public List<ActionType.InputParameters.InputParameter> getInputParameter() {
            if (inputParameter == null) {
                inputParameter = new ArrayList<ActionType.InputParameters.InputParameter>();
            }
            return this.inputParameter;
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
         *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="type">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;enumeration value="String"/>
         *               &lt;enumeration value="Integer"/>
         *               &lt;enumeration value="Float"/>
         *               &lt;enumeration value="Boolean"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="label" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="historyLabel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="defaultValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="editable" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="hidden" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="includeInStateVector" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="stateVectorName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="extractFromStateVector" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="includeInMetaData" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="metaDataName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="extractFromMetaData" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="includeInStickyData" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="stickyDataName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="displayFormatType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="displayFormat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="mandatory" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="setAsCustomer" minOccurs="0">
         *           &lt;complexType>
         *             &lt;simpleContent>
         *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>boolean">
         *                 &lt;attribute name="field" type="{http://www.w3.org/2001/XMLSchema}string" />
         *               &lt;/extension>
         *             &lt;/simpleContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="setAsServiceType" minOccurs="0">
         *           &lt;complexType>
         *             &lt;simpleContent>
         *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>boolean">
         *                 &lt;attribute name="field" type="{http://www.w3.org/2001/XMLSchema}string" />
         *               &lt;/extension>
         *             &lt;/simpleContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="setAsProblem" minOccurs="0">
         *           &lt;complexType>
         *             &lt;simpleContent>
         *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>boolean">
         *                 &lt;attribute name="field" type="{http://www.w3.org/2001/XMLSchema}string" />
         *               &lt;/extension>
         *             &lt;/simpleContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;choice>
         *           &lt;element name="setAsResourceType" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *           &lt;element name="setAsResourceInstance" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;/choice>
         *         &lt;element name="helpTexts" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="helpText" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="icon" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
            "name",
            "type",
            "label",
            "historyLabel",
            "description",
            "defaultValue",
            "editable",
            "hidden",
            "includeInStateVector",
            "stateVectorName",
            "extractFromStateVector",
            "includeInMetaData",
            "metaDataName",
            "extractFromMetaData",
            "includeInStickyData",
            "stickyDataName",
            "displayFormatType",
            "displayFormat",
            "mandatory",
            "setAsCustomer",
            "setAsServiceType",
            "setAsProblem",
            "setAsResourceType",
            "setAsResourceInstance",
            "helpTexts",
            "icon"
        })
        public static class InputParameter {

            @XmlElement(namespace = "http://www.xml.td.hp.com/Model", required = true)
            protected String name;
            @XmlElement(namespace = "http://www.xml.td.hp.com/Model", required = true)
            protected String type;
            @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
            protected String label;
            @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
            protected String historyLabel;
            @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
            protected String description;
            @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
            protected String defaultValue;
            @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
            protected Boolean editable;
            @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
            protected Boolean hidden;
            @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
            protected Boolean includeInStateVector;
            @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
            protected String stateVectorName;
            @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
            protected String extractFromStateVector;
            @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
            protected Boolean includeInMetaData;
            @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
            protected String metaDataName;
            @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
            protected String extractFromMetaData;
            @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
            protected Boolean includeInStickyData;
            @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
            protected String stickyDataName;
            @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
            protected String displayFormatType;
            @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
            protected String displayFormat;
            @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
            protected Boolean mandatory;
            @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
            protected ActionType.InputParameters.InputParameter.SetAsCustomer setAsCustomer;
            @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
            protected ActionType.InputParameters.InputParameter.SetAsServiceType setAsServiceType;
            @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
            protected ActionType.InputParameters.InputParameter.SetAsProblem setAsProblem;
            @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
            protected Boolean setAsResourceType;
            @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
            protected Boolean setAsResourceInstance;
            @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
            protected ActionType.InputParameters.InputParameter.HelpTexts helpTexts;
            @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
            protected String icon;

            /**
             * Gets the value of the name property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getName() {
                return name;
            }

            /**
             * Sets the value of the name property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setName(String value) {
                this.name = value;
            }

            /**
             * Gets the value of the type property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getType() {
                return type;
            }

            /**
             * Sets the value of the type property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setType(String value) {
                this.type = value;
            }

            /**
             * Gets the value of the label property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getLabel() {
                return label;
            }

            /**
             * Sets the value of the label property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setLabel(String value) {
                this.label = value;
            }

            /**
             * Gets the value of the historyLabel property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getHistoryLabel() {
                return historyLabel;
            }

            /**
             * Sets the value of the historyLabel property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setHistoryLabel(String value) {
                this.historyLabel = value;
            }

            /**
             * Gets the value of the description property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDescription() {
                return description;
            }

            /**
             * Sets the value of the description property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDescription(String value) {
                this.description = value;
            }

            /**
             * Gets the value of the defaultValue property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDefaultValue() {
                return defaultValue;
            }

            /**
             * Sets the value of the defaultValue property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDefaultValue(String value) {
                this.defaultValue = value;
            }

            /**
             * Gets the value of the editable property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isEditable() {
                return editable;
            }

            /**
             * Sets the value of the editable property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setEditable(Boolean value) {
                this.editable = value;
            }

            /**
             * Gets the value of the hidden property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isHidden() {
                return hidden;
            }

            /**
             * Sets the value of the hidden property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setHidden(Boolean value) {
                this.hidden = value;
            }

            /**
             * Gets the value of the includeInStateVector property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isIncludeInStateVector() {
                return includeInStateVector;
            }

            /**
             * Sets the value of the includeInStateVector property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setIncludeInStateVector(Boolean value) {
                this.includeInStateVector = value;
            }

            /**
             * Gets the value of the stateVectorName property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getStateVectorName() {
                return stateVectorName;
            }

            /**
             * Sets the value of the stateVectorName property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setStateVectorName(String value) {
                this.stateVectorName = value;
            }

            /**
             * Gets the value of the extractFromStateVector property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getExtractFromStateVector() {
                return extractFromStateVector;
            }

            /**
             * Sets the value of the extractFromStateVector property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setExtractFromStateVector(String value) {
                this.extractFromStateVector = value;
            }

            /**
             * Gets the value of the includeInMetaData property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isIncludeInMetaData() {
                return includeInMetaData;
            }

            /**
             * Sets the value of the includeInMetaData property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setIncludeInMetaData(Boolean value) {
                this.includeInMetaData = value;
            }

            /**
             * Gets the value of the metaDataName property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getMetaDataName() {
                return metaDataName;
            }

            /**
             * Sets the value of the metaDataName property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setMetaDataName(String value) {
                this.metaDataName = value;
            }

            /**
             * Gets the value of the extractFromMetaData property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getExtractFromMetaData() {
                return extractFromMetaData;
            }

            /**
             * Sets the value of the extractFromMetaData property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setExtractFromMetaData(String value) {
                this.extractFromMetaData = value;
            }

            /**
             * Gets the value of the includeInStickyData property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isIncludeInStickyData() {
                return includeInStickyData;
            }

            /**
             * Sets the value of the includeInStickyData property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setIncludeInStickyData(Boolean value) {
                this.includeInStickyData = value;
            }

            /**
             * Gets the value of the stickyDataName property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getStickyDataName() {
                return stickyDataName;
            }

            /**
             * Sets the value of the stickyDataName property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setStickyDataName(String value) {
                this.stickyDataName = value;
            }

            /**
             * Gets the value of the displayFormatType property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDisplayFormatType() {
                return displayFormatType;
            }

            /**
             * Sets the value of the displayFormatType property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDisplayFormatType(String value) {
                this.displayFormatType = value;
            }

            /**
             * Gets the value of the displayFormat property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDisplayFormat() {
                return displayFormat;
            }

            /**
             * Sets the value of the displayFormat property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDisplayFormat(String value) {
                this.displayFormat = value;
            }

            /**
             * Gets the value of the mandatory property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isMandatory() {
                return mandatory;
            }

            /**
             * Sets the value of the mandatory property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setMandatory(Boolean value) {
                this.mandatory = value;
            }

            /**
             * Gets the value of the setAsCustomer property.
             * 
             * @return
             *     possible object is
             *     {@link ActionType.InputParameters.InputParameter.SetAsCustomer }
             *     
             */
            public ActionType.InputParameters.InputParameter.SetAsCustomer getSetAsCustomer() {
                return setAsCustomer;
            }

            /**
             * Sets the value of the setAsCustomer property.
             * 
             * @param value
             *     allowed object is
             *     {@link ActionType.InputParameters.InputParameter.SetAsCustomer }
             *     
             */
            public void setSetAsCustomer(ActionType.InputParameters.InputParameter.SetAsCustomer value) {
                this.setAsCustomer = value;
            }

            /**
             * Gets the value of the setAsServiceType property.
             * 
             * @return
             *     possible object is
             *     {@link ActionType.InputParameters.InputParameter.SetAsServiceType }
             *     
             */
            public ActionType.InputParameters.InputParameter.SetAsServiceType getSetAsServiceType() {
                return setAsServiceType;
            }

            /**
             * Sets the value of the setAsServiceType property.
             * 
             * @param value
             *     allowed object is
             *     {@link ActionType.InputParameters.InputParameter.SetAsServiceType }
             *     
             */
            public void setSetAsServiceType(ActionType.InputParameters.InputParameter.SetAsServiceType value) {
                this.setAsServiceType = value;
            }

            /**
             * Gets the value of the setAsProblem property.
             * 
             * @return
             *     possible object is
             *     {@link ActionType.InputParameters.InputParameter.SetAsProblem }
             *     
             */
            public ActionType.InputParameters.InputParameter.SetAsProblem getSetAsProblem() {
                return setAsProblem;
            }

            /**
             * Sets the value of the setAsProblem property.
             * 
             * @param value
             *     allowed object is
             *     {@link ActionType.InputParameters.InputParameter.SetAsProblem }
             *     
             */
            public void setSetAsProblem(ActionType.InputParameters.InputParameter.SetAsProblem value) {
                this.setAsProblem = value;
            }

            /**
             * Gets the value of the setAsResourceType property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isSetAsResourceType() {
                return setAsResourceType;
            }

            /**
             * Sets the value of the setAsResourceType property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setSetAsResourceType(Boolean value) {
                this.setAsResourceType = value;
            }

            /**
             * Gets the value of the setAsResourceInstance property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isSetAsResourceInstance() {
                return setAsResourceInstance;
            }

            /**
             * Sets the value of the setAsResourceInstance property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setSetAsResourceInstance(Boolean value) {
                this.setAsResourceInstance = value;
            }

            /**
             * Gets the value of the helpTexts property.
             * 
             * @return
             *     possible object is
             *     {@link ActionType.InputParameters.InputParameter.HelpTexts }
             *     
             */
            public ActionType.InputParameters.InputParameter.HelpTexts getHelpTexts() {
                return helpTexts;
            }

            /**
             * Sets the value of the helpTexts property.
             * 
             * @param value
             *     allowed object is
             *     {@link ActionType.InputParameters.InputParameter.HelpTexts }
             *     
             */
            public void setHelpTexts(ActionType.InputParameters.InputParameter.HelpTexts value) {
                this.helpTexts = value;
            }

            /**
             * Gets the value of the icon property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getIcon() {
                return icon;
            }

            /**
             * Sets the value of the icon property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setIcon(String value) {
                this.icon = value;
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
             *         &lt;element name="helpText" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
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

                @XmlElement(namespace = "http://www.xml.td.hp.com/Model", required = true)
                protected List<String> helpText;

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
                 * {@link String }
                 * 
                 * 
                 */
                public List<String> getHelpText() {
                    if (helpText == null) {
                        helpText = new ArrayList<String>();
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
             *   &lt;simpleContent>
             *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>boolean">
             *       &lt;attribute name="field" type="{http://www.w3.org/2001/XMLSchema}string" />
             *     &lt;/extension>
             *   &lt;/simpleContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "value"
            })
            public static class SetAsCustomer {

                @XmlValue
                protected boolean value;
                @XmlAttribute(name = "field")
                protected String field;

                /**
                 * Gets the value of the value property.
                 * 
                 */
                public boolean isValue() {
                    return value;
                }

                /**
                 * Sets the value of the value property.
                 * 
                 */
                public void setValue(boolean value) {
                    this.value = value;
                }

                /**
                 * Gets the value of the field property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getField() {
                    return field;
                }

                /**
                 * Sets the value of the field property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setField(String value) {
                    this.field = value;
                }

            }


            /**
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;simpleContent>
             *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>boolean">
             *       &lt;attribute name="field" type="{http://www.w3.org/2001/XMLSchema}string" />
             *     &lt;/extension>
             *   &lt;/simpleContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "value"
            })
            public static class SetAsProblem {

                @XmlValue
                protected boolean value;
                @XmlAttribute(name = "field")
                protected String field;

                /**
                 * Gets the value of the value property.
                 * 
                 */
                public boolean isValue() {
                    return value;
                }

                /**
                 * Sets the value of the value property.
                 * 
                 */
                public void setValue(boolean value) {
                    this.value = value;
                }

                /**
                 * Gets the value of the field property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getField() {
                    return field;
                }

                /**
                 * Sets the value of the field property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setField(String value) {
                    this.field = value;
                }

            }


            /**
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;simpleContent>
             *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>boolean">
             *       &lt;attribute name="field" type="{http://www.w3.org/2001/XMLSchema}string" />
             *     &lt;/extension>
             *   &lt;/simpleContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "value"
            })
            public static class SetAsServiceType {

                @XmlValue
                protected boolean value;
                @XmlAttribute(name = "field")
                protected String field;

                /**
                 * Gets the value of the value property.
                 * 
                 */
                public boolean isValue() {
                    return value;
                }

                /**
                 * Sets the value of the value property.
                 * 
                 */
                public void setValue(boolean value) {
                    this.value = value;
                }

                /**
                 * Gets the value of the field property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getField() {
                    return field;
                }

                /**
                 * Sets the value of the field property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setField(String value) {
                    this.field = value;
                }

            }

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
     *         &lt;element name="outputParameter" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="type">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;enumeration value="String"/>
     *                         &lt;enumeration value="Integer"/>
     *                         &lt;enumeration value="Float"/>
     *                         &lt;enumeration value="Boolean"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="label" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="historyLabel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="defaultValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="editable" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="hidden" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="includeInStateVector" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="stateVectorName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="includeInMetaData" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="metaDataName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="includeInStickyData" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="stickyDataName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="displayFormatType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="displayFormat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="mandatory" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="setAsCustomer" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;simpleContent>
     *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>boolean">
     *                           &lt;attribute name="field" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                         &lt;/extension>
     *                       &lt;/simpleContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="setAsServiceType" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;simpleContent>
     *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>boolean">
     *                           &lt;attribute name="field" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                         &lt;/extension>
     *                       &lt;/simpleContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="setAsProblem" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;simpleContent>
     *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>boolean">
     *                           &lt;attribute name="field" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                         &lt;/extension>
     *                       &lt;/simpleContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="helpTexts" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="helpText" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="icon" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
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
    @XmlType(name = "", propOrder = {
        "outputParameter"
    })
    public static class OutputParameters {

        @XmlElement(namespace = "http://www.xml.td.hp.com/Model", required = true)
        protected List<ActionType.OutputParameters.OutputParameter> outputParameter;

        /**
         * Gets the value of the outputParameter property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the outputParameter property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getOutputParameter().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ActionType.OutputParameters.OutputParameter }
         * 
         * 
         */
        public List<ActionType.OutputParameters.OutputParameter> getOutputParameter() {
            if (outputParameter == null) {
                outputParameter = new ArrayList<ActionType.OutputParameters.OutputParameter>();
            }
            return this.outputParameter;
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
         *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="type">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;enumeration value="String"/>
         *               &lt;enumeration value="Integer"/>
         *               &lt;enumeration value="Float"/>
         *               &lt;enumeration value="Boolean"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="label" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="historyLabel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="defaultValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="editable" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="hidden" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="includeInStateVector" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="stateVectorName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="includeInMetaData" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="metaDataName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="includeInStickyData" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="stickyDataName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="displayFormatType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="displayFormat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="mandatory" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="setAsCustomer" minOccurs="0">
         *           &lt;complexType>
         *             &lt;simpleContent>
         *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>boolean">
         *                 &lt;attribute name="field" type="{http://www.w3.org/2001/XMLSchema}string" />
         *               &lt;/extension>
         *             &lt;/simpleContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="setAsServiceType" minOccurs="0">
         *           &lt;complexType>
         *             &lt;simpleContent>
         *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>boolean">
         *                 &lt;attribute name="field" type="{http://www.w3.org/2001/XMLSchema}string" />
         *               &lt;/extension>
         *             &lt;/simpleContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="setAsProblem" minOccurs="0">
         *           &lt;complexType>
         *             &lt;simpleContent>
         *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>boolean">
         *                 &lt;attribute name="field" type="{http://www.w3.org/2001/XMLSchema}string" />
         *               &lt;/extension>
         *             &lt;/simpleContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="helpTexts" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="helpText" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="icon" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
            "name",
            "type",
            "label",
            "historyLabel",
            "description",
            "defaultValue",
            "editable",
            "hidden",
            "includeInStateVector",
            "stateVectorName",
            "includeInMetaData",
            "metaDataName",
            "includeInStickyData",
            "stickyDataName",
            "displayFormatType",
            "displayFormat",
            "mandatory",
            "setAsCustomer",
            "setAsServiceType",
            "setAsProblem",
            "helpTexts",
            "icon"
        })
        public static class OutputParameter {

            @XmlElement(namespace = "http://www.xml.td.hp.com/Model", required = true)
            protected String name;
            @XmlElement(namespace = "http://www.xml.td.hp.com/Model", required = true)
            protected String type;
            @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
            protected String label;
            @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
            protected String historyLabel;
            @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
            protected String description;
            @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
            protected String defaultValue;
            @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
            protected Boolean editable;
            @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
            protected Boolean hidden;
            @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
            protected Boolean includeInStateVector;
            @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
            protected String stateVectorName;
            @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
            protected Boolean includeInMetaData;
            @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
            protected String metaDataName;
            @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
            protected Boolean includeInStickyData;
            @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
            protected String stickyDataName;
            @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
            protected String displayFormatType;
            @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
            protected String displayFormat;
            @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
            protected Boolean mandatory;
            @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
            protected ActionType.OutputParameters.OutputParameter.SetAsCustomer setAsCustomer;
            @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
            protected ActionType.OutputParameters.OutputParameter.SetAsServiceType setAsServiceType;
            @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
            protected ActionType.OutputParameters.OutputParameter.SetAsProblem setAsProblem;
            @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
            protected ActionType.OutputParameters.OutputParameter.HelpTexts helpTexts;
            @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
            protected String icon;

            /**
             * Gets the value of the name property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getName() {
                return name;
            }

            /**
             * Sets the value of the name property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setName(String value) {
                this.name = value;
            }

            /**
             * Gets the value of the type property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getType() {
                return type;
            }

            /**
             * Sets the value of the type property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setType(String value) {
                this.type = value;
            }

            /**
             * Gets the value of the label property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getLabel() {
                return label;
            }

            /**
             * Sets the value of the label property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setLabel(String value) {
                this.label = value;
            }

            /**
             * Gets the value of the historyLabel property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getHistoryLabel() {
                return historyLabel;
            }

            /**
             * Sets the value of the historyLabel property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setHistoryLabel(String value) {
                this.historyLabel = value;
            }

            /**
             * Gets the value of the description property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDescription() {
                return description;
            }

            /**
             * Sets the value of the description property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDescription(String value) {
                this.description = value;
            }

            /**
             * Gets the value of the defaultValue property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDefaultValue() {
                return defaultValue;
            }

            /**
             * Sets the value of the defaultValue property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDefaultValue(String value) {
                this.defaultValue = value;
            }

            /**
             * Gets the value of the editable property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isEditable() {
                return editable;
            }

            /**
             * Sets the value of the editable property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setEditable(Boolean value) {
                this.editable = value;
            }

            /**
             * Gets the value of the hidden property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isHidden() {
                return hidden;
            }

            /**
             * Sets the value of the hidden property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setHidden(Boolean value) {
                this.hidden = value;
            }

            /**
             * Gets the value of the includeInStateVector property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isIncludeInStateVector() {
                return includeInStateVector;
            }

            /**
             * Sets the value of the includeInStateVector property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setIncludeInStateVector(Boolean value) {
                this.includeInStateVector = value;
            }

            /**
             * Gets the value of the stateVectorName property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getStateVectorName() {
                return stateVectorName;
            }

            /**
             * Sets the value of the stateVectorName property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setStateVectorName(String value) {
                this.stateVectorName = value;
            }

            /**
             * Gets the value of the includeInMetaData property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isIncludeInMetaData() {
                return includeInMetaData;
            }

            /**
             * Sets the value of the includeInMetaData property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setIncludeInMetaData(Boolean value) {
                this.includeInMetaData = value;
            }

            /**
             * Gets the value of the metaDataName property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getMetaDataName() {
                return metaDataName;
            }

            /**
             * Sets the value of the metaDataName property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setMetaDataName(String value) {
                this.metaDataName = value;
            }

            /**
             * Gets the value of the includeInStickyData property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isIncludeInStickyData() {
                return includeInStickyData;
            }

            /**
             * Sets the value of the includeInStickyData property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setIncludeInStickyData(Boolean value) {
                this.includeInStickyData = value;
            }

            /**
             * Gets the value of the stickyDataName property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getStickyDataName() {
                return stickyDataName;
            }

            /**
             * Sets the value of the stickyDataName property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setStickyDataName(String value) {
                this.stickyDataName = value;
            }

            /**
             * Gets the value of the displayFormatType property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDisplayFormatType() {
                return displayFormatType;
            }

            /**
             * Sets the value of the displayFormatType property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDisplayFormatType(String value) {
                this.displayFormatType = value;
            }

            /**
             * Gets the value of the displayFormat property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDisplayFormat() {
                return displayFormat;
            }

            /**
             * Sets the value of the displayFormat property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDisplayFormat(String value) {
                this.displayFormat = value;
            }

            /**
             * Gets the value of the mandatory property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isMandatory() {
                return mandatory;
            }

            /**
             * Sets the value of the mandatory property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setMandatory(Boolean value) {
                this.mandatory = value;
            }

            /**
             * Gets the value of the setAsCustomer property.
             * 
             * @return
             *     possible object is
             *     {@link ActionType.OutputParameters.OutputParameter.SetAsCustomer }
             *     
             */
            public ActionType.OutputParameters.OutputParameter.SetAsCustomer getSetAsCustomer() {
                return setAsCustomer;
            }

            /**
             * Sets the value of the setAsCustomer property.
             * 
             * @param value
             *     allowed object is
             *     {@link ActionType.OutputParameters.OutputParameter.SetAsCustomer }
             *     
             */
            public void setSetAsCustomer(ActionType.OutputParameters.OutputParameter.SetAsCustomer value) {
                this.setAsCustomer = value;
            }

            /**
             * Gets the value of the setAsServiceType property.
             * 
             * @return
             *     possible object is
             *     {@link ActionType.OutputParameters.OutputParameter.SetAsServiceType }
             *     
             */
            public ActionType.OutputParameters.OutputParameter.SetAsServiceType getSetAsServiceType() {
                return setAsServiceType;
            }

            /**
             * Sets the value of the setAsServiceType property.
             * 
             * @param value
             *     allowed object is
             *     {@link ActionType.OutputParameters.OutputParameter.SetAsServiceType }
             *     
             */
            public void setSetAsServiceType(ActionType.OutputParameters.OutputParameter.SetAsServiceType value) {
                this.setAsServiceType = value;
            }

            /**
             * Gets the value of the setAsProblem property.
             * 
             * @return
             *     possible object is
             *     {@link ActionType.OutputParameters.OutputParameter.SetAsProblem }
             *     
             */
            public ActionType.OutputParameters.OutputParameter.SetAsProblem getSetAsProblem() {
                return setAsProblem;
            }

            /**
             * Sets the value of the setAsProblem property.
             * 
             * @param value
             *     allowed object is
             *     {@link ActionType.OutputParameters.OutputParameter.SetAsProblem }
             *     
             */
            public void setSetAsProblem(ActionType.OutputParameters.OutputParameter.SetAsProblem value) {
                this.setAsProblem = value;
            }

            /**
             * Gets the value of the helpTexts property.
             * 
             * @return
             *     possible object is
             *     {@link ActionType.OutputParameters.OutputParameter.HelpTexts }
             *     
             */
            public ActionType.OutputParameters.OutputParameter.HelpTexts getHelpTexts() {
                return helpTexts;
            }

            /**
             * Sets the value of the helpTexts property.
             * 
             * @param value
             *     allowed object is
             *     {@link ActionType.OutputParameters.OutputParameter.HelpTexts }
             *     
             */
            public void setHelpTexts(ActionType.OutputParameters.OutputParameter.HelpTexts value) {
                this.helpTexts = value;
            }

            /**
             * Gets the value of the icon property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getIcon() {
                return icon;
            }

            /**
             * Sets the value of the icon property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setIcon(String value) {
                this.icon = value;
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
             *         &lt;element name="helpText" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
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

                @XmlElement(namespace = "http://www.xml.td.hp.com/Model", required = true)
                protected List<String> helpText;

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
                 * {@link String }
                 * 
                 * 
                 */
                public List<String> getHelpText() {
                    if (helpText == null) {
                        helpText = new ArrayList<String>();
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
             *   &lt;simpleContent>
             *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>boolean">
             *       &lt;attribute name="field" type="{http://www.w3.org/2001/XMLSchema}string" />
             *     &lt;/extension>
             *   &lt;/simpleContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "value"
            })
            public static class SetAsCustomer {

                @XmlValue
                protected boolean value;
                @XmlAttribute(name = "field")
                protected String field;

                /**
                 * Gets the value of the value property.
                 * 
                 */
                public boolean isValue() {
                    return value;
                }

                /**
                 * Sets the value of the value property.
                 * 
                 */
                public void setValue(boolean value) {
                    this.value = value;
                }

                /**
                 * Gets the value of the field property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getField() {
                    return field;
                }

                /**
                 * Sets the value of the field property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setField(String value) {
                    this.field = value;
                }

            }


            /**
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;simpleContent>
             *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>boolean">
             *       &lt;attribute name="field" type="{http://www.w3.org/2001/XMLSchema}string" />
             *     &lt;/extension>
             *   &lt;/simpleContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "value"
            })
            public static class SetAsProblem {

                @XmlValue
                protected boolean value;
                @XmlAttribute(name = "field")
                protected String field;

                /**
                 * Gets the value of the value property.
                 * 
                 */
                public boolean isValue() {
                    return value;
                }

                /**
                 * Sets the value of the value property.
                 * 
                 */
                public void setValue(boolean value) {
                    this.value = value;
                }

                /**
                 * Gets the value of the field property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getField() {
                    return field;
                }

                /**
                 * Sets the value of the field property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setField(String value) {
                    this.field = value;
                }

            }


            /**
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;simpleContent>
             *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>boolean">
             *       &lt;attribute name="field" type="{http://www.w3.org/2001/XMLSchema}string" />
             *     &lt;/extension>
             *   &lt;/simpleContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "value"
            })
            public static class SetAsServiceType {

                @XmlValue
                protected boolean value;
                @XmlAttribute(name = "field")
                protected String field;

                /**
                 * Gets the value of the value property.
                 * 
                 */
                public boolean isValue() {
                    return value;
                }

                /**
                 * Sets the value of the value property.
                 * 
                 */
                public void setValue(boolean value) {
                    this.value = value;
                }

                /**
                 * Gets the value of the field property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getField() {
                    return field;
                }

                /**
                 * Sets the value of the field property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setField(String value) {
                    this.field = value;
                }

            }

        }

    }

}
