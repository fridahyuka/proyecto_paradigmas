package mx.uaemex.fi.service;

import mx.uaemex.fi.model.RecordsDAO;


public class RecordsServiceLocal implements  RecordsService{
    private RecordsDAO dao;
    public RecordsServiceLocal() {
    }

    public void setDao(RecordsDAO dao) {
        this.dao = dao;
    }
}
