
package de.edgesoft.refereemanager.jaxb;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import de.edgesoft.edgeutils.javafx.SimpleBooleanPropertyAdapter;
import de.edgesoft.edgeutils.javafx.SimpleIntegerPropertyAdapter;
import de.edgesoft.edgeutils.javafx.SimpleObjectPropertyLocalDateAdapter;
import de.edgesoft.refereemanager.model.RefereeModel;


/**
 * <p>Java class for Trainee complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Trainee">
 *   &lt;complexContent>
 *     &lt;extension base="{}Referee">
 *       &lt;sequence>
 *         &lt;element name="withdrawn" type="{}BooleanProperty" minOccurs="0"/>
 *         &lt;element name="did_not_start" type="{}BooleanProperty" minOccurs="0"/>
 *         &lt;element name="exam_date" type="{}LocalDateProperty" minOccurs="0"/>
 *         &lt;element name="points_written_a" type="{}IntegerProperty" minOccurs="0"/>
 *         &lt;element name="points_written_b" type="{}IntegerProperty" minOccurs="0"/>
 *         &lt;element name="points_practical" type="{}IntegerProperty" minOccurs="0"/>
 *         &lt;element name="points_oral" type="{}IntegerProperty" minOccurs="0"/>
 *         &lt;element name="passed" type="{}BooleanProperty" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Trainee", propOrder = {
    "withdrawn",
    "didNotStart",
    "examDate",
    "pointsWrittenA",
    "pointsWrittenB",
    "pointsPractical",
    "pointsOral",
    "passed"
})
public class Trainee
    extends RefereeModel
{

    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(SimpleBooleanPropertyAdapter.class)
    @XmlSchemaType(name = "boolean")
    protected SimpleBooleanProperty withdrawn;
    @XmlElement(name = "did_not_start", type = String.class)
    @XmlJavaTypeAdapter(SimpleBooleanPropertyAdapter.class)
    @XmlSchemaType(name = "boolean")
    protected SimpleBooleanProperty didNotStart;
    @XmlElement(name = "exam_date", type = String.class)
    @XmlJavaTypeAdapter(SimpleObjectPropertyLocalDateAdapter.class)
    @XmlSchemaType(name = "date")
    protected SimpleObjectProperty examDate;
    @XmlElement(name = "points_written_a", type = String.class)
    @XmlJavaTypeAdapter(SimpleIntegerPropertyAdapter.class)
    @XmlSchemaType(name = "int")
    protected SimpleIntegerProperty pointsWrittenA;
    @XmlElement(name = "points_written_b", type = String.class)
    @XmlJavaTypeAdapter(SimpleIntegerPropertyAdapter.class)
    @XmlSchemaType(name = "int")
    protected SimpleIntegerProperty pointsWrittenB;
    @XmlElement(name = "points_practical", type = String.class)
    @XmlJavaTypeAdapter(SimpleIntegerPropertyAdapter.class)
    @XmlSchemaType(name = "int")
    protected SimpleIntegerProperty pointsPractical;
    @XmlElement(name = "points_oral", type = String.class)
    @XmlJavaTypeAdapter(SimpleIntegerPropertyAdapter.class)
    @XmlSchemaType(name = "int")
    protected SimpleIntegerProperty pointsOral;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(SimpleBooleanPropertyAdapter.class)
    @XmlSchemaType(name = "boolean")
    protected SimpleBooleanProperty passed;

    /**
     * Gets the value of the withdrawn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public SimpleBooleanProperty getWithdrawn() {
        return withdrawn;
    }

    /**
     * Sets the value of the withdrawn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWithdrawn(SimpleBooleanProperty value) {
        this.withdrawn = value;
    }

    /**
     * Gets the value of the didNotStart property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public SimpleBooleanProperty getDidNotStart() {
        return didNotStart;
    }

    /**
     * Sets the value of the didNotStart property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDidNotStart(SimpleBooleanProperty value) {
        this.didNotStart = value;
    }

    /**
     * Gets the value of the examDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public SimpleObjectProperty getExamDate() {
        return examDate;
    }

    /**
     * Sets the value of the examDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExamDate(SimpleObjectProperty value) {
        this.examDate = value;
    }

    /**
     * Gets the value of the pointsWrittenA property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public SimpleIntegerProperty getPointsWrittenA() {
        return pointsWrittenA;
    }

    /**
     * Sets the value of the pointsWrittenA property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPointsWrittenA(SimpleIntegerProperty value) {
        this.pointsWrittenA = value;
    }

    /**
     * Gets the value of the pointsWrittenB property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public SimpleIntegerProperty getPointsWrittenB() {
        return pointsWrittenB;
    }

    /**
     * Sets the value of the pointsWrittenB property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPointsWrittenB(SimpleIntegerProperty value) {
        this.pointsWrittenB = value;
    }

    /**
     * Gets the value of the pointsPractical property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public SimpleIntegerProperty getPointsPractical() {
        return pointsPractical;
    }

    /**
     * Sets the value of the pointsPractical property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPointsPractical(SimpleIntegerProperty value) {
        this.pointsPractical = value;
    }

    /**
     * Gets the value of the pointsOral property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public SimpleIntegerProperty getPointsOral() {
        return pointsOral;
    }

    /**
     * Sets the value of the pointsOral property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPointsOral(SimpleIntegerProperty value) {
        this.pointsOral = value;
    }

    /**
     * Gets the value of the passed property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public SimpleBooleanProperty getPassed() {
        return passed;
    }

    /**
     * Sets the value of the passed property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassed(SimpleBooleanProperty value) {
        this.passed = value;
    }

}
