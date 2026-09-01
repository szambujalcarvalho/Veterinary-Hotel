package hva.app.employee;

import hva.core.Hotel;
import hva.core.exception.UnknownEmployeeKeyExceptionCore;
import hva.app.exception.UnknownEmployeeKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

class DoAddResponsibility extends Command<Hotel> {

  DoAddResponsibility(Hotel receiver) {
    super(Label.ADD_RESPONSABILITY, receiver);
    addStringField("employeeKey", Prompt.employeeKey());
    addStringField("responsibilityKey", Prompt.responsibilityKey());
  }
  
  @Override
  protected void execute() throws CommandException {
   try{
    _receiver.addResponsabilidade(
      stringField("employeeKey"),
      stringField("responsibilityKey")
    );
    }
    catch (UnknownEmployeeKeyExceptionCore  e){
      throw new UnknownEmployeeKeyException(e.getKey());
    
    }
  }
}