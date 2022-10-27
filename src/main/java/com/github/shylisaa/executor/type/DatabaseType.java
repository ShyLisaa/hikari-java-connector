package com.github.shylisaa.executor.type;

import java.util.Arrays;

/**
 * @author kleinLisaa
 * @created 27/10/2022
 * <p>
 * Do not edit without permission.
 **/
public enum DatabaseType {

    VARCHAR("VARCHAR(64)"),
    INT("INT"),
    JSON("JSON"),
    UNIQUE_ID("UUID"),
    LONGTEXT("LONGTEXT"),
    TEXT("TEXT"),
    FLOAT("FLOAT");

    private final String databaseText;

    DatabaseType(String databaseText) {
        this.databaseText = databaseText;
    }

    public String getDatabaseText() {
        return databaseText;
    }

    public static DatabaseType getByText(String input){
        return Arrays.stream(values()).filter(databaseType -> databaseType.databaseText.equals(input)).findAny().orElse(null);
    }
}
