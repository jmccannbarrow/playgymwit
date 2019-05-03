package models;

import controllers.Accounts;
import play.Logger;
import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

import static play.mvc.Controller.redirect;


@Entity
public class Member extends Model {
    public String firstname;
    public String lastname;
    public String email;
    public String password;
    public String address;
    public String gender;
    public double height;
    public double startingweight;
    public double bmi;
    public String bmiCategory;
    public boolean isIdealBodyWeight;



    @OneToMany(cascade = CascadeType.ALL)
    public List<Member> memberList = new ArrayList<Member>();


    @OneToMany(cascade = CascadeType.ALL)
    public List<Assessment> assessmentList = new ArrayList<Assessment>();

    public Member(String firstname, String lastname, String email, String password, String address, String gender, double height, double startingweight, double bmi) {

        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.address = address;
        this.gender = gender;
        this.height = height;
        this.startingweight = startingweight;
        this.bmi = bmi;

    }

    public Member(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;




    }



    public static Member findByEmail(String email) {
        return find("email", email).first();
    }

    public static Member findByID(Long id) {return find("id", id).first();
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public double calculateBmi(double height, double startingweight)
    {

        Double BMI = (startingweight) / (height) * (height) ;

        return BMI;



    }




}