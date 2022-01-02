package com.ap43iiitd.willhero;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class Game implements Serializable {
    final static Random r1 = new Random(1337);
    private static final int serialVersionUID = 1;
    transient AnchorPane game_screen;
    ArrayList<GameObject> game_objects;
    ArrayList<Island> islands;
    ArrayList<Orc> orcs;
    BossOrc boss_orc;
    ArrayList<Chest> chests;
    ArrayList<TNT> tnts;
    ArrayList<GameObject> in_scene;
    Player hero;
    transient private Timeline collisionMan;
    transient private Rectangle sword;
    transient private Rectangle shuriken;
    private Boolean paused;
    transient private StackPane game_pane;
    private Integer currentScore = 0;
    transient private Label coins_counter;
    transient private int saveVal;

    public Game(AnchorPane game_screen, Rectangle sword, Rectangle shuriken, StackPane game_pane, Label coins_counter) {
        this.game_pane = game_pane;
        this.game_screen = game_screen;
        this.coins_counter = coins_counter;
        this.sword = sword;
        this.shuriken = shuriken;
        this.paused = false;
            game_objects = new ArrayList<GameObject>();
            islands = new ArrayList<Island>();
            orcs = new ArrayList<Orc>();
            chests = new ArrayList<Chest>();
            tnts = new ArrayList<TNT>();
            initialize_game();
    }

    public void setPref(AnchorPane game_screen, Rectangle sword, Rectangle shuriken, StackPane game_pane, Label coins_counter){
        this.game_pane = game_pane;
        this.game_screen = game_screen;
        this.coins_counter = coins_counter;
        this.sword = sword;
        this.shuriken = shuriken;
        this.paused = false;
//        System.out.println(this.currentScore+"BIBNIBIBI");
    }


    public void play() {
        Game game = this;
        Timeline screen_mover = new Timeline((new KeyFrame(Duration.millis(1500), event -> {
                //get gamescreen x_pos
                //get hero x_pos
                //if sum >= threshold -- shift game_screen

                double gsp = game_screen.getTranslateX();
                double sum = hero.getImage_fx().getTranslateX()+hero.getImage_fx().getLayoutX()+gsp;
                if(sum>300||sum<0){
                    // shift game_screen by sum
                    TranslateTransition gst = new TranslateTransition(Duration.millis(1500), game_screen);
                    gst.setCycleCount(1);
                    gst.setByX(-sum);
                    gst.play();

                }
            })));
            screen_mover.setCycleCount(Timeline.INDEFINITE);
            screen_mover.play();



            collisionMan = new Timeline(new KeyFrame(Duration.millis(5), event->{

                if(!hero.getAlive()) {collisionMan.pause(); gameOver(); return;}

                if(boss_orc.getDead()) {
                    hero.setHasWon();
                    gameWin();
                    // win overlay screen
                }

                ArrayList <GameObject> in_scene = new ArrayList<GameObject>();
                double x_pos = hero.getImage_fx().getTranslateX();
                for (GameObject game_object : game_objects) {
                    if (Math.abs(game_object.getImage_fx().getBoundsInParent().getCenterX() - x_pos) < 1500) {
                        // in scene
                        in_scene.add(game_object);
                    }
                    game_object.setXY();
                }
    //            System.out.println("inscene: " + in_scene.size());
                // Temp line
                game.setIn_scene(in_scene);
                // Temp line
                for(int i = 0; i < in_scene.size(); i++) {
                    in_scene.get(i).getPosition().updatePosition(in_scene.get(i).getImage_fx());
                    for(int j = i + 1; j < in_scene.size(); j++) {

                        in_scene.get(i).collide(in_scene.get(j));
                        in_scene.get(j).collide(in_scene.get(i));
                    }
                }

                int ungray_weapons = hero.getHelmet().hasWeapon();
                if(ungray_weapons==1){
                    sword.setFill(Color.valueOf("d2ff26"));
                }
                else if (ungray_weapons==2){
                    shuriken.setFill(Color.valueOf("d2ff26"));
                }
                else if (ungray_weapons==3){
                    sword.setFill(Color.valueOf("d2ff26"));
                    shuriken.setFill(Color.valueOf("d2ff26"));
                }

                coins_counter.setText("" + hero.getCoins());

            }));
            collisionMan.setCycleCount(Timeline.INDEFINITE);
            collisionMan.play();

    }

    public void heroDash(Label score) {

        if(!hero.getAlive()) {
            return;
        }
        if (paused || hero.getPosition().getVel_x()>0) return;

        currentScore++;
        score.setText(String.valueOf(currentScore));
        Position heroPos = hero.getPosition();
//        System.out.println("gamescreenpos: " +game_screen.getTranslateX());
        heroPos.setVelocity(39.9, heroPos.getVel_y());

        Weapon att = hero.getHelmet().getCurrent_weapon();
        if(att!=null) {
            att.attack(game_objects, game_screen, hero.getImage_fx().getTranslateX()+hero.getImage_fx().getLayoutX(), hero.getImage_fx().getTranslateY()+hero.getImage_fx().getLayoutY());
        }
    }

    public void pause(ImageView pauseButton, Rectangle pause_screen_filter) {
        paused = true;
        collisionMan.pause();

        FadeTransition fTrans = new FadeTransition(Duration.millis(300), pause_screen_filter);
        fTrans.setToValue(1);
        fTrans.play();
        pauseButton.setDisable(true);

        //TODO: Implement menu methods in PauseMenuOverlay Class

        FXMLLoader fxmlLoader = new FXMLLoader(WillHero.class.getResource("PauseMenuOverlay.fxml"));
        try {
            fxmlLoader.load();
            PauseMenuOverlay pmo= fxmlLoader.getController();
//            System.out.println(pmo.overlayPane.getChildren());
            game_pane.getChildren().add(pmo.overlayPane);
            pmo.resumeButton.setOnAction(e->{
//                System.out.println("TAKING ACTION");
                pmo.resumeGame();
//                System.out.println("OPACITY 0");
                collisionMan.play();
                pause_screen_filter.setOpacity(0);
                pauseButton.setDisable(false);
                paused=false;
            });
            pmo.saveButton.setOnAction(event -> {
                pmo.setInstanceOfGame(this);
                pmo.saveGroup.setDisable(false);
                pmo.saveGroup.setOpacity(1);
            });

        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("In-short: Looks like you misplaced some files");
            System.out.println(e.getStackTrace());
        }
    }

    public void gameWin() {
        collisionMan.pause();
        System.out.println("It's a win win situation");
        FadeTransition clearScreen = new FadeTransition(Duration.millis(1500), game_pane);
        clearScreen.setToValue(0);
        clearScreen.setOnFinished(e->{
            game_pane.getChildren().removeAll();
            sceneSwitcher("GameWinOverlay.fxml");
        });
        clearScreen.play();
    }

    public void gameOver() {
        collisionMan.pause();
        System.out.println("Hero respawned? "+hero.hasRespawned());
        if(hero.hasRespawned()||hero.getCoins()<50) {
            FadeTransition byeTrans = new FadeTransition(Duration.millis(1500), game_pane);
            byeTrans.setToValue(0);
            byeTrans.setOnFinished(ev->{
                sceneSwitcher("EnterMenuOverlay.fxml");
            });
            byeTrans.play();
            //game is really over
        }
        else {

            FXMLLoader fxmlLoader = new FXMLLoader(WillHero.class.getResource("GameOverOverlay.fxml"));
            try {
                fxmlLoader.load();
                GameOverOverlay goc = fxmlLoader.getController();
                game_pane.getChildren().add(goc.overlayPane);
                goc.cost.setText("50");//set 50 as cost of life
                goc.progressBar.setProgress(currentScore / 100.0);
                goc.reviveButton.setOnAction(event -> {
                    hero.updateCoins(-50);
                    hero.getImage_fx().setTranslateY(hero.getImage_fx().getY() - 300);
                    hero.setAlive();
                    goc.overlayPane.setDisable(true);
                    goc.overlayPane.setOpacity(0);
                    collisionMan.play();
                });

            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("In-short: Looks like you misplaced some files");
                System.out.println(e.getStackTrace());
            }

            hero.setRespawned();
        }
    }

    public void initialize_game() {

        hero = new Player();
        hero.addToScene(game_screen);

        initialize_islands();
        initialize_orcs();
        initialize_clouds();
        initialize_trees();
        initialize_bonus();
        game_objects.add(hero);
        int x = (int)islands.get(islands.size()-1).getImage_fx().getLayoutX();
        int  y = (int)islands.get(islands.size()-1).getImage_fx().getLayoutY();
        boss_orc = new BossOrc(new Position());
        boss_orc.addToScene(game_screen, x, y-300);
        game_objects.add(boss_orc);



    }

    // generate islands and chests and TNTs and coins and trees
    private void initialize_islands() {
        for(int i = 0; i < 100; i++) {
            islands.add(new Island(new Position(0)));
            int x = 114 + i*350 + r1.nextInt(20);
            int y = 400 + r1.nextInt(100);
            islands.get(i).addToScene(game_screen, x, y);
            game_objects.add(islands.get(i));

            int generate_chest = r1.nextInt(10);
            if(generate_chest == 0 || i == 1) {
                // generate chest at this island
//                Chest temp = new CoinChest();
//                System.out.println("chest gen works");
                int chest_type = r1.nextInt(2);
                if(chest_type == 0) {
                    chests.add(new CoinChest());
                }else {
                    chests.add(new WeaponChest());
                }
//                System.out.println("THIS IS WORKING");
                chests.get(chests.size()-1).addToScene(game_screen, x, y, islands.get(i));
                game_objects.add(chests.get(chests.size()-1));
            }

            int generate_tnt = r1.nextInt(7);
            if(generate_tnt == 0) {
                tnts.add(new TNT(this));
                tnts.get(tnts.size()-1).addToScene(game_screen, x, y, islands.get(i));
                game_objects.add(tnts.get(tnts.size()-1));
            }

            // generate Coins
            int generate_coins = r1.nextInt(2);
            if(generate_coins == 0) {
                int no_of_coins = r1.nextInt(10);
                int delta_x = r1.nextInt(50);
                for(int j = 0; j < no_of_coins; j++) {
                    Coin coin = new Coin();
                    coin.addToScene(game_screen, x + delta_x + j*40, y, islands.get(i));
                    game_objects.add(coin);
                }
            }

        }
    }

    public void initialize_bonus() {
        // testing bonus
        for(int i = 3; i < islands.size(); i+=5) {
            int bonus = r1.nextInt(4);
            if(bonus == 0) {
                System.out.println("BONUS spawns");
                int x = (int) islands.get(i).getImage_fx().getLayoutX();
                int y = (int) islands.get(i).getImage_fx().getLayoutY() - 200;
                PowerUpMushroom mushroom = new PowerUpMushroom();
                PowerUpBrick brick = new PowerUpBrick(mushroom);
                mushroom.addToScene(game_screen, x, y);
                brick.addToScene(game_screen, x, y);
                game_objects.add(mushroom);
                game_objects.add(brick);
            }
        }
    }

    public void initialize_trees() {
        for (Island island : islands) {
            Double x = island.getImage_fx().getLayoutX() + island.getImage_fx().getTranslateX();
            Double y = island.getImage_fx().getLayoutY() + island.getImage_fx().getTranslateY();
            int tree_get = 1 + r1.nextInt(19);
            Image tree_image = new Image("com/ap43iiitd/willhero/imageres/Sprites/Trees/ (".concat(String.valueOf(tree_get)).concat(").png"));
//            Image tree_image = new Image("com/ap43iiitd/willhero/imageres/Sprites/Trees/ (1).png");
            ImageView tree_fx = new ImageView(tree_image);
            tree_fx.setPreserveRatio(true);
            tree_fx.setFitHeight(100 + r1.nextInt(60));
            tree_fx.setLayoutX(x + r1.nextInt(100));
            tree_fx.setLayoutY(y);
            tree_fx.setViewOrder(200000);
            double y_delta = island.getImage_fx().getBoundsInParent().getMinY() - tree_fx.getBoundsInParent().getMaxY();
            tree_fx.setLayoutY(tree_fx.getLayoutY() + y_delta);
            game_screen.getChildren().add(tree_fx);
        }
    }

    private void initialize_orcs() {
        int red_or_green;
        for(int i = 0; i < 100; i++) {
            red_or_green = r1.nextInt(2);
            if(red_or_green == 0) {
                // red
                orcs.add(new RedOrc(new Position()));
                orcs.get(i).addToScene(game_screen, 220 + i*250 + r1.nextInt(20), 300 + r1.nextInt(10));
                game_objects.add(orcs.get(i));
            }else {
                // green
                orcs.add(new GreenOrc(new Position()));
                orcs.get(i).addToScene(game_screen, 220 + i*240 + r1.nextInt(20), 300 + r1.nextInt(10));
                game_objects.add(orcs.get(i));
            }
        }

    }

    public void initialize_clouds() {
        for(int i = 0; i < 50; i++) {
            int no_of_cloud = r1.nextInt(2);
            for(int j = 0; j < 2; j++) {
                if(no_of_cloud == 0 && j == 1) {
                    continue;
                }
                ImageView image_fx = add_cloud();
                image_fx.setPreserveRatio(true);
                image_fx.setFitWidth(200 + r1.nextInt(150));
                image_fx.setLayoutX(220 + i*700 + r1.nextInt(100));
                image_fx.setViewOrder(200090);
                if(j == 0) {
                    image_fx.setLayoutY(100 + r1.nextInt(50));
                }else {
                    image_fx.setLayoutY(400 + r1.nextInt(50));
                }
                game_screen.getChildren().add(image_fx);
                TranslateTransition cloud_mover = new TranslateTransition(Duration.seconds(300), image_fx);
                cloud_mover.setByX(-20000);
                cloud_mover.setCycleCount(1);
                cloud_mover.play();

            }
        }
    }

    private ImageView add_cloud() {
//        System.out.println("CREATING Cloud");
        
        int orc_num = 1 + r1.nextInt(8);
        Image image = new Image("com/ap43iiitd/willhero/imageres/Sprites/Clouds/C (".concat(String.valueOf(orc_num)).concat(").png"));
        return new ImageView(image);
    }


    public void update() {

    }

    public ArrayList<GameObject> getGame_objects() {
        return game_objects;
    }

    public Player getHero() {
        return hero;
    }


    private void resolveCollision() {}
    public void menuHandler() {}
    private void populateScreen() {}
    private void saveGame() {}
    private void serialize() {}
    private void deserialize() {}
    private void loadGame() {}
    private void pause() {}
    private void resume() {}
    private void respawn() {}
    public void addToRender() {}
    public void eraseObject() {}

    // TEMPORARY FUNCTION FOR IN_SCENE

    private void sceneSwitcher(String fxmlName){
        Stage runningInstance = (Stage) game_pane.getScene().getWindow();
//        System.out.println(runningInstance);
        FXMLLoader fxmlLoader = new FXMLLoader(WillHero.class.getResource(fxmlName));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
            runningInstance.setScene(scene);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("In-short: Looks like you misplaced some files");
        }

    }


    public void setIn_scene(ArrayList<GameObject> in_scene) {
        this.in_scene = in_scene;
    }

    public ArrayList<GameObject> getIn_scene() {
        return in_scene;
    }
}


