package hva.app.search;

import hva.core.Hotel;
import hva.core.exception.UnknownHabitatKeyExceptionCore;
import hva.app.exception.UnknownHabitatKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.app.habitat.Prompt;
//FIXME add more imports if needed

/**
 * Show all animals of a given habitat.
 **/
class DoShowAnimalsInHabitat extends Command<Hotel> {

  DoShowAnimalsInHabitat(Hotel receiver) {
    super(Label.ANIMALS_IN_HABITAT, receiver);
    addStringField("habitatKey", Prompt.habitatKey());
  }

  @Override
  protected void execute() throws CommandException {
    try{
    _display.popup(_receiver.mostrarAnimaisHabitat(stringField("habitatKey")));
    }catch(UnknownHabitatKeyExceptionCore e){
      throw new UnknownHabitatKeyException(e.getKey());
    }
  }
}