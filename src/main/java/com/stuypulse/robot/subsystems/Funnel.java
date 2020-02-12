package com.stuypulse.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.stuypulse.robot.Constants;
import com.stuypulse.robot.util.MotorStall;

public class Funnel extends SubsystemBase {

    public Funnel() {
    }

    public void funnel() {
        System.out.println("funneling");
    }

    public void unfunnel() {
        System.out.println("unfunneling");
    }

    public void stop() {
        System.out.println("stopping funnel");
    }
}