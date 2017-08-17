package com.turki.androidapis.storage.sqlite.DataBaseGenericCreation;

/**
 * Helper utility for creating table through SQLite
 *
 * @author Turki Mahmoud
 */
public class TableCreationBuilder {

    public static final int COLUMN_TYPE_INTEGER = 0x01;
    public static final int COLUMN_TYPE_TEXT = 0x02;

    private static final String COLUMN_TYPE_ID = " INTEGER PRIMARY KEY";
    private static final String TYPE_INTEGER = " INTEGER";
    private static final String TYPE_TEXT = " TEXT";

    private StringBuilder sqlStmt;
    private boolean columnAppended;

    public TableCreationBuilder(String tableName) {
        sqlStmt = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
        sqlStmt.append(tableName);
    }

    protected void appendPrefix() {
        sqlStmt.append(columnAppended ? ", " : " (");
        if (!columnAppended)
            columnAppended = true;
    }

    /**
     * Set the id column for your table.
     *
     * @param columnName
     * @return The same instance of the builder to enable chaining
     */
    protected TableCreationBuilder withId(String columnName) {
        appendPrefix();
        sqlStmt.append(columnName).append(COLUMN_TYPE_ID);
        return this;
    }

    /**
     * Add column to your table with specified name and one of two predefined types
     *
     * @param columnName
     * @param columnType COLUMN_TYPE_INTEGER , or COLUMN_TYPE_TEXT
     * @return The same instance of the builder to enable chaining
     */
    protected TableCreationBuilder withColumn(String columnName, int columnType) {
        appendPrefix();
        sqlStmt.append(columnName).append(getType(columnType));
        return this;
    }

    /**
     * @return The SQL statement that can be used to create the described table.
     */
    protected String getSqlStmt() {
        sqlStmt.append("); ");
        return sqlStmt.toString();
    }

    private String getType(int type) {
        String typeString = null;
        switch (type) {
            case COLUMN_TYPE_INTEGER:
                typeString = TYPE_INTEGER;
                break;
            case COLUMN_TYPE_TEXT:
                typeString = TYPE_TEXT;
                break;
            default:
                throw new IllegalArgumentException("[TableCreationBuilder] Column type must be specified");
        }
        return typeString;
    }
}
