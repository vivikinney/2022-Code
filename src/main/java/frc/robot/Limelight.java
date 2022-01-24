package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Log;

public class Limelight implements Loggable {
    private static NetworkTable table;

    @Log.BooleanBox(rowIndex = 0, columnIndex = 1)
    public boolean isFound() {

        return false;
    }

    public void getLimelightValues() {
        Constants.tv = NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("tv").getDouble(0);
        Constants.ta = NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("ta").getDouble(0);
        Constants.tx = NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("tx").getDouble(0);
        Constants.ty = NetworkTableInstance.getDefault().getTable(Constants.limelight).getEntry("ty").getDouble(0);
    }

    public static void limeLightPeriodic() {
        
    }

    public static void setPipeline(int pipeline) {
        setEntry("pipeline", pipeline);
    }

    public static double getPipeline() {
        return getEntry("getpipe");
    }

    private static void setEntry(String entry, double value) {
        table.getEntry(entry).setNumber(value);
    }

    private static double getEntry(String entry) {
        return table.getEntry(entry).getDouble(0);
    }
}
