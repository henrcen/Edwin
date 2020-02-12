package com.stuypulse.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {

    public Intake() {
    }

    public void extend() {
        System.out.println("extending intake");
    }

    public void retract() {
        System.out.println("retracting intake");
    }

    public void toggle() {
        System.out.println("toggling intake");
    }

    public void acquire() {
        System.out.println("acquiring");
    }

    public void deacquire() {
        System.out.println("deacquring");
    }

    public void stop() {
        System.out.println("stopping intake");
    }

    public void setMotor(double speed) {
        System.out.println("setting intake motor at" + speed);
    }


}