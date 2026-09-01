package hva.app.employee;

import hva.core.Hotel;
import hva.core.exception.DuplicateEmployeeKeyExceptionCore;
import hva.app.exception.DuplicateEmployeeKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

class DoRegisterEmployee extends Command<Hotel> {

  DoRegisterEmployee(Hotel receiver) {
    super(Label.REGISTER_EMPLOYEE, receiver);
    addStringField("employeeKey", Prompt.employeeKey());
    addStringField("employeeName", Prompt.employeeName());
    addStringField("employeeType", Prompt.employeeType());
  }
  
  
  @Override
  protected void execute() throws CommandException {
   try{
    _receiver.registerEmployee(
      stringField("employeeKey"),
      stringField("employeeName"),
      stringField("employeeType"),
      null);
  }
    catch (DuplicateEmployeeKeyExceptionCore e){
      throw new DuplicateEmployeeKeyException(e.getKey());
    } 
  }
}