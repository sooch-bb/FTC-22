package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name = "Auto1Red.java", group = "")
public class Auto1Red extends LinearOpMode {

  private DcMotor frontLeftWheel;
  private DcMotor backLeftWheel;
  private DcMotor wrubby_motor;
  private DcMotor slide_motor;
  private DcMotor frontRightWheel;
  private DcMotor backRightWheel;
  private Servo servo1;
  private Servo servo2;
  
  /**
   * This function is executed when this Op Mode is selected from the Driver Station.
   */
  @Override
  public void runOpMode() {
    //NEWCODE
    ElementLocator Duck = new ElementLocator(this);
    
    frontLeftWheel = hardwareMap.get(DcMotor.class, "frontLeftWheel");
    backLeftWheel = hardwareMap.get(DcMotor.class, "backLeftWheel");
    frontRightWheel = hardwareMap.get(DcMotor.class, "frontRightWheel");
    backRightWheel = hardwareMap.get(DcMotor.class, "backRightWheel");
    wrubby_motor = hardwareMap.get(DcMotor.class, "wrubby_motor");
    slide_motor = hardwareMap.get(DcMotor.class, "slide_motor");
    servo1 = hardwareMap.get(Servo.class, "servo1");
    servo2 = hardwareMap.get(Servo.class, "servo2");
    //NEWCODE
    int pos = 0;
    int slidesleep = 1;
    
    // Put initialization blocks here
    servo2.setPosition(0.8);
    servo1.setPosition(0.6);
    slide_motor.setDirection(DcMotorSimple.Direction.REVERSE);
    waitForStart();
    
    servo2.setPosition(0.6);
    
   //NEWCODE
    pos = Duck.ducklocation();
    telemetry.addData("DUCKPOS", pos);
    telemetry.update();
    if (pos == 1)
      slidesleep = 1;
    else
    if (pos == 2)
      slidesleep = 8000;
    else
    if (pos == 3)
      slidesleep = 12000;
    
    slide_motor.setPower(0.4); //tweak
    sleep(slidesleep); //time it 
    slide_motor.setPower(0.0);
    
    //NEWCODE
    sleep(50000);

    //MOVE FORWARD 2 BLOCKS
    frontLeftWheel.setDirection(DcMotorSimple.Direction.FORWARD);
    backLeftWheel.setDirection(DcMotorSimple.Direction.FORWARD);
    frontRightWheel.setDirection(DcMotorSimple.Direction.REVERSE);
    backRightWheel.setDirection(DcMotorSimple.Direction.REVERSE);
    frontRightWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    frontLeftWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    frontRightWheel.setTargetPosition(870);
    frontLeftWheel.setTargetPosition(870);
    frontRightWheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    frontLeftWheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    frontRightWheel.setPower(0.4);
    frontLeftWheel.setPower(0.4);
    backRightWheel.setPower(0.4);
    backLeftWheel.setPower(0.4);
    while (!(!frontLeftWheel.isBusy() || !frontRightWheel.isBusy())) {
      // Put loop blocks here
      telemetry.addData("TickFR", frontRightWheel.getCurrentPosition());
      telemetry.addData("TicksFL", frontLeftWheel.getCurrentPosition());
      telemetry.update();
    }
    frontLeftWheel.setPower(0);
    frontRightWheel.setPower(0);
    backRightWheel.setPower(0);
    backLeftWheel.setPower(0);
    
    //grippy
    servo2.setPosition(0.5); //test
    servo1.setPosition(0.2); //test
    sleep(2000);
    
    //MOVE BACKWARD 2 BLOCKS
    frontRightWheel.setDirection(DcMotorSimple.Direction.FORWARD);
    backRightWheel.setDirection(DcMotorSimple.Direction.FORWARD);
    frontLeftWheel.setDirection(DcMotorSimple.Direction.REVERSE);
    backLeftWheel.setDirection(DcMotorSimple.Direction.REVERSE);
    frontRightWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    frontLeftWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    frontRightWheel.setTargetPosition(200);
    frontLeftWheel.setTargetPosition(200);
    frontRightWheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    frontLeftWheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    frontRightWheel.setPower(0.4);
    frontLeftWheel.setPower(0.4);
    backRightWheel.setPower(0.4);
    backLeftWheel.setPower(0.4);
     while (!(!frontLeftWheel.isBusy() || !frontRightWheel.isBusy())) {
      // Put loop blocks here
      telemetry.addData("TickFR", frontRightWheel.getCurrentPosition());
      telemetry.addData("TicksFL", frontLeftWheel.getCurrentPosition());
      telemetry.update();
    }
    frontRightWheel.setPower(0);
    frontLeftWheel.setPower(0);
    backRightWheel.setPower(0);
    backLeftWheel.setPower(0);
    
    servo2.setPosition(0.6);
    servo1.setPosition(0.6);
    //sleep(2000);

    
    //STRAFE left
    frontRightWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    frontLeftWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    frontLeftWheel.setDirection(DcMotorSimple.Direction.REVERSE);
    backLeftWheel.setDirection(DcMotorSimple.Direction.FORWARD);
    frontRightWheel.setDirection(DcMotorSimple.Direction.REVERSE);
    backRightWheel.setDirection(DcMotorSimple.Direction.FORWARD);
    sleep(1000);
    frontRightWheel.setPower(0.4);
    frontLeftWheel.setPower(0.4);
    backRightWheel.setPower(0.4);
    backLeftWheel.setPower(0.4);
    sleep(3500);//weak
    frontLeftWheel.setPower(0);
    frontRightWheel.setPower(0);
    backRightWheel.setPower(0);
    backLeftWheel.setPower(0);
    sleep(1000);
    
    //MOVE TO CAROUSEL
    backRightWheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    backLeftWheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    frontLeftWheel.setDirection(DcMotorSimple.Direction.REVERSE);
    backLeftWheel.setDirection(DcMotorSimple.Direction.REVERSE);
    frontRightWheel.setDirection(DcMotorSimple.Direction.FORWARD);
    backRightWheel.setDirection(DcMotorSimple.Direction.FORWARD);
    frontLeftWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    frontRightWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    backLeftWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    backRightWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    //frontRightWheel.setTargetPosition(215); //1755
    //frontLeftWheel.setTargetPosition(215);
    //frontRightWheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    //frontLeftWheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    backRightWheel.setTargetPosition(211); //1755
    backLeftWheel.setTargetPosition(211);
    backRightWheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    backLeftWheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    frontRightWheel.setPower(0.2);
    frontLeftWheel.setPower(0.2);
    backRightWheel.setPower(0.2);
    backLeftWheel.setPower(0.2);
    while (!(!backLeftWheel.isBusy() || !backRightWheel.isBusy())) {
      // Put loop blocks here
      telemetry.addData("TickBR", backRightWheel.getCurrentPosition());
      telemetry.addData("TicksBL", backLeftWheel.getCurrentPosition());
      telemetry.update();
    }
    backRightWheel.setPower(0);
    backLeftWheel.setPower(0);
    frontLeftWheel.setPower(0);
    frontRightWheel.setPower(0);

    
    //WRUBBY CODE
    wrubby_motor.setDirection(DcMotorSimple.Direction.REVERSE);
    wrubby_motor.setPower(0.4);
    sleep(4000); //adjust time
    wrubby_motor.setPower(0.0);
    
    //TURN RIGHT
    frontRightWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    frontLeftWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    backRightWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    backLeftWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    frontLeftWheel.setDirection(DcMotorSimple.Direction.FORWARD);
    backLeftWheel.setDirection(DcMotorSimple.Direction.FORWARD);
    frontRightWheel.setDirection(DcMotorSimple.Direction.FORWARD);
    backRightWheel.setDirection(DcMotorSimple.Direction.FORWARD);
    sleep(1000);
    frontRightWheel.setPower(0.3);
    frontLeftWheel.setPower(0.4);
    backRightWheel.setPower(0.3);
    backLeftWheel.setPower(0.4);
    sleep(1200);
    frontLeftWheel.setPower(0);
    frontRightWheel.setPower(0);
    backRightWheel.setPower(0);
    backLeftWheel.setPower(0);
    
    //MOVE FORWARD TO WAREHOUSE
    frontRightWheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    frontLeftWheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    frontLeftWheel.setDirection(DcMotorSimple.Direction.FORWARD);
    backLeftWheel.setDirection(DcMotorSimple.Direction.FORWARD);
    frontRightWheel.setDirection(DcMotorSimple.Direction.REVERSE);
    backRightWheel.setDirection(DcMotorSimple.Direction.REVERSE);
    frontLeftWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    frontRightWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    frontRightWheel.setTargetPosition(4785);
    frontLeftWheel.setTargetPosition(4785);
    frontRightWheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    frontLeftWheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    frontRightWheel.setPower(1);
    frontLeftWheel.setPower(1);
    backRightWheel.setPower(1);
    backLeftWheel.setPower(1);
    while (!(!frontLeftWheel.isBusy() || !frontRightWheel.isBusy())) {
      // Put loop blocks here
      telemetry.addData("TickFR", frontRightWheel.getCurrentPosition());
      telemetry.addData("TicksFL", frontLeftWheel.getCurrentPosition());
      telemetry.update();
    }
    // Put run blocks here.
    frontLeftWheel.setPower(0);
    frontRightWheel.setPower(0);
    backRightWheel.setPower(0);
    backLeftWheel.setPower(0);
    
  }
}
