package org.firstinspires.ftc.teamcode;

import android.graphics.Color;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import org.firstinspires.ftc.robotcore.external.JavaUtil;

@TeleOp(name = "checkSensors (Blocks to Java)", group = "")
public class checkSensors extends LinearOpMode {

  private ColorSensor colorBack;
  private ColorSensor colorLeft;
  private ColorSensor colorRight;

  /**
   * This function is executed when this Op Mode is selected from the Driver Station.
   */
  @Override
  public void runOpMode() {
    colorBack = hardwareMap.colorSensor.get("colorBack");
    colorLeft = hardwareMap.colorSensor.get("colorLeft");
    colorRight = hardwareMap.colorSensor.get("colorRight");

    // Put initialization blocks here.
    waitForStart();
    if (opModeIsActive()) {
      // Put run blocks here.
      while (opModeIsActive()) {
        // Put loop blocks here.
        telemetry.update();
        telemetry.addData("ColorBack", JavaUtil.colorToHue(Color.argb(colorBack.alpha(), colorBack.red(), colorBack.green(), colorBack.blue())));
        telemetry.addData("ColorLeft", JavaUtil.colorToHue(Color.rgb(colorLeft.red(), colorLeft.green(), colorLeft.blue())));
        telemetry.addData("ColorRight", Color.blue(Color.argb(colorRight.alpha(), colorRight.red(), colorRight.green(), colorRight.blue())));
        telemetry.addData("RightBlue", colorRight.blue());
      }
    }
  }
}
