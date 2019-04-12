package final_project.football_old_paper_game;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FootballOldPaperGameApplication {

    public static void main(String[] args) {
        SpringApplication.run(FootballOldPaperGameApplication.class, args);

        CheckPointId checkPointId = new CheckPointId();
        checkPointId.checkPointIdGenerator();
    }

}
