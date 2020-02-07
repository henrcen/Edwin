package com.stuypulse.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.stuypulse.robot.Constants;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Intake extends SubsystemBase {
    private CANSparkMax intakeMotor;
    private Solenoid intakeSolenoid;
  
    public Intake() {
        intakeMotor = new CANSparkMax(Constants.INTAKE_MOTOR_PORT, MotorType.kBrushless);
        intakeSolenoid = new Solenoid(Constants.INTAKE_SOLENOID_PORT);

    }

    public boolean isExtended() {
        return intakeSolenoid.get();
    }

    public void extend() {
        intakeSolenoid.set(true);
        System.out.println("Intake Extend : " + intakeMotor);
    }

    public void retract() {
        intakeSolenoid.set(false);
        System.out.println("Intake Retract : " + intakeMotor);
    }

    public void acquire() {
        intakeMotor.set(1);
        System.out.println("Intake Acquire : " + intakeMotor);
    }

    public void deacquire() {
        intakeMotor.set(-1);
        System.out.println("Intake Deacquire : " + intakeMotor);
    }

    public void setMotor(double speed) {
        intakeMotor.set(speed);
        System.out.println("Intake setMotor : " + intakeMotor);
    }
}