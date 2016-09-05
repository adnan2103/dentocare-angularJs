package com.dk.dento.care.model;

import java.util.List;

/**
 * Created by khana on 22/02/16.
 */
public class Patient extends User {

    private String imagePath;

    private List<Treatment> treatments;

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public List<Treatment> getTreatments() {
        return treatments;
    }

    public void setTreatments(List<Treatment> treatments) {
        this.treatments = treatments;
    }

}
