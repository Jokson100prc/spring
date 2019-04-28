package final_project.football_old_paper_game;

import java.util.ArrayList;
import java.util.List;

public class CheckPointId {

    public static List<String> checkPointsId = new ArrayList<>();

    public static List<String> checkPointIdGenerator() {
        int firstHalfId = 0;
        int secondHalfId = 0;

        List<Integer> firstHalfIdNumbers = new ArrayList<>();
        List<Integer> secondHalfIdNumbers = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            firstHalfId++;

            firstHalfIdNumbers.add(firstHalfId);
            String fHIdNumToString = firstHalfIdNumbers.get(i).toString();
            for (int j = 0; j < 11; j++) {

                if (secondHalfId > 10) {
                    secondHalfId = 0;
                }
                secondHalfId++;

//                System.out.println(firstHalfId + " / " + secondHalfId);
                secondHalfIdNumbers.add(secondHalfId);
                String sHIdNumToString = secondHalfIdNumbers.get(j).toString();
                String checkPointId = fHIdNumToString + " : " + sHIdNumToString;

//                System.out.println(checkPointId);
                checkPointsId.add(checkPointId);
            }
//            System.out.println(firstHalfId + " " + " " + secondHalfId);
        }

//        for (int i = 0; i < checkPointsId.size(); i++) {
//            System.out.println(checkPointsId.get(i));
//        }

//        System.out.println(checkPointsId.size());
        return checkPointsId;
    }

    public List<String> getCheckPointsId() {
        return checkPointsId;
    }

    public void setCheckPointsId(List<String> checkPointsId) {
        this.checkPointsId = checkPointsId;
    }
}
