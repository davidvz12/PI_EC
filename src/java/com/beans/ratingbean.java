/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import javax.annotation.ManagedBean;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

import com.conexion.Conexion;
import java.io.IOException;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.RateEvent;

/**
 *
 * @author Suanny
 */
@Named(value = "rb")
@RequestScoped
public class ratingbean {
    private Integer rating1;  
    private Integer rating2;  
    private Integer rating3;  
    private Integer rating4 = 3;

    /**
     * Creates a new instance of ratingbean
     */
    public ratingbean() {
    }
     public void onrate(RateEvent<Integer> rateEvent) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Rate Event", "You rated:" + rateEvent.getRating());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    public void oncancel() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cancel Event", "Rate Reset");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }    public Integer getRating1() {
        return rating1;
    }
    public void setRating1(Integer rating1) {
        this.rating1 = rating1;
    }
    public Integer getRating2() {
        return rating2;
    }
    public void setRating2(Integer rating2) {
        this.rating2 = rating2;
    }
    public Integer getRating3() {
        return rating3;
    }
    public void setRating3(Integer rating3) {
        this.rating3 = rating3;
    }
    public Integer getRating4() {
        return rating4;
    }

    public void setRating4(Integer rating4) {
        this.rating4 = rating4;
    }
}
