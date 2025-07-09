package pt.ipp.isep.dei.application.UserMenus;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import pt.ipp.isep.dei.Serializer.Save;
import pt.ipp.isep.dei.application.Authentication.App;
import pt.ipp.isep.dei.domain.simulation.TimeCounter;
import pt.ipp.isep.dei.domain.simulation.Simulation;
import pt.ipp.isep.dei.repository.Simulator;
import pt.ipp.isep.dei.us005.CreateStationUi;
import pt.ipp.isep.dei.us007.DisplayStationsUi;
import pt.ipp.isep.dei.us008.CreateRailwayLineUi;
import pt.ipp.isep.dei.us006.UpgradeStationUi;
import pt.ipp.isep.dei.us009.BuyLocomotiveUI;
import pt.ipp.isep.dei.us010.TrainToRouteUIFX;
import pt.ipp.isep.dei.us011.ListAllTrainsUI;
import pt.ipp.isep.dei.utils.GraphHolder;
import pt.ipp.isep.dei.utils.GraphViewerPane;

public class PlayerMenuController {
    private final String buyStation = "Buy Station";
    private final String upgradeStation = "Upgrade Station";
    private final String listAllStations = "list Stations";
    private final String buildRailwayLine = "Build RailwayLine";
    private final String buyTrain = "Buy Train";
    private final String createRoute = "Assign Route";
    private final String listAllTrains = "List Trains";
    private final String play = "Play";
    private final String pause = "Pause";
    private final String save = "Save";
    private final String exit = "Exit";

    private static String allLog = "================================\n";

    private final int buyStationVal = 1;
    private final int upgradeStationVal = 2;
    private final int listAllStationsVal = 3;
    private final int buildRailwayLineVal = 4;
    private final int buyTrainVal = 5;
    private final int createRouteVal = 6;
    private final int listAllTrainsVal = 7;
    private final int playVal = 8;
    private final int pauseVal = 9;
    private final int saveVal = 10;
    private final int exitVal = 11;

    private TimeCounter timeCounter = Simulation.getInstance().getTimeCounter();

    @FXML public Label timePlace;
    @FXML public Label moneyPlace;

    @FXML private TextArea logTextArea;
    @FXML private Button btn1;
    @FXML private Button btn2;
    @FXML private Button btn3;
    @FXML private Button btn4;
    @FXML private Button btn5;
    @FXML private Button btn6;
    @FXML private Button btn7;
    @FXML private Button btn8;
    @FXML private Button btn9;
    @FXML private Button btn10;
    @FXML private Button btn11;
    @FXML private StackPane graphContainer;

    private GraphViewerPane graphViewerPane;

    @FXML
    public void initialize() {
        Font font = Font.loadFont(getClass().getResourceAsStream("/Fonts/Eastwood.ttf"), 30);

        setButtonsStrings();
        btn1.setOnAction(e -> handleMenu(buyStationVal));
        btn2.setOnAction(e -> handleMenu(upgradeStationVal));
        btn3.setOnAction(e -> handleMenu(listAllStationsVal));
        btn4.setOnAction(e -> handleMenu(buildRailwayLineVal));
        btn5.setOnAction(e -> handleMenu(buyTrainVal));
        btn6.setOnAction(e -> handleMenu(createRouteVal));
        btn7.setOnAction(e -> handleMenu(listAllTrainsVal));
        btn8.setOnAction(e -> handleMenu(playVal));
        btn9.setOnAction(e -> handleMenu(pauseVal));
        btn10.setOnAction(e -> handleMenu(saveVal));
        btn11.setOnAction(e -> handleMenu(exitVal));
        graphViewerPane = new GraphViewerPane(GraphHolder.getGraph(Simulation.getInstance().getCurrentScenario()));
        graphContainer.getChildren().add(graphViewerPane);
    }

    public void setButtonsStrings() {
        btn1.setText(buyStation);
        btn2.setText(upgradeStation);
        btn3.setText(listAllStations);
        btn4.setText(buildRailwayLine);
        btn5.setText(buyTrain);
        btn6.setText(createRoute);
        btn7.setText(listAllTrains);
        btn8.setText(play);
        btn9.setText(pause);
        btn10.setText(save);
        btn11.setText(exit);
        timePlace.setText("                      CurrentYear: " + Simulation.getInstance().getTimeCounter().getCurrentYear());
        moneyPlace.setText("Budget " + Simulation.getInstance().getCurrentScenario().getBudget().getFunds());
    }

    /**
     * Handle the given button selected
     * @param which button that was selected index
     */
    private void handleMenu(int which)  {
        try {
            if (which == buyStationVal)
                new CreateStationUi().run();
            else if (which == upgradeStationVal)
                new UpgradeStationUi().run();
            else if (which == listAllStationsVal)
                new DisplayStationsUi().run();
            else if (which == buildRailwayLineVal)
                new CreateRailwayLineUi().run();
            else if (which == buyTrainVal)
                new BuyLocomotiveUI(timeCounter).run();
            else if (which == createRouteVal)
                new TrainToRouteUIFX().run();
            else if (which == listAllTrainsVal)
                new ListAllTrainsUI().run();
            else if (which == playVal)
                Simulation.getInstance().getTimeCounter().play();
            else if (which == pauseVal)
                Simulation.getInstance().getTimeCounter().pause();
            else if (which == saveVal)
                new Save().saveSimulation();
            else if (which == exitVal) {
                PlayerMenu playerMenu = new PlayerMenu();
                playerMenu.stopRefreshing();
                PlayerSimulationMenu playerSimulationMenu = new PlayerSimulationMenu();
                playerSimulationMenu.start(App.getPrimaryStage());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Add a given string to the log of the program
     * @param log given string with the information of what happened in the simulation
     */
    public void addLog(String log){
        timePlace.setText("                      CurrentYear: " + Simulation.getInstance().getTimeCounter().getCurrentYear());
        moneyPlace.setText("Budget " + Simulation.getInstance().getCurrentScenario().getBudget().getFunds());
        if(!log.isBlank()) {
            allLog = log + "================================\n" + allLog;
        }
        logTextArea.setText(allLog);
    }

    /**
     * close the graph pane
     */
    public void close(){
        if (graphViewerPane != null) {
            graphViewerPane.close();
            graphContainer.getChildren().clear();
        }
    }
}
