package controllers;

import models.Assessment;
import models.Member;
import play.Logger;
import play.mvc.Controller;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

public class DashboardTrainer extends Controller
{

    public static void index(Long id) {
        Logger.info("Rendering Dashboard");
        Member member = Member.findById(id);
        List<Assessment> assessmentList =  member.assessmentList;
        render("dashboardtrainer.html", member, assessmentList );

    }

    public static void addAssessmentComment (Long assessmentid, Long id, String comments)
    {
        Assessment assessment = Assessment.findById(assessmentid);
        assessment.trainerComment = comments;
        assessment.save();
        Member member = Member.findById(id);
        List<Assessment> assessmentList =  member.assessmentList;
        render("dashboardtrainer.html", member, assessmentList );

    }


}
