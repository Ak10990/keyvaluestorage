package com.android.keyvaluestorage.helper;

import java.util.ArrayList;
import java.util.List;

public class QueryBuilder {

    private boolean isWhere = false;
    private List<String> queryBuild = new ArrayList<>();

    public void select(String tableName) {
        queryBuild.add(0, "SELECT * FROM " + tableName);
    }

    public void selectCount(String tableName) {
        queryBuild.add(0, "SELECT count(*) as count FROM " + tableName);
    }

    public void where(String lhs, String rhs, boolean isEqual) {
        String result;
        if (isWhere) {
            result = " AND ";
        } else {
            result = " WHERE ";
            isWhere = true;
        }
        String equalString = isEqual ? "=" : "LIKE";
        queryBuild.add(result + lhs + " " + equalString + " '" + rhs + "'");
    }

    /*public void selectColumn(String tableName, String columnName) {
        queryBuild.add(0, "SELECT " + columnName + " FROM " + tableName);
    }

    public void selectDistinctColumn(String tableName, String columnName) {
        queryBuild.add(0, "SELECT DISTINCT" + columnName + " FROM " + tableName);
    }

    public void selectSum(String tableName, String columnName) {
        queryBuild.add(0, "SELECT SUM(" + columnName + ") as sum FROM " + tableName);
    }

    public void whereOr(String lhs, String rhs, boolean isEqual) {
        String result;
        if (isWhere) {
            result = " OR ";
        } else {
            result = " WHERE ";
            isWhere = true;
        }
        String equalString = isEqual ? "=" : "LIKE";
        queryBuild.add(result + lhs + " " + equalString + " '" + rhs + "'");
    }

    public void whereBetween(String columnname, String val1, String val2) {
        String result;
        if (isWhere) {
            result = " OR ";
        } else {
            result = " WHERE ";
            isWhere = true;
        }
        queryBuild.add(result + columnname + " BETWEEN " + val1 + " AND " + val2);
    }

    public void whereIn(boolean isIn, String lhs, String... rhs) {
        String result;
        if (isWhere) {
            result = " OR ";
        } else {
            result = " WHERE ";
            isWhere = true;
        }
        if (isIn) {
            queryBuild.add(result + lhs + " IN " + Arrays.toString(rhs));
        } else {
            queryBuild.add(result + lhs + " NOT IN " + Arrays.toString(rhs));
        }
    }

    public void order(String orderBy, boolean isAsc) {
        String result = " ORDER BY " + orderBy;
        if (isAsc) {
            result += " ASC ";
        } else
            result += " DESC ";
        queryBuild.add(result);
    }

    public void groupBy(String columnName, String havingCondition) {
        String result = " GROUP BY " + columnName;
        if (havingCondition != null) {
            result += havingCondition;
        }
        queryBuild.add(result);
    }

    public void unique() {
        queryBuild.add(" LIMIT 1");//After where
    }

    public void limit(long lowerLimit, long upperLimit) {
        queryBuild.add(" LIMIT " + lowerLimit + "," + upperLimit);
    }*/

    public String list() {
        String queryResult = "";
        for (String query : queryBuild) {
            queryResult += query;
        }
        return queryResult;
    }
}
