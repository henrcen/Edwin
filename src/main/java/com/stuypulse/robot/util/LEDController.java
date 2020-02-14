package com.stuypulse.robot.util;

import com.stuypulse.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.PWMSparkMax;

public class LEDController {

    private static PWMSparkMax controller;
    private static Shooter shooter;

    public LEDController(int port, Shooter shooter) {
        controller = new PWMSparkMax(port);
        this.shooter = shooter;
    }

    public static void setValue(double value) {
        controller.set(value);
    }

    enum Color {
        PINK, ORANGE, WHITE, VIOLET
    }

    public static void setColor(Color color) {
        switch (color) {
            case PINK:
                setValue(0.57);
                break;
            case ORANGE:
                setValue(0.65);
                break;
            case WHITE:
                setValue(0.93);
                break;
            case VIOLET:
                setValue(0.91);
                break;
        }
    }

    public static void controlLEDs() {
        switch (shooter.getMode()) {
            case SHOOT_FROM_FAR:
                setColor(Color.PINK);
                break;
            case SHOOT_FROM_INITIATION_LINE:
                setColor(Color.ORANGE);
                break;
            case SHOOT_FROM_TRENCH:
                setColor(Color.WHITE);
        }
    }

}