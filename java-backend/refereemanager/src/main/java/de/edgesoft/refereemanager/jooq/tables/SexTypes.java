/**
 * This class is generated by jOOQ
 */
package de.edgesoft.refereemanager.jooq.tables;


import de.edgesoft.refereemanager.jooq.Keys;
import de.edgesoft.refereemanager.jooq.Refereemanager;
import de.edgesoft.refereemanager.jooq.tables.records.SexTypesRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Identity;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;
import org.jooq.types.UInteger;


/**
 * Lookup table for sexes.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.8.0"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SexTypes extends TableImpl<SexTypesRecord> {

    private static final long serialVersionUID = 1770550720;

    /**
     * The reference instance of <code>refereemanager.rfrmgr_sex_types</code>
     */
    public static final SexTypes SEX_TYPES = new SexTypes();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<SexTypesRecord> getRecordType() {
        return SexTypesRecord.class;
    }

    /**
     * The column <code>refereemanager.rfrmgr_sex_types.id</code>.
     */
    public final TableField<SexTypesRecord, UInteger> ID = createField("id", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>refereemanager.rfrmgr_sex_types.sid</code>.
     */
    public final TableField<SexTypesRecord, String> SID = createField("sid", org.jooq.impl.SQLDataType.VARCHAR.length(20).nullable(false), this, "");

    /**
     * The column <code>refereemanager.rfrmgr_sex_types.title</code>.
     */
    public final TableField<SexTypesRecord, String> TITLE = createField("title", org.jooq.impl.SQLDataType.VARCHAR.length(100).nullable(false), this, "");

    /**
     * The column <code>refereemanager.rfrmgr_sex_types.remark</code>.
     */
    public final TableField<SexTypesRecord, String> REMARK = createField("remark", org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * Create a <code>refereemanager.rfrmgr_sex_types</code> table reference
     */
    public SexTypes() {
        this("rfrmgr_sex_types", null);
    }

    /**
     * Create an aliased <code>refereemanager.rfrmgr_sex_types</code> table reference
     */
    public SexTypes(String alias) {
        this(alias, SEX_TYPES);
    }

    private SexTypes(String alias, Table<SexTypesRecord> aliased) {
        this(alias, aliased, null);
    }

    private SexTypes(String alias, Table<SexTypesRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "Lookup table for sexes.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Refereemanager.REFEREEMANAGER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<SexTypesRecord, UInteger> getIdentity() {
        return Keys.IDENTITY_SEX_TYPES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<SexTypesRecord> getPrimaryKey() {
        return Keys.KEY_RFRMGR_SEX_TYPES_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<SexTypesRecord>> getKeys() {
        return Arrays.<UniqueKey<SexTypesRecord>>asList(Keys.KEY_RFRMGR_SEX_TYPES_PRIMARY, Keys.KEY_RFRMGR_SEX_TYPES_ID_UNIQUE, Keys.KEY_RFRMGR_SEX_TYPES_SID_UNIQUE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SexTypes as(String alias) {
        return new SexTypes(alias, this);
    }

    /**
     * Rename this table
     */
    public SexTypes rename(String name) {
        return new SexTypes(name, null);
    }
}
