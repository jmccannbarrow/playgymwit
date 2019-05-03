package controllers;

import models.Assessment;
import models.Member;
import play.mvc.Controller;

import java.util.List;

public class Admin extends Controller {


    public static void index() {

        List<Member> memberList = Member.findAll();

        //Hide trainer name
        Member member_marge = Member.findByEmail("marge@simpson.com");

        memberList.remove(member_marge);
               render("admin.html",  memberList);

    }


}
