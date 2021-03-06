
package de.edgesoft.refereemanager.jaxb;

import javafx.beans.property.SimpleBooleanProperty;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import de.edgesoft.edgeutils.commons.AbstractModelClass;
import de.edgesoft.edgeutils.javafx.SimpleBooleanPropertyAdapter;


/**
 * <p>Java class for Wish complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Wish">
 *   &lt;complexContent>
 *     &lt;extension base="{}AbstractModelClass">
 *       &lt;sequence>
 *         &lt;element name="saturday" type="{}BooleanProperty" minOccurs="0"/>
 *         &lt;element name="sunday" type="{}BooleanProperty" minOccurs="0"/>
 *         &lt;element name="tournament_only" type="{}BooleanProperty" minOccurs="0"/>
 *         &lt;element name="league_games_only" type="{}BooleanProperty" minOccurs="0"/>
 *         &lt;element name="club" type="{http://www.w3.org/2001/XMLSchema}IDREF" minOccurs="0"/>
 *         &lt;element name="league" type="{http://www.w3.org/2001/XMLSchema}IDREF" minOccurs="0"/>
 *         &lt;element name="sex_type" type="{http://www.w3.org/2001/XMLSchema}IDREF" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Wish", propOrder = {
    "saturday",
    "sunday",
    "tournamentOnly",
    "leagueGamesOnly",
    "club",
    "league",
    "sexType"
})
public class Wish
    extends AbstractModelClass
{

    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(SimpleBooleanPropertyAdapter.class)
    @XmlSchemaType(name = "boolean")
    protected SimpleBooleanProperty saturday;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(SimpleBooleanPropertyAdapter.class)
    @XmlSchemaType(name = "boolean")
    protected SimpleBooleanProperty sunday;
    @XmlElement(name = "tournament_only", type = String.class)
    @XmlJavaTypeAdapter(SimpleBooleanPropertyAdapter.class)
    @XmlSchemaType(name = "boolean")
    protected SimpleBooleanProperty tournamentOnly;
    @XmlElement(name = "league_games_only", type = String.class)
    @XmlJavaTypeAdapter(SimpleBooleanPropertyAdapter.class)
    @XmlSchemaType(name = "boolean")
    protected SimpleBooleanProperty leagueGamesOnly;
    @XmlElement(type = Object.class)
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Club club;
    @XmlElement(type = Object.class)
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected League league;
    @XmlElement(name = "sex_type", type = Object.class)
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected SexType sexType;

    /**
     * Gets the value of the saturday property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public SimpleBooleanProperty getSaturday() {
        return saturday;
    }

    /**
     * Sets the value of the saturday property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSaturday(SimpleBooleanProperty value) {
        this.saturday = value;
    }

    /**
     * Gets the value of the sunday property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public SimpleBooleanProperty getSunday() {
        return sunday;
    }

    /**
     * Sets the value of the sunday property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSunday(SimpleBooleanProperty value) {
        this.sunday = value;
    }

    /**
     * Gets the value of the tournamentOnly property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public SimpleBooleanProperty getTournamentOnly() {
        return tournamentOnly;
    }

    /**
     * Sets the value of the tournamentOnly property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTournamentOnly(SimpleBooleanProperty value) {
        this.tournamentOnly = value;
    }

    /**
     * Gets the value of the leagueGamesOnly property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public SimpleBooleanProperty getLeagueGamesOnly() {
        return leagueGamesOnly;
    }

    /**
     * Sets the value of the leagueGamesOnly property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLeagueGamesOnly(SimpleBooleanProperty value) {
        this.leagueGamesOnly = value;
    }

    /**
     * Gets the value of the club property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Club getClub() {
        return club;
    }

    /**
     * Sets the value of the club property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setClub(Club value) {
        this.club = value;
    }

    /**
     * Gets the value of the league property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public League getLeague() {
        return league;
    }

    /**
     * Sets the value of the league property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setLeague(League value) {
        this.league = value;
    }

    /**
     * Gets the value of the sexType property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public SexType getSexType() {
        return sexType;
    }

    /**
     * Sets the value of the sexType property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setSexType(SexType value) {
        this.sexType = value;
    }

}
