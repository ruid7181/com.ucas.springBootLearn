package com.ucas.entity;

public class TMQuery {

    private String con_fields;
    private String con_tLis;
    private String con_tRange;
    private String fuzzy;
    private String con_path;
    private String con_row;
    private String con_order;

    public String getFuzzy() {
        return fuzzy;
    }

    public void setFuzzy(String fuzzy) {
        this.fuzzy = fuzzy;
    }

    public void setCon_fields(String con_fields) {
        this.con_fields = con_fields;
    }

    public void setCon_tLis(String con_tLis) {
        this.con_tLis = con_tLis;
    }

    public void setCon_tRange(String con_tRange) {
        this.con_tRange = con_tRange;
    }

    public void setCon_path(String con_path) {
        this.con_path = con_path;
    }

    public void setCon_row(String con_row) {
        this.con_row = con_row;
    }

    public void setCon_order(String con_order) {
        this.con_order = con_order;
    }

    public String getCon_tLis() {
        return con_tLis;
    }

    public String getCon_tRange() {
        return con_tRange;
    }

    public String getCon_path() {
        return con_path;
    }

    public String getCon_row() {
        return con_row;
    }

    public String getCon_order() {
        return con_order;
    }

    public String getCon_fields() {
        return con_fields;
    }
}
