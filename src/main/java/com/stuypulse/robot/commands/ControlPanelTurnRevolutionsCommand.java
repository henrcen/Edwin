package com.stuypulse.robot.commands;

import com.stuypulse.robot.subsystems.ControlPanel;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ControlPanelTurnRevolutionsCommand extends CommandBase {
    private final ControlPanel controlPanel;

    /**
     * Creates a new ExampleCommand.
     *
     * @param subsystem The subsystem used by this command.
     */
    public ControlPanelTurnRevolutionsCommand(ControlPanel controlPanel) {
        this.controlPanel = controlPanel;
        addRequirements(controlPanel);
    }

    @Override
    public void execute() {
        System.out.println("Running ControlPanelTurnRevolutionsCommand");
        controlPanel.turn(1.0);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("Ending ControlPanelTurnRevolutionsCommand");
        controlPanel.stop();
    }
}