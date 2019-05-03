package controllers;

import java.util.List;

import models.Assessment;
import play.Logger;
import play.mvc.Controller;

public class AssessmentCtrl extends Controller
{
    public static void index(Long id)
    {
        Assessment assessment = Assessment.findById(id);
        Logger.info ("Assessment id = " + id);
        render("assessment.html", assessment);
    }




}
