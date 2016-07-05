package com.dk.dento.care.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by khana on 22/06/16.
 */
@Controller
public class LayoutController {

    @RequestMapping("patients/layout")
    public String getPatientsPartialPage() {
        return "patients/layout";
    }

    @RequestMapping("patient/layout")
    public String getPatientPartialPage() {
        return "patient/layout";
    }

    @RequestMapping("appointment/layout")
    public String getAppointmentPartialPage() {
        return "appointment/layout";
    }

    @RequestMapping("treatment/layout")
    public String getTreatmentPartialPage() {
        return "treatment/layout";
    }

    @RequestMapping("treatment-images/layout")
    public String getTreatmentImagesPartialPage() {
        return "treatment-images/layout";
    }

    @RequestMapping("login/layout")
    public String getLoginPartialPage() {
        return "login/layout";
    }
}
