package com.stuypulse.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Chimney extends SubsystemBase {

    public Chimney() {
    }

    public void liftUp() {
        System.out.println("moving chimney lift up");
    }

    public void liftDown() {
        System.out.println("moving chimney lift down");
    }

    public void stop() {
        System.out.println("stopping chimney");
    }

}