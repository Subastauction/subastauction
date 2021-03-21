/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.subastauction.model;

import java.util.Date;

/**
 *
 * @author StivenVanegas
 */
public class Evento {
    private String name, description;
    private Date startDate, endDate;
    private int initialOffer;

    public Evento(String name, String description, Date startDate, Date endDate, int initialOffer) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.initialOffer = initialOffer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getInitialOffer() {
        return initialOffer;
    }

    public void setInitialOffer(int initialOffer) {
        this.initialOffer = initialOffer;
    }
    
}
