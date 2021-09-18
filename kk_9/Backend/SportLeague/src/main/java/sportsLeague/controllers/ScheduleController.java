package sportsLeague.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import sportsLeague.entity.Schedule;
import sportsLeague.entity.User;
import sportsLeague.services.ScheduleService;

@RestController
//@RequestMapping
public class ScheduleController {

    @Autowired
    ScheduleService  scheduleService;

    @PostMapping("/schedule/add")
    public void addSchedule (@RequestBody Schedule add) {
        scheduleService.addToSchedule(add);
    }

    @PostMapping("/schedule/addWinner")
    public void addWinner (@RequestBody Schedule winner) {
        scheduleService.addWinner(winner);
    }

    @RequestMapping("/schedule/get")
    public List<Schedule> getSchedule() {
        List<Schedule> schedule = scheduleService.getSchedules();
        return schedule;
    }

//    @RequestMapping(method = RequestMethod.POST, value = "/schedule/add")
//    public void addToSchedule(@RequestBody Schedule schedule) {
//        scheduleService.addToSchedule(schedule);
//    }
//    @PostMapping("/schedule/date")
//    public void addSchedule (@RequestBody Schedule addDate) {
//        scheduleService.addToSchedule(addDate);
//    }
//    @PostMapping("/schedule/team")
//    public void addTeam (@RequestBody Schedule addTeam) {
//        scheduleService.addToSchedule(addTeam);
//    }
//    @PostMapping("/schedule/sport")
//    public void addSport (@RequestBody Schedule addSport) {
//        scheduleService.addToSchedule(addSport);
//    }





}
