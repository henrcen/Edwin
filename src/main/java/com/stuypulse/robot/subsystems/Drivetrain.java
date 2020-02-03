package com.stuypulse.robot.subsystems;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.stuypulse.robot.Constants.DrivetrainSettings;
import com.stuypulse.robot.Constants.Ports;

import java.util.Arrays;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Drivetrain extends SubsystemBase {

    // Enum used to store the state of the gear
    public static enum DrivetrainGear {
        HIGH, LOW
    };

    // Turn a list of speed controllers into a speed controller group
    private static SpeedControllerGroup makeControllerGroup(SpeedController... controllers) {
        return new SpeedControllerGroup(controllers[0], Arrays.copyOfRange(controllers, 1, controllers.length));
    }

    // Take a list of motors and return the ones used in high gear
    private static CANSparkMax[] getHighGear(CANSparkMax[] motors) {
        return Arrays.copyOfRange(motors, 0, 2);
    }

    // Take a list of motors and return the ones used in low gear
    private static CANSparkMax[] getLowGear(CANSparkMax[] motors) {
        return motors;
    }

    // An array of motors on the left and right side of the drive train
    private CANSparkMax[] mLeftMotors;
    private CANSparkMax[] mRightMotors;

    // An encoder for each side of the drive train
    private CANEncoder mLeftEncoder;
    private CANEncoder mRightEncoder;

    // DifferentialDrive and Gear Information
    private DrivetrainGear mGear;
    private Solenoid mGearShift;
    private DifferentialDrive mHighGearDrive;
    private DifferentialDrive mLowGearDrive;

    // NAVX for Gyro
    //private AHRS mNavX;

    public Drivetrain() {
        // Add Motors to list
        mLeftMotors = new CANSparkMax[] { 
                new CANSparkMax(Ports.Drivetrain.kLeftTop, MotorType.kBrushless),
                new CANSparkMax(Ports.Drivetrain.kLeftMiddle, MotorType.kBrushless),
                new CANSparkMax(Ports.Drivetrain.kLeftBottom, MotorType.kBrushless) };

        mRightMotors = new CANSparkMax[] { 
                new CANSparkMax(Ports.Drivetrain.kRightTop, MotorType.kBrushless),
                new CANSparkMax(Ports.Drivetrain.kRightMiddle, MotorType.kBrushless),
                new CANSparkMax(Ports.Drivetrain.kRightBottom, MotorType.kBrushless) };

        // Configure Motors
        setInverted(true);
        setSmartCurrentLimit(DrivetrainSettings.kCurrentLimit);
        setDistancePerRotation(DrivetrainSettings.kDistancePerRotation);

        // Create list of encoders based on motors
        mLeftEncoder = mLeftMotors[1].getEncoder();
        mRightEncoder = mRightMotors[1].getEncoder();

        // Set Gear to Low
        mGear = DrivetrainGear.LOW;

        // Create DifferentialDrive for different gears
        mHighGearDrive = new DifferentialDrive(
                makeControllerGroup(getHighGear(mLeftMotors)),
                makeControllerGroup(getHighGear(mRightMotors)));

        mLowGearDrive = new DifferentialDrive(
                makeControllerGroup(getLowGear(mLeftMotors)),
                makeControllerGroup(getLowGear(mRightMotors)));

        mGearShift = new Solenoid(Ports.Drivetrain.kGearShift);

        // Initialize NAVX
        //mNavX = new AHRS(SPI.Port.kMXP);
    }

    /**
     * Set the smart current limit of all the motors
     * 
     * @param limit smart current limit
     */
    public void setSmartCurrentLimit(int limit) {
        for (CANSparkMax motor : mLeftMotors) {
            motor.setSmartCurrentLimit(limit);
        }

        for (CANSparkMax motor : mRightMotors) {
            motor.setSmartCurrentLimit(limit);
        }

    }

    /**
     * Set isInverted of all the motors
     * 
     * @param inverted desired settings
     */
    public void setInverted(boolean inverted) {
        for (CANSparkMax motor : mLeftMotors) {
            motor.setInverted(inverted);
        }

        for (CANSparkMax motor : mRightMotors) {
            motor.setInverted(inverted);
        }
    }

    /**
     * Sets drivetrain into high gear
     */
    public void setLowGear() {
        stop();
        mGear = DrivetrainGear.LOW;
        mGearShift.set(false);
    }

    /**
     * Sets drivetrain into low gear
     */
    public void setHighGear() {
        stop();
        mGear = DrivetrainGear.HIGH;
        mGearShift.set(true);
    }

    /**
     * Set the distance that is traveled when one rotation of a motor is complete,
     * this helps improve encoder readings
     * 
     * @param distance distance robot moves in one rotation
     */
    public void setDistancePerRotation(double distance) {
        mLeftEncoder.setPositionConversionFactor(distance);
        mRightEncoder.setPositionConversionFactor(distance);
    }

    /**
     * @return distance left side of drivetrain has moved
     */
    public double getLeftDistance() {
        return mLeftEncoder.getPosition();
    }

    /**
     * @return distance right side of drivetrain has moved
     */
    public double getRightDistance() {
        return mRightEncoder.getPosition();
    }

    /**
     * @return distance drivetrain has moved
     */
    public double getDistance() {
        return (getLeftDistance() + getRightDistance()) / 2.0;
    }

    /**
     * @return DifferentialDrive class based on current gear
     */
    public DifferentialDrive getCurrentDrive() {
        if (mGear == DrivetrainGear.HIGH) {
            return mHighGearDrive;
        } else {
            return mLowGearDrive;
        }
    }

    /**
     * Stops drivetrain from moving
     */
    public void stop() {
        tankDrive(0, 0);
    }

    /**
     * Drives using tank drive
     * 
     * @param left  speed of left side
     * @param right speed of right side
     */
    public void tankDrive(double left, double right) {
        getCurrentDrive().tankDrive(left, right, false);
    }

    /**
     * Drives using arcade drive
     * 
     * @param speed    speed of drive train
     * @param rotation amount that it is turning
     */
    public void arcadeDrive(double speed, double rotation) {
        getCurrentDrive().arcadeDrive(speed, rotation, false);
    }

    /**
     * Drives using curvature drive algorithm
     * 
     * @param speed     speed of robot
     * @param rotation  amount that it turns
     * @param quickturn overrides constant curvature
     */
    public void curvatureDrive(double speed, double rotation, boolean quickturn) {
        getCurrentDrive().curvatureDrive(speed, rotation, quickturn);
    }

    /**
     * Drives using curvature drive algorithm with automatic quick turn
     * 
     * @param speed    speed of robot
     * @param rotation amount that it turns
     */
    public void curvatureDrive(double speed, double rotation) {
        if (speed < DrivetrainSettings.kQuickTurnThreshold) {
            curvatureDrive(speed, rotation * DrivetrainSettings.kQuickTurnSpeed, true);
        } else {
            curvatureDrive(speed, rotation, false);
        }
    }
}