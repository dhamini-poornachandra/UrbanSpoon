package com.project.msrit.urbanspoon;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by plank-dhamini on 21/01/18.
 */

public class GlobalVariable {
    private static GlobalVariable instance;
    ArrayList<Tables> tablesList = new ArrayList<>();
    private List<Tables> tables;
    String phoneNumber;
    String message;

    private GlobalVariable() {
    }

    public static synchronized GlobalVariable getInstance() {
        if (instance == null) {
            instance = new GlobalVariable();
        }
        return instance;
    }

    public List<Tables> getTables() {
        return tables;
    }

    public void setTables(List<Tables> tables) {
        this.tables = tables;
    }
}

