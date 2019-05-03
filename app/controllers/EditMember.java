package controllers;


import models.Assessment;
import models.Member;
import play.Logger;
import play.mvc.Controller;

import java.util.List;

public class EditMember extends Controller {


    public static void index() {
        Logger.info("Rendering EditMember");
Member member = Accounts.getLoggedInMember();
        render("editmember.html", member );

    }


}