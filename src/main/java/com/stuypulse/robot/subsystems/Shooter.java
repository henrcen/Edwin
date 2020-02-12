package com.stuypulse.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {

    public enum Mode {
        SHOOT_FROM_INITIATION_LINE, SHOOT_FROM_FAR, SHOOT_FROM_TRENCH
    }

    private Mode mode;

    public Shooter() {
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public Mode getMode() {
        return mode;
    }

    public void runShooter(double speed) {
        System.out.println("running shooter at " + speed);
    }

    public void reverseShooter() {
        System.out.println("reversing shooter");
    }

    public void stopShooter() {
        System.out.println("stopping shooter");
    }

    public void runFeeder() {
        System.out.println("running feeder");
    }

    public void reverseFeed() {
        System.out.println("reverse feeder");
    }

    public void stopFeeder() {
        System.out.println("stopping feeder motor");
    }
    
    public void extendHoodSolenoid() {
        System.out.println("extending hood solenoid");
    }

    public void retractHoodSolenoid() {
        System.out.println("retracting hood solenoid");
    }

    public void setDefaultSolenoidPosition() {
        retractHoodSolenoid();
    }
}