package hva.app.vaccine;

import hva.core.Hotel;
import hva.app.exception.UnknownSpeciesKeyException;
import hva.app.exception.DuplicateVaccineKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.core.exception.DuplicateVaccineKeyExceptionCore;
import hva.core.exception.UnknownSpeciesKeyExceptionCore;


class DoRegisterVaccine extends Command<Hotel> {

  DoRegisterVaccine(Hotel receiver) {
    super(Label.REGISTER_VACCINE, receiver);
    addStringField("vaccineKey", Prompt.vaccineKey());
    addStringField("vaccineName", Prompt.vaccineName());
    addStringField("listOfSpeciesKeys", Prompt.listOfSpeciesKeys());
  }

  @Override
  protected final void execute() throws CommandException {
    try{
      _receiver.registerVaccine(stringField("vaccineKey"), stringField("vaccineName"), stringField("listOfSpeciesKeys").split(","));
    }
      catch ( DuplicateVaccineKeyExceptionCore  e){
      throw new  DuplicateVaccineKeyException(e.getKey());
      }
      catch ( UnknownSpeciesKeyExceptionCore  e){
      throw new  UnknownSpeciesKeyException(e.getKey());
    }
  }
}
