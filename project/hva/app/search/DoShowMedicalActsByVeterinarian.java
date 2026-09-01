package hva.app.search;

import hva.core.Hotel;
import hva.app.exception.UnknownVeterinarianKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.app.employee.Prompt;

//FIXME add more imports if needed

/**
 * Show all medical acts of a given veterinarian.
 **/
class DoShowMedicalActsByVeterinarian extends Command<Hotel> {

  DoShowMedicalActsByVeterinarian(Hotel receiver) {
    super(Label.MEDICAL_ACTS_BY_VET, receiver);
    addStringField("employeeKey", Prompt.employeeKey());
  }
  
  @Override
  protected void execute() throws CommandException {
    _display.popup(_receiver.atosMedicosVeterinario(stringField("employeeKey")));
  }
}
