package controllers;


import models.Member;
import play.Logger;
import play.mvc.Controller;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

public class Accounts extends Controller {
    public static void signup() {
        render("signup.html");
    }

    public static void login() {
        render("login.html");
    }



    public static void register(String firstname, String lastname, String email, String password, String address, String gender, double height, double startingweight) {
        Logger.info("Registering new user " + email);
        //calculate bmi and assign it to a variable
        //String BMI = String.valueOf(Double.valueOf(startingweight) / (Double.valueOf(height) * Double.valueOf(height)));
        double BMI = ((startingweight) / (height) * (height));

        Member member = new Member(firstname, lastname, email, password, address, gender, height, startingweight, BMI);
        member.bmiCategory = (Utility.determineBMICategory((BMI)));
        member.save();
        redirect("/");
    }

    public static void authenticate(String email, String password) {
        Logger.info("Attempting to authenticate with " + email + ":" + password);

        Member member = Member.findByEmail(email);
        if ((member != null) && (member.checkPassword(password) == true)) {
            Logger.info("Authentication successful");
            session.put("logged_in_Memberid", member.id);
            Logger.info(email);

            if (email.equals("marge@simpson.com")) {
                Logger.info("in email is trainer");
                redirect("/admin");
            } else {
                Logger.info("in email is not trainer");

                redirect("/dashboard");
            }


        } else {
            Logger.info("Authentication failed");
            redirect("/login");
        }
    }

    public static void logout() {
        session.clear();
        redirect("/");
    }

    public static Member getLoggedInMember() {
        Member member = null;
        if (session.contains("logged_in_Memberid")) {
            String memberId = session.get("logged_in_Memberid");
            member = Member.findById(Long.parseLong(memberId));
        } else {
            login();
        }
        return member;
    }

    public static void updateMemberDetails(String firstname, String lastname, String email, String password, String address, String gender, double height, double startingweight)
    {
        Member member = Accounts.getLoggedInMember();
        member.firstname = firstname;
        member.lastname = lastname;
        member.email = email;
        member.password  = password;
        member.address = address;
        member.gender = gender;
        member.height = height;
        member.startingweight = startingweight;

        double BMI = (member.startingweight / (member.height * member.height));
        member.bmiCategory = (Utility.determineBMICategory((BMI)));
        member.bmi = BMI;
        member.save();
        Logger.info("Updating Member Details" );
        redirect("/dashboard");
    }

}

