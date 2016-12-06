package com.company;

import spark.ModelAndView;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;

import java.util.HashMap;

public class Main {
    static User user;

    public static void main(String[] args) {

        Spark.init();

        Spark.get(
                "/",
                //used Lambda that is going to push information to the website
                ((request, response) -> {
                    //input data to push out the data you want
                    HashMap m = new HashMap();
                    if (user == null) {
                        return new ModelAndView(m, "login.html");
                    } else {
                        m.put("name", user.name);
                        return new ModelAndView(m, "home.html");
                    }
                }),
                new MustacheTemplateEngine()
        );
        Spark.post(
                "/login",
                ((request, response) -> {
                    String name = request.queryParams("loginName");
                    user = new User(name);
                    response.redirect("/");
                    return "";
                })
        );
        Spark.post(
                "/logout",
                ((request, response) -> {
                    user = null;
                    response.redirect("/");
                    return "";
                })
        );
    }
}



//        Spark.get(
//                "/",
//                //used Lambda that is going to push information to the website
//                ((request, response) -> {
//                    //input data to push out the data you want
//                    HashMap m = new HashMap();
//                    m.put("name", "Superior Being.");
//                    //return HashMap and the path for the data
//                    return new ModelAndView(m, "home.html");
//                }),
//                new MustacheTemplateEngine()
//        );
//    }
//}
