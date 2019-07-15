package final_project.football_old_paper_game;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

import static final_project.football_old_paper_game.Court.CHECKPOINT_HASHMAP;

@SpringBootApplication
public class FootballOldPaperGameApplication {

    public static void main(String[] args) {
        SpringApplication.run(FootballOldPaperGameApplication.class, args);

//        CheckPointId checkPointId = new CheckPointId();
//        checkPointId.checkPointIdGenerator();

//        CheckPoint checkPoint = new CheckPoint(true,true,true,true,true,true,true,true);

        List<String> checkPointsId = CheckPointId.checkPointIdGenerator();


        for (int i = 0; i <checkPointsId.size() ; i++) {


            CHECKPOINT_HASHMAP.put(checkPointsId.get(i),new CheckPoint(true,true,true,true,true,true,true,true));
            boolean b = CHECKPOINT_HASHMAP.containsKey("11 : 10");
//            System.out.println(b);
        }
        for (String i : CHECKPOINT_HASHMAP.keySet()) {
            System.out.println("key: " + i + " value: " + CHECKPOINT_HASHMAP.get(i));
        }
    }

}
