/**
 * This class is generated by jOOQ
 */
package de.edgesoft.refereemanager.jooq.tables.records;


import de.edgesoft.refereemanager.jooq.tables.TrainingLevelTypes;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record7;
import org.jooq.Row7;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.types.UInteger;


/**
 * Possible training levels such as international referee, Germ / * comment 
 * truncated * / / *an referee.* /
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.8.0"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TrainingLevelTypesRecord extends UpdatableRecordImpl<TrainingLevelTypesRecord> implements Record7<UInteger, String, UInteger, String, String, UInteger, String> {

    private static final long serialVersionUID = 180655620;

    /**
     * Setter for <code>refereemanager.rfrmgr_training_level_types.id</code>.
     */
    public void setId(UInteger value) {
        set(0, value);
    }

    /**
     * Getter for <code>refereemanager.rfrmgr_training_level_types.id</code>.
     */
    public UInteger getId() {
        return (UInteger) get(0);
    }

    /**
     * Setter for <code>refereemanager.rfrmgr_training_level_types.sid</code>.
     */
    public void setSid(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>refereemanager.rfrmgr_training_level_types.sid</code>.
     */
    public String getSid() {
        return (String) get(1);
    }

    /**
     * Setter for <code>refereemanager.rfrmgr_training_level_types.rank</code>.
     */
    public void setRank(UInteger value) {
        set(2, value);
    }

    /**
     * Getter for <code>refereemanager.rfrmgr_training_level_types.rank</code>.
     */
    public UInteger getRank() {
        return (UInteger) get(2);
    }

    /**
     * Setter for <code>refereemanager.rfrmgr_training_level_types.title</code>.
     */
    public void setTitle(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>refereemanager.rfrmgr_training_level_types.title</code>.
     */
    public String getTitle() {
        return (String) get(3);
    }

    /**
     * Setter for <code>refereemanager.rfrmgr_training_level_types.abbreviation</code>.
     */
    public void setAbbreviation(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>refereemanager.rfrmgr_training_level_types.abbreviation</code>.
     */
    public String getAbbreviation() {
        return (String) get(4);
    }

    /**
     * Setter for <code>refereemanager.rfrmgr_training_level_types.update_interval</code>.
     */
    public void setUpdateInterval(UInteger value) {
        set(5, value);
    }

    /**
     * Getter for <code>refereemanager.rfrmgr_training_level_types.update_interval</code>.
     */
    public UInteger getUpdateInterval() {
        return (UInteger) get(5);
    }

    /**
     * Setter for <code>refereemanager.rfrmgr_training_level_types.remark</code>.
     */
    public void setRemark(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>refereemanager.rfrmgr_training_level_types.remark</code>.
     */
    public String getRemark() {
        return (String) get(6);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<UInteger> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record7 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row7<UInteger, String, UInteger, String, String, UInteger, String> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row7<UInteger, String, UInteger, String, String, UInteger, String> valuesRow() {
        return (Row7) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field1() {
        return TrainingLevelTypes.TRAINING_LEVEL_TYPES.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return TrainingLevelTypes.TRAINING_LEVEL_TYPES.SID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field3() {
        return TrainingLevelTypes.TRAINING_LEVEL_TYPES.RANK;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return TrainingLevelTypes.TRAINING_LEVEL_TYPES.TITLE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
        return TrainingLevelTypes.TRAINING_LEVEL_TYPES.ABBREVIATION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field6() {
        return TrainingLevelTypes.TRAINING_LEVEL_TYPES.UPDATE_INTERVAL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field7() {
        return TrainingLevelTypes.TRAINING_LEVEL_TYPES.REMARK;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getSid();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger value3() {
        return getRank();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getTitle();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value5() {
        return getAbbreviation();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger value6() {
        return getUpdateInterval();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value7() {
        return getRemark();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TrainingLevelTypesRecord value1(UInteger value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TrainingLevelTypesRecord value2(String value) {
        setSid(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TrainingLevelTypesRecord value3(UInteger value) {
        setRank(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TrainingLevelTypesRecord value4(String value) {
        setTitle(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TrainingLevelTypesRecord value5(String value) {
        setAbbreviation(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TrainingLevelTypesRecord value6(UInteger value) {
        setUpdateInterval(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TrainingLevelTypesRecord value7(String value) {
        setRemark(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TrainingLevelTypesRecord values(UInteger value1, String value2, UInteger value3, String value4, String value5, UInteger value6, String value7) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached TrainingLevelTypesRecord
     */
    public TrainingLevelTypesRecord() {
        super(TrainingLevelTypes.TRAINING_LEVEL_TYPES);
    }

    /**
     * Create a detached, initialised TrainingLevelTypesRecord
     */
    public TrainingLevelTypesRecord(UInteger id, String sid, UInteger rank, String title, String abbreviation, UInteger updateInterval, String remark) {
        super(TrainingLevelTypes.TRAINING_LEVEL_TYPES);

        set(0, id);
        set(1, sid);
        set(2, rank);
        set(3, title);
        set(4, abbreviation);
        set(5, updateInterval);
        set(6, remark);
    }
}
