/**
 * This class is generated by jOOQ
 */
package de.edgesoft.refereemanager.jooq.tables;


import de.edgesoft.refereemanager.jooq.Keys;
import de.edgesoft.refereemanager.jooq.Refereemanager;
import de.edgesoft.refereemanager.jooq.tables.records.SeasonsRecord;

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
 * All seasons.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.8.0"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Seasons extends TableImpl<SeasonsRecord> {

    private static final long serialVersionUID = 1400056064;

    /**
     * The reference instance of <code>refereemanager.rfrmgr_seasons</code>
     */
    public static final Seasons SEASONS = new Seasons();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<SeasonsRecord> getRecordType() {
        return SeasonsRecord.class;
    }

    /**
     * The column <code>refereemanager.rfrmgr_seasons.id</code>.
     */
    public final TableField<SeasonsRecord, UInteger> ID = createField("id", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>refereemanager.rfrmgr_seasons.year_start</code>.
     */
    public final TableField<SeasonsRecord, Integer> YEAR_START = createField("year_start", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>refereemanager.rfrmgr_seasons.title</code>.
     */
    public final TableField<SeasonsRecord, String> TITLE = createField("title", org.jooq.impl.SQLDataType.VARCHAR.length(100), this, "");

    /**
     * The column <code>refereemanager.rfrmgr_seasons.editor_only</code>.
     */
    public final TableField<SeasonsRecord, Byte> EDITOR_ONLY = createField("editor_only", org.jooq.impl.SQLDataType.TINYINT, this, "");

    /**
     * The column <code>refereemanager.rfrmgr_seasons.remark</code>.
     */
    public final TableField<SeasonsRecord, String> REMARK = createField("remark", org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * Create a <code>refereemanager.rfrmgr_seasons</code> table reference
     */
    public Seasons() {
        this("rfrmgr_seasons", null);
    }

    /**
     * Create an aliased <code>refereemanager.rfrmgr_seasons</code> table reference
     */
    public Seasons(String alias) {
        this(alias, SEASONS);
    }

    private Seasons(String alias, Table<SeasonsRecord> aliased) {
        this(alias, aliased, null);
    }

    private Seasons(String alias, Table<SeasonsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "All seasons.");
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
    public Identity<SeasonsRecord, UInteger> getIdentity() {
        return Keys.IDENTITY_SEASONS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<SeasonsRecord> getPrimaryKey() {
        return Keys.KEY_RFRMGR_SEASONS_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<SeasonsRecord>> getKeys() {
        return Arrays.<UniqueKey<SeasonsRecord>>asList(Keys.KEY_RFRMGR_SEASONS_PRIMARY, Keys.KEY_RFRMGR_SEASONS_ID_UNIQUE, Keys.KEY_RFRMGR_SEASONS_YEAR_START_UNIQUE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Seasons as(String alias) {
        return new Seasons(alias, this);
    }

    /**
     * Rename this table
     */
    public Seasons rename(String name) {
        return new Seasons(name, null);
    }
}
