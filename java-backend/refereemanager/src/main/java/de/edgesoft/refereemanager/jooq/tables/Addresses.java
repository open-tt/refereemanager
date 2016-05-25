/**
 * This class is generated by jOOQ
 */
package de.edgesoft.refereemanager.jooq.tables;


import de.edgesoft.refereemanager.jooq.Keys;
import de.edgesoft.refereemanager.jooq.Refereemanager;
import de.edgesoft.refereemanager.jooq.tables.records.AddressesRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;
import org.jooq.types.UInteger;


/**
 * All addresses.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.8.0"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Addresses extends TableImpl<AddressesRecord> {

    private static final long serialVersionUID = 1699488596;

    /**
     * The reference instance of <code>refereemanager.rfrmgr_addresses</code>
     */
    public static final Addresses ADDRESSES = new Addresses();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<AddressesRecord> getRecordType() {
        return AddressesRecord.class;
    }

    /**
     * The column <code>refereemanager.rfrmgr_addresses.id</code>.
     */
    public final TableField<AddressesRecord, UInteger> ID = createField("id", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>refereemanager.rfrmgr_addresses.contact_id</code>.
     */
    public final TableField<AddressesRecord, UInteger> CONTACT_ID = createField("contact_id", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>refereemanager.rfrmgr_addresses.street</code>.
     */
    public final TableField<AddressesRecord, String> STREET = createField("street", org.jooq.impl.SQLDataType.VARCHAR.length(100).nullable(false), this, "");

    /**
     * The column <code>refereemanager.rfrmgr_addresses.number</code>.
     */
    public final TableField<AddressesRecord, String> NUMBER = createField("number", org.jooq.impl.SQLDataType.VARCHAR.length(100), this, "");

    /**
     * The column <code>refereemanager.rfrmgr_addresses.zip_code</code>.
     */
    public final TableField<AddressesRecord, String> ZIP_CODE = createField("zip_code", org.jooq.impl.SQLDataType.VARCHAR.length(100), this, "");

    /**
     * The column <code>refereemanager.rfrmgr_addresses.city</code>.
     */
    public final TableField<AddressesRecord, String> CITY = createField("city", org.jooq.impl.SQLDataType.VARCHAR.length(100).nullable(false), this, "");

    /**
     * Create a <code>refereemanager.rfrmgr_addresses</code> table reference
     */
    public Addresses() {
        this("rfrmgr_addresses", null);
    }

    /**
     * Create an aliased <code>refereemanager.rfrmgr_addresses</code> table reference
     */
    public Addresses(String alias) {
        this(alias, ADDRESSES);
    }

    private Addresses(String alias, Table<AddressesRecord> aliased) {
        this(alias, aliased, null);
    }

    private Addresses(String alias, Table<AddressesRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "All addresses.");
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
    public Identity<AddressesRecord, UInteger> getIdentity() {
        return Keys.IDENTITY_ADDRESSES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<AddressesRecord> getPrimaryKey() {
        return Keys.KEY_RFRMGR_ADDRESSES_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<AddressesRecord>> getKeys() {
        return Arrays.<UniqueKey<AddressesRecord>>asList(Keys.KEY_RFRMGR_ADDRESSES_PRIMARY, Keys.KEY_RFRMGR_ADDRESSES_ID_UNIQUE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<AddressesRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<AddressesRecord, ?>>asList(Keys.FK_RFRMGR_ADDRESSES_RFRMGR_CONTACTS1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Addresses as(String alias) {
        return new Addresses(alias, this);
    }

    /**
     * Rename this table
     */
    public Addresses rename(String name) {
        return new Addresses(name, null);
    }
}
