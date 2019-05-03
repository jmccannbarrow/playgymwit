package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity

public class Assessment extends Model
{
    public double weight;
    public double chest;
    public double thigh;
    public double upperArm;
    public double waist;
    public double hips;
    public double BMI;
    public String trainerComment;


    @OneToMany(cascade = CascadeType.ALL)
    public List<Assessment> assessments = new ArrayList<Assessment>();

    public Assessment(double weight,double chest,double thigh,double upperArm,double waist,double hips, double BMI)
    {
        this.weight = weight;
        this.chest = chest;
        this.thigh = thigh;
        this.upperArm = upperArm;
        this.waist = waist;
        this.hips = hips;
        this.BMI = BMI;


     }

    public Assessment(double weight,double chest,double thigh,double upperArm,double waist,double hips, double BMI,String trainerComment)
    {
        this.weight = weight;
        this.chest = chest;
        this.thigh = thigh;
        this.upperArm = upperArm;
        this.waist = waist;
        this.hips = hips;
        this.BMI = BMI;
        this.trainerComment = trainerComment;

    }


}