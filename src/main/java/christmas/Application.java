package christmas;

import christmas.controller.PlannerController;

public class Application {

    public static void main(String[] args) {

        Config config = new Config();
        PlannerController controller = config.getPlannerController();
        controller.run();

    }

}
