package com.stuypulse.robot.commands;

import com.stuypulse.robot.Constants.DrivetrainSettings;
import com.stuypulse.robot.subsystems.Drivetrain;
import com.stuypulse.stuylib.math.SLMath;

/**
 * Extends off of the DrivetrainPIDAlignmentCommand and uses its controllers to
 * align the robot very specifically. It uses the robots encoders and navx to
 * get these values, and will do other things like, drive in a straight line
 * while making sure the angle is correct, and turning before driving.
 * 
 * WARNING: It is not recommended to combine angle and distance in one command
 * as turning the robot can lead to bad measurements with distance and
 * visa-versa. It is only included to make code more specific.
 */
public class DrivetrainMovementCommand extends DrivetrainAlignmentCommand {

    /**
     * This aligner uses encoders and the navx on the drivetrain to move the
     * drivetrain a very specific amount. First it turns to the desired angle and
     * then it moves the desired amount.
     */
    public static class Aligner implements DrivetrainAlignmentCommand.Aligner {

        private Drivetrain drivetrain;

        private double angle;
        private double distance;
        private boolean justTurning;

        private double goalAngle;
        private double goalDistance;

        public Aligner(Drivetrain drivetrain, double angle, double distance) {
            this.drivetrain = drivetrain;

            this.angle = angle;
            this.distance = distance;
            this.justTurning = false;

            init();
        }

        public Aligner(Drivetrain drivetrain, double angle) {
            this(drivetrain, angle, 0.0);

            this.justTurning = true;
        }

        private double getGyroAngle() {
            return drivetrain.getGyroAngle();
        }

        private double getDistance() {
            return DrivetrainSettings.Encoders.USE_GREYHILLS ? drivetrain.getGreyhillDistance() : drivetrain.getNEODistance();
        }

        /**
         * Set goals based on when the command is initialized
         */
        public void init() {
            goalAngle = getGyroAngle() + angle;
            goalDistance = getDistance() + distance;
        }

        public double getSpeedError() {
            if (justTurning) {
                return 0.0;
            } else {
                return goalDistance - getDistance();
            }
        }

        public double getAngleError() {
            return goalAngle - getGyroAngle();
        }
    }

    /**
     * This command lets you drive forward x amount of feet
     */
    public static class DriveCommand extends DrivetrainMovementCommand {

        /**
         * @param drivetrain drivetrain used to move
         * @param distance number of feet used to turn
         */
		public DriveCommand(Drivetrain drivetrain, double distance) {
			super(drivetrain, 0, distance);
		}
    }

    /**
     * This command lets you turn a certain angle
     */
    public static class TurnCommand extends DrivetrainMovementCommand {

        /**
         * @param drivetrain drivetrain used to turn
         * @param angle angle that you want to turn
         */
		public TurnCommand(Drivetrain drivetrain, double angle) {
			super(drivetrain, angle);
		}
    }

    // Speed the drivetrain should move
    private double speed;

    /**
     * Creates command that moves drivetrain very specific amounts
     * 
     * @param drivetrain the drivetrain you want to move
     * @param angle      the angle you want it to turn before moving (this may*
     *                   affect distance)
     * @param distance   the distance you want it to travel (feet)
     */
    public DrivetrainMovementCommand(Drivetrain drivetrain, double angle, double distance) {
        super(drivetrain, new Aligner(drivetrain, angle, distance));
        speed = 1.0;
    }

    /**
     * Creates command that moves drivetrain very specific amounts
     * 
     * @param drivetrain the drivetrain you want to move
     * @param angle      the angle you want it to turn
     */
    public DrivetrainMovementCommand(Drivetrain drivetrain, double angle) {
        super(drivetrain, new Aligner(drivetrain, angle));
        speed = 1.0;
    }

    // Set the speed of the movement command
    public DrivetrainMovementCommand setSpeed(double speed) {
        this.speed = speed;
        return this;
    }

    // Scale the speed
    public double getSpeed() {
        return SLMath.limit(super.getSpeed(), speed);
    }
}