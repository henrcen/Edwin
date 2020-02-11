package com.stuypulse.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.stuypulse.robot.Constants;

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