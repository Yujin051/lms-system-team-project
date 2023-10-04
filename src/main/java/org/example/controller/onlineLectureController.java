package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class onlineLectureController {


    @GetMapping("/as")
    public String attendanceStatus() {

        return "/onlinelecture/attendance_status";
    }

    @GetMapping("/ttr")
    public String thisTime() {

        return "/onlinelecture/thisTime_registration";
    }

    @GetMapping("/ytr")
    public String youTubeRegistration() {

        return "/onlinelecture/youTube_registration";
    }



}
