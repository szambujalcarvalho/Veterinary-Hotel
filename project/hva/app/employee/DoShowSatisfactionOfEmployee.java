package hva.app.employee;

import hva.core.Hotel;
import hva.core.exception.UnknownEmployeeKeyExceptionCore;
import hva.app.exception.UnknownEmployeeKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

class DoShowSatisfactionOfEmployee extends Command<Hotel> {

  DoShowSatisfactionOfEmployee(Hotel receiver) {
    super(Label.SHOW_SATISFACTION_OF_EMPLOYEE, receiver);
    addStringField("employeeKey", Prompt.employeeKey());
  }
  
  @Override
  protected void execute() throws CommandException {
    try{
      _display.popup(_receiver.calcularSatisfacaoFuncionario(stringField("employeeKey")));
    }
    catch (UnknownEmployeeKeyExceptionCore  e){
      throw new UnknownEmployeeKeyException(e.getKey());
    
    }
  }

}
