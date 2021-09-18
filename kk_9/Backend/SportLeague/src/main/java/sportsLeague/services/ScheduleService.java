package sportsLeague.services;

import org.springframework.stereotype.Service;
import sportsLeague.entity.Prediction;
import sportsLeague.entity.Schedule;
import sportsLeague.repo.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
@Service
public class ScheduleService {
    @Autowired
    public ScheduleRepository scheduleRepo;

    public  Schedule addToSchedule(Schedule s) {
        scheduleRepo.save(new Schedule(0,s.getSport(),s.getDate(),s.getteams(),s.getWinner()));

//        List<Schedule> schedulesList = new ArrayList<>();
//        scheduleRepo.findAll().forEach(schedulesList::add);
//        schedulesList.add(s);
//        scheduleRepo.saveAll(schedulesList);
//        // ScheduleRepo.save(new Schedule(Schedule.getDate(),Schedule.getSport(),Schedule.getTeam()));
        return s;
    }

    //Schedule into list
    public List<Schedule> getSchedules() {
        List<Schedule> schedulesList = new ArrayList<>();
        scheduleRepo.findAll().forEach(schedulesList::add);
        return schedulesList;
    }

    public String getSportById(int id){

        return scheduleRepo.findById(id).get().getSport();
    }
   
    public List<Schedule> getSingleSchedule(Schedule singleSchedule){
    	
    	List<Schedule>singleScheduleList = new ArrayList<>();
    	
    	for(int i =0; i < singleScheduleList.size(); i++) {
    		if(singleScheduleList.get(i).getDate()== singleSchedule.getDate()) {
    			scheduleRepo.findAll().forEach(singleScheduleList::add);
    		}
    	}
		return singleScheduleList;
 
    }
    public int getDateById(int id){

        return scheduleRepo.findById(id).get().getDate();
    }

    public String getTeamById(int id){
        return scheduleRepo.findById(id).get().getteams();
    }

    public void addWinner(Schedule winner) {//date/teams/winner
//        String[] a = winner.split("/");
//        String MatchDate = a[0];
//        String MatchTeams = a[1];
//        String MatchWinner =a[2];
        List<Schedule> allmatches = new ArrayList<>();
        scheduleRepo.findAll().forEach(allmatches::add);
        for(int i = 0; i < allmatches.size();i++){
            if(allmatches.get(i).getDate() == winner.getDate() && allmatches.get(i).getteams().equals(winner.getteams())){
                System.out.println(winner.getDate());
                System.out.println(winner.getteams());
               scheduleRepo.deleteById(allmatches.get(i).getGameid());
               scheduleRepo.save(new Schedule(0,winner.getSport(),winner.getDate(),winner.getteams(),winner.getWinner()));
            }
        }
    }
}
