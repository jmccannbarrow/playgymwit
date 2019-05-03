package controllers;

import models.Assessment;
import models.Member;
import play.Logger;
import play.mvc.Controller;

import java.util.List;

public class Dashboard extends Controller
{

  public static void index() {
    Logger.info("Rendering Dashboard");
    Member member = Accounts.getLoggedInMember();
    List<Assessment> assessmentList =  member.assessmentList;
    render("dashboard.html", member,assessmentList);
  }


  public static void addAssessment(double weight, double chest,double thigh,double upperArm,double waist,double hips, double BMI)
  {
    Member member = Accounts.getLoggedInMember();

    Assessment assessment = new Assessment(weight, chest, thigh, upperArm, waist, hips, BMI );
    member.assessmentList.add(assessment);
    member.bmi = (Utility.calculateBMI(member, assessment));
    member.bmiCategory = (Utility.determineBMICategory(Utility.calculateBMI(member, assessment)));
    member.isIdealBodyWeight = Utility.isIdealBodyWeight(member, assessment);
    member.save();
    Logger.info("Adding Assessment" + weight, chest, thigh, upperArm, waist, hips, BMI);


    redirect("/dashboard");
  }

  public static void deleteAssessment (Long assessmentid)
  {
    Member member = Accounts.getLoggedInMember();
    Assessment assessment = Assessment.findById(assessmentid);
    member.assessmentList.remove(assessment);
    member.save();
    Logger.info("Deleting" + assessmentid);
    redirect("/dashboard");

  }

  public static void deleteMember (Long memberid)
  {
    Member member = Accounts.getLoggedInMember();
    Member memberList = Member.findById(memberid);
    member.memberList.remove(member);
    memberList.save();
    Logger.info("Deleting" + memberid);
    redirect("/admin");

  }

}
