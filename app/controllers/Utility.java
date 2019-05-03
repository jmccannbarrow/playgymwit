package controllers;

import models.Assessment;
import models.Member;

public class Utility {

    private static double toTwoDecimalPlaces(double num){

        return (int) (num *100 ) /100.0;
    }
        public static double calculateBMI(Member member, Assessment assessment) {
            //Returns the BMI for the member based on the calculation:
            //BMI is weight divided by the square of the height.

            Double BMI = (assessment.weight / (member.height * member.height)) ;
            return toTwoDecimalPlaces(BMI);
        }

    public static String determineBMICategory(double bmiValue) {

        String BMICategory;

        if (bmiValue < 16) {
            BMICategory = "SEVERELY UNDERWEIGHT";
        }
        else if (bmiValue >= 16 && bmiValue < 18.5) {
            BMICategory = "UNDERWEIGHT";
        }
        else if (bmiValue >= 18.5 && bmiValue < 25) {
             BMICategory = "NORMAL";
        }
        else if (bmiValue >= 25 && bmiValue < 30) {
             BMICategory = "OVERWEIGHT";
        }
        else if (bmiValue >= 30 && bmiValue < 35) {
            BMICategory = "MODERATELY OBESE";
        }
        else if (bmiValue >= 35) {
              BMICategory = "SEVERELY OBESE";
        }
        else {
            BMICategory = "Invalid bmiValue entered.";
        }
                return BMICategory;

    }

    public static boolean isIdealBodyWeight(Member member, Assessment assessment)

    {
        //Returns a boolean to indicate if the member has an ideal body weight based on the Devine formula

        double height_in_metres;
        double height_in_inches;
        double weight;
        String gender;

        height_in_metres = member.height;
        weight = assessment.weight;

        //Convert height to inches
        height_in_inches = (height_in_metres * 39.37);
        gender = member.gender.toUpperCase();

        if (gender.equals("MALE"))

        {

            // if the member is 5 feet or less, return 50kg for male
            if (height_in_inches <= 60)
            {
                if (weight > 50)
                {
                    return false;
                } else {
                    return true;
                }
            }

            //For males, an ideal body weight is: 50 kg + 2.3 kg for each inch over 5 feet
             else

            {
                if (weight > (50 + (2.3 * (height_in_inches - 60))))
                {
                    return false;
                } else {

                    return true;
                }
            }
        }

        else if (gender.equals("FEMALE"))
        {
            //if the member is 5 feet or less, return 45.5kg for female
            if (height_in_inches <= 60)
            {
                if (weight > 45.5)
                {
                    return false;
                } else {
                    return true;
                }
            } else {
                if (weight > (45.5 + (2.3 * (height_in_inches - 60))))
                {
                    return false;
                } else {
                    return true;
                }
            }
        }

        //if no gender is specified, return the result of the female calculation
         else

        {
            //if the member is 5 feet or less, return 45.5kg (same as for female)
            if (height_in_inches <= 60)
            {
                if (weight > 45.5)
                {
                    return false;

                } else {
                    return true;
                }
            } else {
                if (weight > (45.5 + (2.3 * (height_in_inches - 60))))
                {
                    return  false;
                } else {
                    return true;
                }



            }





        }

    }


}
