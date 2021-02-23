
package com.hpe.dataload.generator.model.dataloadmodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.xml.bind.annotation.*;


/**
 * <p>Java class for GroupType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GroupType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="states">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="indexes">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="index" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="cause" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="states">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;choice>
 *                               &lt;element name="actions">
 *                                 &lt;complexType>
 *                                   &lt;complexContent>
 *                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                       &lt;sequence>
 *                                         &lt;element name="recommended" minOccurs="0">
 *                                           &lt;complexType>
 *                                             &lt;complexContent>
 *                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                                 &lt;sequence>
 *                                                   &lt;element name="action" type="{http://www.xml.td.hp.com/Model}ActionRefType" maxOccurs="unbounded" minOccurs="0"/>
 *                                                 &lt;/sequence>
 *                                               &lt;/restriction>
 *                                             &lt;/complexContent>
 *                                           &lt;/complexType>
 *                                         &lt;/element>
 *                                         &lt;element name="excluded" minOccurs="0">
 *                                           &lt;complexType>
 *                                             &lt;complexContent>
 *                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                                 &lt;sequence>
 *                                                   &lt;element name="action" type="{http://www.xml.td.hp.com/Model}ActionRefType" maxOccurs="unbounded" minOccurs="0"/>
 *                                                 &lt;/sequence>
 *                                               &lt;/restriction>
 *                                             &lt;/complexContent>
 *                                           &lt;/complexType>
 *                                         &lt;/element>
 *                                         &lt;element name="possible" minOccurs="0">
 *                                           &lt;complexType>
 *                                             &lt;complexContent>
 *                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                                 &lt;sequence>
 *                                                   &lt;element name="action" type="{http://www.xml.td.hp.com/Model}ActionRefType" maxOccurs="unbounded" minOccurs="0"/>
 *                                                 &lt;/sequence>
 *                                               &lt;/restriction>
 *                                             &lt;/complexContent>
 *                                           &lt;/complexType>
 *                                         &lt;/element>
 *                                       &lt;/sequence>
 *                                       &lt;attribute name="proceed" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *                                     &lt;/restriction>
 *                                   &lt;/complexContent>
 *                                 &lt;/complexType>
 *                               &lt;/element>
 *                               &lt;element name="nextGroup" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                               &lt;element name="endState">
 *                                 &lt;complexType>
 *                                   &lt;complexContent>
 *                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;/restriction>
 *                                   &lt;/complexContent>
 *                                 &lt;/complexType>
 *                               &lt;/element>
 *                             &lt;/choice>
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
 *       &lt;attribute name="order" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GroupType", namespace = "http://www.xml.td.hp.com/Model", propOrder = {
    "name",
    "states",
    "indexes"
})
@XmlRootElement
public class GroupType {

    @XmlElement(namespace = "http://www.xml.td.hp.com/Model", required = true)
    protected String name;
    @XmlElement(namespace = "http://www.xml.td.hp.com/Model", required = true)
    protected GroupType.States states;
    @XmlElement(namespace = "http://www.xml.td.hp.com/Model", required = true)
    protected GroupType.Indexes indexes;
    @XmlAttribute(name = "order", required = true)
    protected int order;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupType groupType = (GroupType) o;
        return order == groupType.order &&
                Objects.equals(name, groupType.name) &&
                Objects.equals(states, groupType.states) &&
                Objects.equals(indexes, groupType.indexes);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, states, indexes, order);
    }

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
     * Gets the value of the states property.
     * 
     * @return
     *     possible object is
     *     {@link GroupType.States }
     *     
     */
    public GroupType.States getStates() {
        return states;
    }

    /**
     * Sets the value of the states property.
     * 
     * @param value
     *     allowed object is
     *     {@link GroupType.States }
     *     
     */
    public void setStates(GroupType.States value) {
        this.states = value;
    }

    /**
     * Gets the value of the indexes property.
     * 
     * @return
     *     possible object is
     *     {@link GroupType.Indexes }
     *     
     */
    public GroupType.Indexes getIndexes() {
        return indexes;
    }

    /**
     * Sets the value of the indexes property.
     * 
     * @param value
     *     allowed object is
     *     {@link GroupType.Indexes }
     *     
     */
    public void setIndexes(GroupType.Indexes value) {
        this.indexes = value;
    }

    /**
     * Gets the value of the order property.
     * 
     */
    public int getOrder() {
        return order;
    }

    /**
     * Sets the value of the order property.
     * 
     */
    public void setOrder(int value) {
        this.order = value;
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
     *         &lt;element name="index" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="cause" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="states">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;choice>
     *                     &lt;element name="actions">
     *                       &lt;complexType>
     *                         &lt;complexContent>
     *                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                             &lt;sequence>
     *                               &lt;element name="recommended" minOccurs="0">
     *                                 &lt;complexType>
     *                                   &lt;complexContent>
     *                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                       &lt;sequence>
     *                                         &lt;element name="action" type="{http://www.xml.td.hp.com/Model}ActionRefType" maxOccurs="unbounded" minOccurs="0"/>
     *                                       &lt;/sequence>
     *                                     &lt;/restriction>
     *                                   &lt;/complexContent>
     *                                 &lt;/complexType>
     *                               &lt;/element>
     *                               &lt;element name="excluded" minOccurs="0">
     *                                 &lt;complexType>
     *                                   &lt;complexContent>
     *                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                       &lt;sequence>
     *                                         &lt;element name="action" type="{http://www.xml.td.hp.com/Model}ActionRefType" maxOccurs="unbounded" minOccurs="0"/>
     *                                       &lt;/sequence>
     *                                     &lt;/restriction>
     *                                   &lt;/complexContent>
     *                                 &lt;/complexType>
     *                               &lt;/element>
     *                               &lt;element name="possible" minOccurs="0">
     *                                 &lt;complexType>
     *                                   &lt;complexContent>
     *                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                       &lt;sequence>
     *                                         &lt;element name="action" type="{http://www.xml.td.hp.com/Model}ActionRefType" maxOccurs="unbounded" minOccurs="0"/>
     *                                       &lt;/sequence>
     *                                     &lt;/restriction>
     *                                   &lt;/complexContent>
     *                                 &lt;/complexType>
     *                               &lt;/element>
     *                             &lt;/sequence>
     *                             &lt;attribute name="proceed" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
     *                           &lt;/restriction>
     *                         &lt;/complexContent>
     *                       &lt;/complexType>
     *                     &lt;/element>
     *                     &lt;element name="nextGroup" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                     &lt;element name="endState">
     *                       &lt;complexType>
     *                         &lt;complexContent>
     *                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;/restriction>
     *                         &lt;/complexContent>
     *                       &lt;/complexType>
     *                     &lt;/element>
     *                   &lt;/choice>
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
        "index"
    })
    public static class Indexes {
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Indexes)) return false;
            Indexes indexes = (Indexes) o;
            return Objects.equals(index, indexes.index);
        }

        @Override
        public int hashCode() {

            return Objects.hash(index);
        }

        @XmlElement(namespace = "http://www.xml.td.hp.com/Model", required = true)
        protected List<GroupType.Indexes.Index> index;


        /**
         * Gets the value of the index property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the index property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getIndex().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link GroupType.Indexes.Index }
         * 
         * 
         */
        public List<GroupType.Indexes.Index> getIndex() {
            if (index == null) {
                index = new ArrayList<GroupType.Indexes.Index>();
            }
            return this.index;
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
         *         &lt;element name="cause" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="states">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;choice>
         *           &lt;element name="actions">
         *             &lt;complexType>
         *               &lt;complexContent>
         *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                   &lt;sequence>
         *                     &lt;element name="recommended" minOccurs="0">
         *                       &lt;complexType>
         *                         &lt;complexContent>
         *                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                             &lt;sequence>
         *                               &lt;element name="action" type="{http://www.xml.td.hp.com/Model}ActionRefType" maxOccurs="unbounded" minOccurs="0"/>
         *                             &lt;/sequence>
         *                           &lt;/restriction>
         *                         &lt;/complexContent>
         *                       &lt;/complexType>
         *                     &lt;/element>
         *                     &lt;element name="excluded" minOccurs="0">
         *                       &lt;complexType>
         *                         &lt;complexContent>
         *                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                             &lt;sequence>
         *                               &lt;element name="action" type="{http://www.xml.td.hp.com/Model}ActionRefType" maxOccurs="unbounded" minOccurs="0"/>
         *                             &lt;/sequence>
         *                           &lt;/restriction>
         *                         &lt;/complexContent>
         *                       &lt;/complexType>
         *                     &lt;/element>
         *                     &lt;element name="possible" minOccurs="0">
         *                       &lt;complexType>
         *                         &lt;complexContent>
         *                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                             &lt;sequence>
         *                               &lt;element name="action" type="{http://www.xml.td.hp.com/Model}ActionRefType" maxOccurs="unbounded" minOccurs="0"/>
         *                             &lt;/sequence>
         *                           &lt;/restriction>
         *                         &lt;/complexContent>
         *                       &lt;/complexType>
         *                     &lt;/element>
         *                   &lt;/sequence>
         *                   &lt;attribute name="proceed" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
         *                 &lt;/restriction>
         *               &lt;/complexContent>
         *             &lt;/complexType>
         *           &lt;/element>
         *           &lt;element name="nextGroup" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *           &lt;element name="endState">
         *             &lt;complexType>
         *               &lt;complexContent>
         *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;/restriction>
         *               &lt;/complexContent>
         *             &lt;/complexType>
         *           &lt;/element>
         *         &lt;/choice>
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
            "cause",
            "states",
            "actions",
            "nextGroup",
            "endState"
        })
        public static class Index {


            @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
            protected String cause;
            @XmlElement(namespace = "http://www.xml.td.hp.com/Model", required = true)
            protected GroupType.Indexes.Index.States states;
            @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
            protected GroupType.Indexes.Index.Actions actions;
            @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
            protected String nextGroup;
            @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
            protected GroupType.Indexes.Index.EndState endState;

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof Index)) return false;
                Index index = (Index) o;
                return Objects.equals(cause, index.cause) &&
                        Objects.equals(states, index.states) &&
                        Objects.equals(actions, index.actions) &&
                        Objects.equals(nextGroup, index.nextGroup) &&
                        Objects.equals(endState, index.endState);
            }

            @Override
            public int hashCode() {
                return Objects.hash(cause, states, actions, nextGroup, endState);
            }

            /**
             * Gets the value of the cause property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCause() {
                return cause;
            }

            /**
             * Sets the value of the cause property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCause(String value) {
                this.cause = value;
            }

            /**
             * Gets the value of the states property.
             * 
             * @return
             *     possible object is
             *     {@link GroupType.Indexes.Index.States }
             *     
             */
            public GroupType.Indexes.Index.States getStates() {
                return states;
            }

            /**
             * Sets the value of the states property.
             * 
             * @param value
             *     allowed object is
             *     {@link GroupType.Indexes.Index.States }
             *     
             */
            public void setStates(GroupType.Indexes.Index.States value) {
                this.states = value;
            }

            /**
             * Gets the value of the actions property.
             * 
             * @return
             *     possible object is
             *     {@link GroupType.Indexes.Index.Actions }
             *     
             */
            public GroupType.Indexes.Index.Actions getActions() {
                return actions;
            }

            /**
             * Sets the value of the actions property.
             * 
             * @param value
             *     allowed object is
             *     {@link GroupType.Indexes.Index.Actions }
             *     
             */
            public void setActions(GroupType.Indexes.Index.Actions value) {
                this.actions = value;
            }

            /**
             * Gets the value of the nextGroup property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getNextGroup() {
                return nextGroup;
            }

            /**
             * Sets the value of the nextGroup property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setNextGroup(String value) {
                this.nextGroup = value;
            }

            /**
             * Gets the value of the endState property.
             * 
             * @return
             *     possible object is
             *     {@link GroupType.Indexes.Index.EndState }
             *     
             */
            public GroupType.Indexes.Index.EndState getEndState() {
                return endState;
            }

            /**
             * Sets the value of the endState property.
             * 
             * @param value
             *     allowed object is
             *     {@link GroupType.Indexes.Index.EndState }
             *     
             */
            public void setEndState(GroupType.Indexes.Index.EndState value) {
                this.endState = value;
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
             *         &lt;element name="recommended" minOccurs="0">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="action" type="{http://www.xml.td.hp.com/Model}ActionRefType" maxOccurs="unbounded" minOccurs="0"/>
             *                 &lt;/sequence>
             *               &lt;/restriction>
             *             &lt;/complexContent>
             *           &lt;/complexType>
             *         &lt;/element>
             *         &lt;element name="excluded" minOccurs="0">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="action" type="{http://www.xml.td.hp.com/Model}ActionRefType" maxOccurs="unbounded" minOccurs="0"/>
             *                 &lt;/sequence>
             *               &lt;/restriction>
             *             &lt;/complexContent>
             *           &lt;/complexType>
             *         &lt;/element>
             *         &lt;element name="possible" minOccurs="0">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="action" type="{http://www.xml.td.hp.com/Model}ActionRefType" maxOccurs="unbounded" minOccurs="0"/>
             *                 &lt;/sequence>
             *               &lt;/restriction>
             *             &lt;/complexContent>
             *           &lt;/complexType>
             *         &lt;/element>
             *       &lt;/sequence>
             *       &lt;attribute name="proceed" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "recommended",
                "excluded",
                "possible"
            })
            public static class Actions {

                @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
                protected GroupType.Indexes.Index.Actions.Recommended recommended;
                @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
                protected GroupType.Indexes.Index.Actions.Excluded excluded;
                @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
                protected GroupType.Indexes.Index.Actions.Possible possible;
                @XmlAttribute(name = "proceed")
                protected Boolean proceed;

                @Override
                public boolean equals(Object o) {
                    if (this == o) return true;
                    if (o == null || getClass() != o.getClass()) return false;
                    Actions actions = (Actions) o;
                    return Objects.equals(recommended, actions.recommended) &&
                            Objects.equals(excluded, actions.excluded) &&
                            Objects.equals(possible, actions.possible) &&
                            Objects.equals(proceed, actions.proceed);
                }

                @Override
                public int hashCode() {
                    return Objects.hash(recommended, excluded, possible, proceed);
                }

                /**
                 * Gets the value of the recommended property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link GroupType.Indexes.Index.Actions.Recommended }
                 *     
                 */
                public GroupType.Indexes.Index.Actions.Recommended getRecommended() {
                    return recommended;
                }

                /**
                 * Sets the value of the recommended property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link GroupType.Indexes.Index.Actions.Recommended }
                 *     
                 */
                public void setRecommended(GroupType.Indexes.Index.Actions.Recommended value) {
                    this.recommended = value;
                }

                /**
                 * Gets the value of the excluded property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link GroupType.Indexes.Index.Actions.Excluded }
                 *     
                 */
                public GroupType.Indexes.Index.Actions.Excluded getExcluded() {
                    return excluded;
                }

                /**
                 * Sets the value of the excluded property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link GroupType.Indexes.Index.Actions.Excluded }
                 *     
                 */
                public void setExcluded(GroupType.Indexes.Index.Actions.Excluded value) {
                    this.excluded = value;
                }

                /**
                 * Gets the value of the possible property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link GroupType.Indexes.Index.Actions.Possible }
                 *     
                 */
                public GroupType.Indexes.Index.Actions.Possible getPossible() {
                    return possible;
                }

                /**
                 * Sets the value of the possible property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link GroupType.Indexes.Index.Actions.Possible }
                 *     
                 */
                public void setPossible(GroupType.Indexes.Index.Actions.Possible value) {
                    this.possible = value;
                }

                /**
                 * Gets the value of the proceed property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Boolean }
                 *     
                 */
                public boolean isProceed() {
                    if (proceed == null) {
                        return false;
                    } else {
                        return proceed;
                    }
                }

                /**
                 * Sets the value of the proceed property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Boolean }
                 *     
                 */
                public void setProceed(Boolean value) {
                    this.proceed = value;
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
                 *         &lt;element name="action" type="{http://www.xml.td.hp.com/Model}ActionRefType" maxOccurs="unbounded" minOccurs="0"/>
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
                public static class Excluded {

                    @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
                    protected List<ActionRefType> action;

                    @Override
                    public boolean equals(Object o) {
                        if (this == o) return true;
                        if (o == null || getClass() != o.getClass()) return false;
                        Excluded excluded = (Excluded) o;
                        return Objects.equals(action, excluded.action);
                    }

                    @Override
                    public int hashCode() {

                        return Objects.hash(action);
                    }

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
                     * {@link ActionRefType }
                     * 
                     * 
                     */
                    public List<ActionRefType> getAction() {
                        if (action == null) {
                            action = new ArrayList<ActionRefType>();
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
                 *         &lt;element name="action" type="{http://www.xml.td.hp.com/Model}ActionRefType" maxOccurs="unbounded" minOccurs="0"/>
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
                public static class Possible {

                    @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
                    protected List<ActionRefType> action;

                    @Override
                    public boolean equals(Object o) {
                        if (this == o) return true;
                        if (o == null || getClass() != o.getClass()) return false;
                        Possible possible = (Possible) o;
                        return Objects.equals(action, possible.action);
                    }

                    @Override
                    public int hashCode() {

                        return Objects.hash(action);
                    }

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
                     * {@link ActionRefType }
                     * 
                     * 
                     */
                    public List<ActionRefType> getAction() {
                        if (action == null) {
                            action = new ArrayList<ActionRefType>();
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
                 *         &lt;element name="action" type="{http://www.xml.td.hp.com/Model}ActionRefType" maxOccurs="unbounded" minOccurs="0"/>
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
                public static class Recommended {

                    @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
                    protected List<ActionRefType> action;

                    @Override
                    public boolean equals(Object o) {
                        if (this == o) return true;
                        if (o == null || getClass() != o.getClass()) return false;
                        Recommended that = (Recommended) o;
                        return Objects.equals(action, that.action);
                    }

                    @Override
                    public int hashCode() {
                        return Objects.hash(action);
                    }


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
                     * {@link ActionRefType }
                     * 
                     * 
                     */
                    public List<ActionRefType> getAction() {
                        if (action == null) {
                            action = new ArrayList<ActionRefType>();
                        }
                        return this.action;
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
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class EndState {
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
             *         &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
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
                "value"
            })
            public static class States {

                @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
                protected List<String> value;

                @Override
                public boolean equals(Object o) {
                    if (this == o) return true;
                    if (o == null || getClass() != o.getClass()) return false;
                    States states = (States) o;
                    return Objects.equals(value, states.value);
                }

                @Override
                public int hashCode() {
                    return Objects.hash(value);
                }

                /**
                 * Gets the value of the value property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the value property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getValue().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link String }
                 * 
                 * 
                 */
                public List<String> getValue() {
                    if (value == null) {
                        value = new ArrayList<String>();
                    }
                    return this.value;
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
     *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
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
        "name"
    })
    public static class States {

        @XmlElement(namespace = "http://www.xml.td.hp.com/Model")
        protected List<String> name;

        /**
         * Gets the value of the name property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the name property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getName().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getName() {
            if (name == null) {
                name = new ArrayList<String>();
            }
            return this.name;
        }
    }

}
