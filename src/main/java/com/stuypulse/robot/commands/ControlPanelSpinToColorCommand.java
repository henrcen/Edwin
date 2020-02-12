package com.stuypulse.robot.commands;

import com.stuypulse.robot.Constants;
import com.stuypulse.robot.subsystems.ControlPanel;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ControlPanelSpinToColorCommand extends CommandBase {
    private final ControlPanel controlPanel;
    private Color goal;
    public ControlPanelSpinToColorCommand(ControlPanel controlPanel) {
        this.controlPanel = controlPanel;
        addRequirements(controlPanel);
    }

    private void setTargetColor() {
        String gameData = DriverStation.getInstance().getGameSpecificMessage();
        if(gameData != null && gameData.length() > 0) {
            System.out.println("target color: " + gameData.charAt(0));
        }
    }

    @Override
    public void execute() {
        System.out.println("Running ControlPanelSpinToColorCommand");
        if (goal == null) {
            setTargetColor();
        } else {
            controlPanel.turn(Constants.CONTROL_PANEL_TURN_SPEED);
        }
    }
    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("Ending ControlPanelSpinToColorCommand");
        controlPanel.stop();
    }
}