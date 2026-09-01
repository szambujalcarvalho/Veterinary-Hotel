package hva.app.employee;

import hva.app.exception.NoResponsibilityException;
import hva.app.exception.UnknownEmployeeKeyException;
import hva.core.Hotel;
import hva.core.exception.NoResponsibilityExceptionCore;
import hva.core.exception.UnknownEmployeeKeyExceptionCore;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Remove a given responsability from a given employee of this zoo hotel.
 **/
class DoRemoveResponsibility extends Command<Hotel> {

  DoRemoveResponsibility(Hotel receiver) {
    super(Label.REMOVE_RESPONSABILITY, receiver);
    addStringField("employeeKey", Prompt.employeeKey());
    addStringField("responsibilityKey", Prompt.responsibilityKey());
  }
  
  @Override
  protected void execute() throws CommandException {
   try{
    _receiver.removeResponsabilidade(
      stringField("employeeKey"),
      stringField("responsibilityKey")
    );
   }
    catch (UnknownEmployeeKeyExceptionCore  e){
      throw new UnknownEmployeeKeyException(e.getKey());
    }
    catch (NoResponsibilityExceptionCore  e){
      throw new NoResponsibilityException(e.getKey(),e.getNome());
    }
  }
}
