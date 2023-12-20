package ch.epfl.cs107.icmon.gamelogic.actions;

public class LogAction implements Action{
    private String msg;

    public LogAction(String msg){
        this.msg=msg;
    }
    @Override
    public void perform() {
        System.out.println(msg);
    }

    //@Override
    //public void execute() {
       // perform();
    //}
}
