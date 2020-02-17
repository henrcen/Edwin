package com.stuypulse.robot.util;

import com.stuypulse.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.PWMSparkMax;
import edu.wpi.first.wpilibj.Timer;

public class LEDController {

    private static PWMSparkMax controller;
    private static Shooter shooter;
    private static Timer timer;

    public LEDController(int port, Shooter shooter) {
        controller = new PWMSparkMax(port);
        this.shooter = shooter;
    }

    public static void setValue(double value) {
        controller.set(value);
    }

    enum Color {
        ORANGE_SOLID, 
        PINK_SOLID, 
        WHITE_SOLID, 
        ORANGE_PULSE, 
        PINK_PULSE, 
        WHITE_PULSE, 
        GREEN_SOLID, 
        YELLOW_SOLID, 
        RED_SOLID, 
        WHITE_SHOT
    }

    public static void setColor(Color color) {
        switch (color) {
            case ORANGE_SOLID:
                setValue(0.65);
                break;
            case PINK_SOLID:
                setValue(0.57);
                break;
            case WHITE_SOLID:
                setValue(0.93);
                break;
            case ORANGE_PULSE:
                setValue(0.65);
                timer.delay(0.5);
                setValue(0.99);
                timer.delay(0.1);
                break;
            case PINK_PULSE:
                setValue(0.57);
                timer.delay(0.5);
                setValue(0.99);
                timer.delay(0.1);
                break;
            case WHITE_PULSE:
                setValue(0.93);
                timer.delay(0.5);
                setValue(0.99);
                timer.delay(0.1);
                break;
            case GREEN_SOLID:
                setValue(0.77);
                break;
            case YELLOW_SOLID:
                setValue(0.69);
                break;
            case RED_SOLID:
                setValue(0.61);
                break;
            case WHITE_SHOT:
                setValue(0.13); //TODO: set speed and brightness on blinkin LED
                break;
        }
    }

    public static void controlLEDs() {
        switch (shooter.getMode()) {
            case SHOOT_FROM_INITIATION_LINE:
                setColor(Color.ORANGE_SOLID);
                break;
            case SHOOT_FROM_TRENCH:
                setColor(Color.WHITE_SOLID);
                break;
            case SHOOT_FROM_FAR:
                setColor(Color.PINK_SOLID);
                break;
            case CHARGING_SHOT_INITATION_LINE:
                setColor(Color.ORANGE_PULSE);
                break;
            case CHARING_SHOT_TRENCH:
                setColor(Color.WHITE_PULSE);
                break;
            case CHARGING_SHOT_FAR:
                setColor(Color.PINK_PULSE);
                break;
            case ALIGNING:
                setColor(Color.YELLOW_SOLID);
                break;
            case ALIGNED:
                setColor(Color.GREEN_SOLID);
                break;
            case HOLDING_MAX_CELLS:
                setColor(Color.RED_SOLID);
                break;
            case ACQUIRED_ONE_CELL:
                setColor(Color.WHITE_SHOT);
                break;
        }
    }

}