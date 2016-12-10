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

	private static final String BASE = "view/";
	
    @RequestMapping("patients/layout")
    public String getPatientsPartialPage() {
        return BASE + "patients/layout";
    }

    @RequestMapping("patient/layout")
    public String getPatientPartialPage() {
        return BASE + "patient/layout";
    }

    @RequestMapping("appointments/layout")
    public String getAppointmentsPartialPage() {
        return BASE + "appointments/layout";
    }

    @RequestMapping("treatment/layout")
    public String getTreatmentPartialPage() {
        return BASE + "treatment/layout";
    }

    @RequestMapping("treatment-images/layout")
    public String getTreatmentImagesPartialPage() {
        return BASE + "treatment-images/layout";
    }

    @RequestMapping("administration/layout")
    public String getAdministrationPartialPage() {
        return BASE + "administration/layout";
    }

    @RequestMapping("treatments/layout")
    public String getAllTreatmentsPartialPage() {
        return BASE + "treatments/layout";
    }

    @RequestMapping("login/layout")
    public String getLoginPartialPage() {
        return BASE + "login/layout";
    }

    @RequestMapping(value = "/documentation")
    public String documentation() {
        return "redirect:swagger-ui.html";
    }
}
