package com.dk.dento.care.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Created by khana on 22/06/16.
 */
@ApiIgnore
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

    @RequestMapping("appointments/layout")
    public String getAppointmentsPartialPage() {
        return "appointments/layout";
    }

    @RequestMapping("treatment/layout")
    public String getTreatmentPartialPage() {
        return "treatment/layout";
    }

    @RequestMapping("treatment-images/layout")
    public String getTreatmentImagesPartialPage() {
        return "treatment-images/layout";
    }

    @RequestMapping("administration/layout")
    public String getAdministrationPartialPage() {
        return "administration/layout";
    }

    @RequestMapping("treatments/layout")
    public String getAllTreatmentsPartialPage() {
        return "treatments/layout";
    }

    @RequestMapping("login/layout")
    public String getLoginPartialPage() {
        return "login/layout";
    }

    @RequestMapping(value = "/documentation")
    public String documentation() {
        return "redirect:swagger-ui.html";
    }
}
